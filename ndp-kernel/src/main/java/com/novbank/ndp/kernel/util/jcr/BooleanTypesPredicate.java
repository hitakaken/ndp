package com.novbank.ndp.kernel.util.jcr;


import org.jooq.lambda.Seq;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import java.util.*;
import java.util.function.Predicate;

import static java.util.stream.StreamSupport.*;
import static com.google.common.base.Throwables.propagate;
import static com.novbank.ndp.kernel.util.jcr.JcrPropertyFunctions.*;
import static com.novbank.ndp.kernel.vocabulary.Vocabularies.*;

/**
 * Created by CaoKe on 2015/5/16.
 */
public abstract class BooleanTypesPredicate implements Predicate<Node> {
    protected final Collection<String> nodeTypes;
    /**
     * Base constructor for function peforming boolean ops on matched node types.
     * @param types the types
     */
    public BooleanTypesPredicate(final String... types) {
        nodeTypes = Arrays.asList(types);
    }

    @Override
    public boolean test(final Node input) {
        if (input == null) {
            throw new IllegalArgumentException(
                    "null node passed to" + getClass().getName()
            );
        }
        int matched = 0;
        try {
            if (isFrozen.test(input) && input.hasProperty(JCR.frozenMixinTypes.abbr()))
                matched += Seq.seq(property2values.apply(input.getProperty(JCR.frozenMixinTypes.abbr())))
                        .map(value2string).filter(nodeTypes::contains).count();
             else {
                for (final String nodeType : nodeTypes)
                    if (input.isNodeType(nodeType)) matched++;
            }
        } catch (RepositoryException e) {
            throw propagate(e);
        }
        return test(matched);
    }

    protected abstract boolean test(final int matched);
}
