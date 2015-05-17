package com.novbank.ndp.kernel.model;

import com.novbank.ndp.kernel.exception.MalformedRdfException;
import com.novbank.ndp.kernel.exception.PathNotFoundRuntimeException;
import com.novbank.ndp.kernel.exception.RepositoryRuntimeException;
import com.novbank.ndp.kernel.rdfsupport.RDFStream;
import com.novbank.ndp.kernel.util.jcr.JcrTypesUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.jooq.lambda.Seq;
import org.slf4j.Logger;

import javax.jcr.*;
import javax.jcr.version.Version;
import javax.jcr.version.VersionHistory;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

import static com.novbank.ndp.kernel.util.jcr.JcrPropertyFunctions.isFrozen;
import static com.novbank.ndp.kernel.util.jcr.JcrPropertyFunctions.property2values;
import static com.novbank.ndp.kernel.util.jcr.JcrPropertyFunctions.value2string;
import static com.novbank.ndp.kernel.vocabulary.Vocabularies.JCR;
import static com.novbank.ndp.kernel.vocabulary.Vocabularies.MIX;

/**
 * Created by CaoKe on 2015/5/12.
 */
public interface Resource {
    /**
     * @return The JCR node that backs this object.
     */
    Node getNode();

    /**
     * @return The Logger of The Class
     */
    Logger getLogger();

    /**
     * Get the path to the JCR node
     * @return path
     */
    default String getPath() {
        try {
            return getNode().getPath();
        } catch (final RepositoryException e) {
            throw new RepositoryRuntimeException(e);
        }
    }

    /**
     * Get the children of this resource
     * @return iterator
     */
    Iterator<Resource> getChildren();

    /**
     * Get the container of this resource
     * @return the container of this resource
     */
    Resource getContainer();

    /**
     * Get the child of this resource at the given path
     * @param relPath the given path
     * @return the child of this resource
     */
    Resource getChild(String relPath);

    /**
     * Does this resource have a property
     * @param relPath the given path
     * @return the boolean value whether the resource has a property
     */
    default boolean hasProperty(String relPath) {
        try {
            return getNode().hasProperty(relPath);
        } catch (final RepositoryException e) {
            throw new RepositoryRuntimeException(e);
        }
    }

    /**
     * Retrieve the given property value for this resource
     * @param relPath the given path
     * @return the property
     */
    default Property getProperty(String relPath) {
        try {
            return getNode().getProperty(relPath);
        } catch (final RepositoryException e) {
            throw new RepositoryRuntimeException(e);
        }
    }

    /**
     * Set the given property value for this resource as a URI, without translating any URIs that
     * appear to be references to repository resources.  Using untranslated URIs to refer to
     * repository resources will disable referential integrity checking, but also allows referring
     * to resources that do not exist, have been deleted, etc.
     *
     * @param relPath the given path
     * @param value the URI value
     */
    default void setURIProperty(String relPath, URI value){
        try {
            getNode().setProperty(relPath, value.toString(), PropertyType.URI);
        } catch (final RepositoryException e) {
            throw new RepositoryRuntimeException(e);
        }
    }

    /**
     * Delete this resource, and any inbound references to it
     */
    void delete();

    /**
     * Get the date this dataStream was created
     * @return created date
     */
    default Date getCreatedDate() {
        try {
            if (hasProperty(JCR.created.abbr())) {
                return new Date(getProperty(JCR.created.abbr()).getDate().getTimeInMillis());
            }
        } catch (final PathNotFoundException e) {
            throw new PathNotFoundRuntimeException(e);
        } catch (final RepositoryException e) {
            throw new RepositoryRuntimeException(e);
        }
        getLogger().debug("Node {} does not have a createdDate", getNode());
        return null;
    }

    /**
     * Get the date this datastream was last modified
     * @return last modified date
     */
    default Date getLastModifiedDate() {
        try {
            if (hasProperty(JCR.lastModified.abbr())) {
                return new Date(getProperty(JCR.lastModified.abbr()).getDate().getTimeInMillis());
            }
        } catch (final PathNotFoundException e) {
            throw new PathNotFoundRuntimeException(e);
        } catch (final RepositoryException e) {
            throw new RepositoryRuntimeException(e);
        }
        getLogger().debug("Could not get last modified date property for node {}", getNode());
        final Date createdDate = getCreatedDate();
        if (createdDate != null) {
            getLogger().trace("Using created date for last modified date for node {}", getNode());
            return createdDate;
        }
        return null;
    }

    /**
     * Check if this object uses a given mixin
     * @param type the given type
     * @return a collection of mixin names
     */
    default boolean hasType(final String type) {
        try {
            if (isFrozen.test(getNode()) && hasProperty(JCR.frozenMixinTypes.abbr())) {
                return Seq.seq(property2values.apply(getProperty(JCR.frozenMixinTypes.abbr())))
                        .map(value2string).toSet().contains(type);
            }
            return getNode().isNodeType(type);
        } catch (final PathNotFoundException e) {
            throw new PathNotFoundRuntimeException(e);
        } catch (final RepositoryException e) {
            throw new RepositoryRuntimeException(e);
        }
    }

    /**
     * Get the JCR Base version for the node
     *
     * @return base version
     */
    default Version getBaseVersion(){
        try {
            return getNode().getSession().getWorkspace().getVersionManager().getBaseVersion(getPath());
        } catch (final RepositoryException e) {
            throw new RepositoryRuntimeException(e);
        }
    }

    /**
     * Get JCR VersionHistory for the node.
     *
     * @return version history
     */
    default VersionHistory getVersionHistory() {
        try {
            return getNode().getSession().getWorkspace().getVersionManager().getVersionHistory(getPath());
        } catch (final RepositoryException e) {
            throw new RepositoryRuntimeException(e);
        }
    }

    /**
     * Check if a resource was created in this session
     * @return if resource created in this session
     */
    default Boolean isNew(){ return getNode().isNew(); }


    /**
     * Construct an ETag value from the last modified date and path. JCR has a
     * mix:etag type, but it only takes into account binary properties. We
     * actually want whole-object etag data. TODO : construct and store an ETag
     * value on object modify
     *
     * @return constructed etag value
     */
    default String getEtagValue(){
        final Date lastModifiedDate = getLastModifiedDate();

        if (lastModifiedDate != null) {
            return DigestUtils.sha1Hex(getPath() + lastModifiedDate.getTime());
        }
        return "";
    }

    /**
     * Enable versioning
     */
    default void enableVersioning(){
        try {
            getNode().addMixin(MIX.versionable.abbr());
        } catch (final RepositoryException e) {
            throw new RepositoryRuntimeException(e);
        }
    }

    /**
     * Disable versioning
     */
    default void disableVersioning(){
        try {
            getNode().removeMixin(MIX.versionable.abbr());
        } catch (final RepositoryException e) {
            throw new RepositoryRuntimeException(e);
        }
    }

    /**
     * Check if a resource is versioned
     * @return whether the resource is versioned
     */
    default boolean isVersioned(){
        try {
            return getNode().isNodeType(MIX.versionable.abbr());
        } catch (final RepositoryException e) {
            throw new RepositoryRuntimeException(e);
        }
    }

    /**
     * Check if a resource is frozen (a historic version).
     * @return whether the resource is frozen
     */
    default boolean isFrozenResource() {
        return JcrTypesUtils.isFrozenNode.test(this);
    }

    /**
     * When this is a frozen node, get the ancestor that was explicitly versioned
     * @return the ancestor that was explicity versioned
     */
    Resource getVersionedAncestor();

    /**
     * Get the unfrozen equivalent of a frozen versioned node
     * @return the unfrozen equivalent of a frozen versioned node
     */
    Resource getUnfrozenResource();

    /**
     * Get the node for this object at the version provided.
     * @param label the label
     * @return the node for this object at the version provided
     */
    default Node getNodeVersion(String label){
        try {
            final Session session = getNode().getSession();
            final Node n = getFrozenNode(label);
            if (n != null) return n;
            if (isVersioned()) {
                final VersionHistory hist =
                        session.getWorkspace().getVersionManager().getVersionHistory(getPath());
                if (hist.hasVersionLabel(label)) {
                    getLogger().debug("Found version for {} by label {}.", this, label);
                    return hist.getVersionByLabel(label).getFrozenNode();
                }
            }
            getLogger().warn("Unknown version {} with label or uuid {}!", this, label);
            return null;
        } catch (final RepositoryException e) {
            throw new RepositoryRuntimeException(e);
        }
    }

    default Node getFrozenNode(final String label) throws RepositoryException {
        try {
            final Session session = getNode().getSession();
            final Node frozenNode = session.getNodeByIdentifier(label);
            final String baseUUID = getNode().getIdentifier();
            /*
             * We found a node whose identifier is the "label" for the version.  Now
             * we must do due dilligence to make sure it's a frozen node representing
             * a version of the subject node.
             */
            final Property p = frozenNode.getProperty("jcr:frozenUuid");
            if (p != null) {
                if (p.getString().equals(baseUUID)) {
                    return frozenNode;
                }
            }
            /*
             * Though a node with an id of the label was found, it wasn't the
             * node we were looking for, so fall through and look for a labeled
             * node.
             */
        } catch (final ItemNotFoundException ex) {
            /*
             * the label wasn't a uuid of a frozen node but
             * instead possibly a version label.
             */
        }
        return null;
    }

    /**
     * Return the RDF properties of this object using the provided context
     *
     * @param context the context
     * @return the rdf properties of this object using the provided context
     */
    default RDFStream getTriples(Class<? extends RDFStream> context){
        return getTriples(Collections.singleton(context));
    }

    /**
     * Return the RDF properties of this object using the provided contexts
     *
     * @param contexts the contexts
     * @return the rdf properties of this object using the provided context
     */
    default RDFStream getTriples(Class<? extends RDFStream>... contexts){
        return getTriples(Arrays.asList(contexts));
    }

    /**
     * Return the RDF properties of this object using the provided contexts
     *
     * @param contexts the contexts
     * @return the rdf properties of this object using the provided context
     */
    default RDFStream getTriples(final Iterator<Class<? extends RDFStream>> contexts){
        return getTriples(() -> contexts);
    }

    /**
     * Return the RDF properties of this object using the provided contexts
     *
     * @param contexts the contexts
     * @return the rdf properties of this object using the provided context
     */
    RDFStream getTriples(Iterable<Class<? extends RDFStream>> contexts);

    /**
     * Update the provided properties with a SPARQL Update query. The updated
     * properties may be serialized to the JCR store.
     *
     * After applying the statement, clients SHOULD check the result
     * of #getDatasetProblems, which may include problems when attempting to
     * serialize the data to JCR.
     *
     * @param sparqlUpdateStatement sparql update statement
     * @param originalTriples original triples
     * @throws MalformedRdfException if malformed rdf exception occurred
     * @throws AccessDeniedException if access denied in updating properties
     */
    void updateProperties(final String sparqlUpdateStatement, final RDFStream originalTriples) throws MalformedRdfException, AccessDeniedException;

    /**
     * Replace the properties of this object with the properties from the given model
     *
     * @param inputTriples the input triples
     * @param originalTriples the original triples
     * @throws MalformedRdfException if malformed rdf exception occurred
     */
    void replaceProperties(final RDFStream inputTriples, final RDFStream originalTriples) throws MalformedRdfException;
}
