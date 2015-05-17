package com.novbank.ndp.kernel.util.jcr;

import com.google.common.collect.Iterators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.*;
import javax.jcr.nodetype.NodeType;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Throwables.propagate;
import static com.novbank.ndp.kernel.vocabulary.Vocabularies.*;

/**
 * Created by CaoKe on 2015/5/16.
 */
public class JcrPropertyFunctions {
    private static final Logger LOGGER = LoggerFactory.getLogger(JcrPropertyFunctions.class);

    private JcrPropertyFunctions() {
    }

    /**
     * Translates a {@link javax.jcr.nodetype.NodeType} to its {@link String} name.
     */
    public static Function<NodeType, String> nodetype2name = t -> {
        checkNotNull(t, "null has no name!");
        return t.getName(); };

    /**
     * Translates a JCR {@link javax.jcr.Value} to its {@link String} expression.
     */
    public static Function<Value, String> value2string = v -> {
        try {
            checkNotNull(v, "null has no appropriate "
                    + "String representation!");
            return v.getString();
        } catch (final RepositoryException e) {
            throw propagate(e);
        }
    };

    /**
     * Constructs an {@link java.util.List} of {@link javax.jcr.Value}s from any
     * {@link javax.jcr.Property}, multi-valued or not.
     */
    public static Function<Property, Iterator<Value>> property2values = p -> {
        try {
            if (p.isMultiple()) {
                LOGGER.debug("Found multi-valued property: {}", p);
                return Iterators.forArray(p.getValues());
            }
            LOGGER.debug("Found single-valued property: {}", p);
            return Iterators.forArray(p.getValue());
        } catch (final Exception e) {
            throw propagate(e);
        }
    };

    /**
     * Check if a JCR property is a multivalued property or not
     */
    public static Predicate<Property> isMultipleValuedProperty = p -> {
        checkNotNull(p, "null is neither multiple nor not multiple!");
        try {
            return p.isMultiple();
        } catch (final RepositoryException e) {
            throw propagate(e);
        }
    };

    /**
     * Check if a JCR property is a binary jcr:data property
     */
    public static Predicate<Property> isBinaryContentProperty = p -> {
        checkNotNull(p, "null is neither binary nor not binary!");
        try {
            return p.getType() == PropertyType.BINARY && p.getName().equals(JCR.data.abbr());
        } catch (final RepositoryException e) {
            throw propagate(e);
        }
    };

    /**
     * Predicate for determining whether this {@link javax.jcr.Node} is a frozen node
     * (a part of the system version history).
     */
    public static Predicate<Node> isFrozen = node -> {
        checkNotNull(node, "null cannot be a Frozen node!");
        try {
            return node.isNodeType(NT.frozenNode.abbr());
        } catch (final RepositoryException e) {
            throw propagate(e);
        }
    };
}
