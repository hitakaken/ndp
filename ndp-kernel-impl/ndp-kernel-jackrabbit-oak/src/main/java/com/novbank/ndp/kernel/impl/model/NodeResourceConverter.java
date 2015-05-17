package com.novbank.ndp.kernel.impl.model;

import com.novbank.ndp.kernel.model.Resource;
import com.novbank.ndp.kernel.util.convert.AbstractConverter;

import javax.jcr.Node;

/**
 * Created by CaoKe on 2015/5/17.
 */
public class NodeResourceConverter extends AbstractConverter<Node, Resource> {
    public static final NodeResourceConverter nodeConverter = new NodeResourceConverter();

    @Override
    protected Resource doForward(Node from) {
        final Resource resource;

        if (NonRDFSourceDescriptionImpl.hasMixin(node)) {
            resource = new NonRDFSourceDescriptionImpl(node);
        } else if (BinaryImpl.hasMixin(node)) {
            resource = new BinaryImpl(node);
        } else if (TombstoneImpl.hasMixin(node)) {
            resource = new TombstoneImpl(node);
        } else {
            resource = new ContainerImpl(node);
        }

        return resource;
    }

    @Override
    protected Node doBackward(Resource to) {
        return to.getNode();
    }
}
