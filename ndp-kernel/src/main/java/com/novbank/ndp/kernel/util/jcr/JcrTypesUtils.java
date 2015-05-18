package com.novbank.ndp.kernel.util.jcr;

import com.novbank.ndp.kernel.exception.RepositoryRuntimeException;
import com.novbank.ndp.kernel.model.NonRDFSourceDescription;
import com.novbank.ndp.kernel.model.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.nodetype.NodeType;
import javax.jcr.nodetype.PropertyDefinition;

import java.util.function.Predicate;

import static com.google.common.base.Preconditions.checkNotNull;
import static javax.jcr.PropertyType.REFERENCE;
import static javax.jcr.PropertyType.UNDEFINED;
import static javax.jcr.PropertyType.WEAKREFERENCE;
import static com.novbank.ndp.kernel.vocabulary.Vocabularies.*;


/**
 * Created by CaoKe on 2015/5/17.
 */
public class JcrTypesUtils {
    public static final String REFERENCE_PROPERTY_SUFFIX = "_ref";

    private static final Logger LOGGER = LoggerFactory.getLogger(JcrTypesUtils.class);

    /**
     * Predicate for determining whether this {@link Node} is a {@link com.novbank.ndp.kernel.model.Container}.
     */
    public static Predicate<Node> isContainer =
            new AnyTypesPredicate(NDP.Container.abbr());

    /**
     * Predicate for determining whether this {@link Node} is a {@link NonRDFSourceDescription}
     */
    public static Predicate<Node> isNonRDFSourceDescription =
            new AnyTypesPredicate(NDP.NonRDFSourceDescription.abbr());


    /**
     * Predicate for determining whether this {@link Node} is a NDP
     * binary.
     */
    public static Predicate<Node> isBinary =
            new AnyTypesPredicate(NDP.Binary.abbr());

    /**
     * Predicate for determining whether this {@link Resource} has a frozen node
     */
    public static Predicate<Resource> isFrozenNode =
            f -> f.hasType(NT.frozenNode.abbr()) || f.getPath().contains(JCR.frozenNode.abbr());

    /**
     * Predicate for determining whether this {@link Node} is a NDP
     * binary.
     */
    public static Predicate<Node> isBlankNode =
            new AnyTypesPredicate(NDP.BlankNode.abbr());

    /**
     * Check if a property is a reference property.
     */
    public static Predicate<Property> isInternalReferenceProperty =
            p -> {
                try {
                    return (p.getType() == REFERENCE || p.getType() == WEAKREFERENCE)
                            && p.getName().endsWith(REFERENCE_PROPERTY_SUFFIX);
                } catch (final RepositoryException e) {
                    throw new RepositoryRuntimeException(e);
                }
            };

    /**
     * Check whether a property is protected (ie, cannot be modified directly) but
     * is not one we've explicitly chosen to include.
     */
    public static Predicate<Property> isProtectedAndShouldBeHidden = p -> {
        try {
            if (!p.getDefinition().isProtected()) {
                return false;
            } else if (p.getParent().isNodeType(NT.frozenNode.abbr())) {
                // everything on a frozen node is protected
                // but we wish to display it anyway and there's
                // another mechanism in place to make clear that
                // things cannot be edited.
                return false;
            } else {
                final String name = p.getName();
                for (String exposedName : EXPOSED_PROTECTED_JCR_TYPES) {
                    if (name.equals(exposedName)) {
                        return false;
                    }
                }
                return true;
            }
        } catch (final RepositoryException e) {
            throw new RepositoryRuntimeException(e);
        }
    };

    /**
     * Check whether a property is an internal property that should be suppressed
     * from external output.
     */
    public static Predicate<Property> isInternalProperty =
            JcrPropertyFunctions.isBinaryContentProperty.or(isProtectedAndShouldBeHidden);

    /**
     * Check if a node is "internal" and should not be exposed e.g. via the REST
     * API
     */
    public static Predicate<Node> isInternalNode = n -> {
        checkNotNull(n, "null is neither internal nor not internal!");
        try {
            return n.isNodeType(JCR.system.abbr());
        } catch (final RepositoryException e) {
            throw new RepositoryRuntimeException(e);
        }
    };

    /**
     * Get the JCR property type ID for a given property name. If unsure, mark
     * it as UNDEFINED.
     *
     * @param node the JCR node to add the property on
     * @param propertyName the property name
     * @return a PropertyType value
     * @throws RepositoryException if repository exception occurred
     */
    public static int getPropertyType(final Node node, final String propertyName)
            throws RepositoryException {
        LOGGER.debug("Getting type of property: {} from node: {}",
                propertyName, node);
        final PropertyDefinition def =
                getDefinitionForPropertyName(node, propertyName);

        if (def == null) {
            return UNDEFINED;
        }

        return def.getRequiredType();
    }

    /**
     * Determine if a given JCR property name is single- or multi- valued.
     * If unsure, choose the least restrictive
     * option (multivalued)
     *
     * @param node the JCR node to check
     * @param propertyName the property name
     *   (which may or may not already exist)
     * @return true if the property is (or could be) multivalued
     * @throws RepositoryException if repository exception occurred
     */
    public static boolean isMultivaluedProperty(final Node node,
                                                final String propertyName)
            throws RepositoryException {
        final PropertyDefinition def =
                getDefinitionForPropertyName(node, propertyName);

        if (def == null) {
            return true;
        }

        return def.isMultiple();
    }

    /**
     * Get the property definition information (containing type and multi-value
     * information)
     *
     * @param node the node to use for inferring the property definition
     * @param propertyName the property name to retrieve a definition for
     * @return a JCR PropertyDefinition, if available, or null
     * @throws javax.jcr.RepositoryException if repository exception occurred
     */
    public static PropertyDefinition getDefinitionForPropertyName(final Node node,
                                                                  final String propertyName)
            throws RepositoryException {

        final NodeType primaryNodeType = node.getPrimaryNodeType();
        final PropertyDefinition[] propertyDefinitions = primaryNodeType.getPropertyDefinitions();
        LOGGER.debug("Looking for property name: {}", propertyName);
        for (final PropertyDefinition p : propertyDefinitions) {
            LOGGER.debug("Checking property: {}", p.getName());
            if (p.getName().equals(propertyName)) {
                return p;
            }
        }

        for (final NodeType nodeType : node.getMixinNodeTypes()) {
            for (final PropertyDefinition p : nodeType.getPropertyDefinitions()) {
                if (p.getName().equals(propertyName)) {
                    return p;
                }
            }
        }
        return null;
    }

    /**
     * When we add certain URI properties, we also want to leave a reference node
     * @param propertyName the property name
     * @return property name as a reference
     */
    public static String getReferencePropertyName(final String propertyName) {
        return propertyName + REFERENCE_PROPERTY_SUFFIX;
    }

    /**
     * Given an internal reference node property, get the original name
     * @param refPropertyName the reference node property name
     * @return original property name of the reference property
     */
    public static String getReferencePropertyOriginalName(final String refPropertyName) {
        final int i = refPropertyName.lastIndexOf(REFERENCE_PROPERTY_SUFFIX);

        if (i < 0) {
            return refPropertyName;
        }
        return refPropertyName.substring(0, i);
    }

    /**
     * Check if a property definition is a reference property
     * @param node the given node
     * @param propertyName the property name
     * @return whether a property definition is a reference property
     * @throws RepositoryException if repository exception occurred
     */
    public static boolean isReferenceProperty(final Node node, final String propertyName) throws RepositoryException {
        final PropertyDefinition propertyDefinition = getDefinitionForPropertyName(node, propertyName);

        return propertyDefinition != null &&
                (propertyDefinition.getRequiredType() == REFERENCE
                        || propertyDefinition.getRequiredType() == WEAKREFERENCE);
    }


    /**
     * Get the closest ancestor that current exists
     *
     * @param session the given session
     * @param path the given path
     * @return the closest ancestor that current exists
     * @throws RepositoryException if repository exception occurred
     */
    public static Node getClosestExistingAncestor(final Session session,
                                                  final String path) throws RepositoryException {
        final String[] pathSegments = path.replaceAll("^/+", "").replaceAll("/+$", "").split("/");

        final StringBuilder existingAncestorPath = new StringBuilder(path.length());
        existingAncestorPath.append("/");

        final int len = pathSegments.length;
        for (int i = 0; i != len; ++i) {
            final String pathSegment = pathSegments[i];

            if (session.nodeExists(existingAncestorPath.toString() + pathSegment)) {
                // Add to existingAncestorPath  ...
                existingAncestorPath.append(pathSegment);
                if (i != (len - 1)) {
                    existingAncestorPath.append("/");
                }
            } else {
                if (i != 0) {
                    existingAncestorPath.deleteCharAt(existingAncestorPath.length() - 1);
                }
                break;
            }

        }

        return session.getNode(existingAncestorPath.toString());
    }
}
