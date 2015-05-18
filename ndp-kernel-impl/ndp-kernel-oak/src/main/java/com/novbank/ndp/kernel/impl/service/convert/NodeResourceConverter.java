package com.novbank.ndp.kernel.impl.service.convert;

import com.novbank.ndp.kernel.impl.model.BinaryImpl;
import com.novbank.ndp.kernel.impl.model.ContainerImpl;
import com.novbank.ndp.kernel.impl.model.NonRDFSourceDescriptionImpl;
import com.novbank.ndp.kernel.impl.model.TombstoneImpl;
import com.novbank.ndp.kernel.model.Resource;
import com.novbank.ndp.kernel.rdfsupport.RDFResource;
import com.novbank.ndp.kernel.util.convert.AbstractConverter;
import com.novbank.ndp.kernel.util.convert.Converter;

import javax.jcr.Node;

/**
 * Created by CaoKe on 2015/5/17.
 */
public class NodeResourceConverter extends AbstractConverter<Node, Resource> {
    public static final NodeResourceConverter nodeConverter = new NodeResourceConverter();

    /**
     * Get a converter that can transform a Node to a Resource
     * @param c the given node
     * @return the converter that can transform a node to resource
     */
    public static Converter<Node, RDFResource> nodeToResource(final Converter<RDFResource, Resource> c) {
        return nodeConverter.andThen(c.reverse());
    }

    @Override
    protected Resource doForward(Node from) {
        final Resource resource;

        if (NonRDFSourceDescriptionImpl.hasMixin(from)) {
            resource = new NonRDFSourceDescriptionImpl(from);
        } else if (BinaryImpl.hasMixin(from)) {
            resource = new BinaryImpl(from);
        } else if (TombstoneImpl.hasMixin(from)) {
            resource = new TombstoneImpl(from);
        } else {
            resource = new ContainerImpl(from);
        }

        return resource;
    }

    @Override
    protected Node doBackward(Resource to) {
        return to.getNode();
    }
}
