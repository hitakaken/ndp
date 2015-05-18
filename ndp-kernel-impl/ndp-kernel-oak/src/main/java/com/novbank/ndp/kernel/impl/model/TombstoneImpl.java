package com.novbank.ndp.kernel.impl.model;

import com.novbank.ndp.kernel.exception.RepositoryRuntimeException;
import com.novbank.ndp.kernel.model.Tombstone;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import static com.novbank.ndp.kernel.vocabulary.Vocabularies.*;

/**
 * Created by CaoKe on 2015/5/17.
 */
public class TombstoneImpl extends ResourceImpl implements Tombstone {
    /**
     * Construct a {@link Tombstone} from an existing JCR Node
     * @param node an existing JCR node to treat as an ndp object
     */
    public TombstoneImpl(final Node node) {
        super(node);
    }


    /**
     * Check if the node has a ndp:RDFSource mixin
     * @param node the node
     * @return true if the node has the ndp object mixin
     */
    public static boolean hasMixin(final Node node) {
        try {
            return node.isNodeType(NDP.Tombstone.abbr());
        } catch (final RepositoryException e) {
            throw new RepositoryRuntimeException(e);
        }
    }

    @Override
    public void delete() {
        try {
            node.remove();
        } catch (final RepositoryException e) {
            throw new RepositoryRuntimeException(e);
        }
    }
}
