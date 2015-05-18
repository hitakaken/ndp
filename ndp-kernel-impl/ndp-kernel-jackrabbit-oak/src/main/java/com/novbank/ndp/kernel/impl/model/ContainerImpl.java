package com.novbank.ndp.kernel.impl.model;

import com.novbank.ndp.kernel.model.Container;
import com.novbank.ndp.kernel.model.Resource;
import com.novbank.ndp.kernel.util.jcr.JcrTypesUtils;

import javax.jcr.Node;

import static com.novbank.ndp.kernel.vocabulary.Vocabularies.*;

/**
 * Created by CaoKe on 2015/5/17.
 */
public class ContainerImpl extends ResourceImpl implements Container{
    /**
     * Construct a {@link Container} from an existing JCR Node
     *
     * @param node an existing JCR node to treat as an ndp object
     */
    public ContainerImpl(Node node) {
        super(node);
    }

    /**
     * Check if the node has a ndp:object mixin
     * @param node the given node
     * @return true if the node has the ndp object mixin
     */
    public static boolean hasMixin(final Node node) {
        return JcrTypesUtils.isContainer.test(node);
    }
}
