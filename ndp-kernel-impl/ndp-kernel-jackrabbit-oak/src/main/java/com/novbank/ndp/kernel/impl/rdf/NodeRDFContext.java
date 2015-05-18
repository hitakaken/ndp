package com.novbank.ndp.kernel.impl.rdf;

import com.novbank.ndp.kernel.impl.service.convert.NodeResourceConverter;
import com.novbank.ndp.kernel.model.Resource;
import com.novbank.ndp.kernel.rdfsupport.RDFModelFactory;
import com.novbank.ndp.kernel.rdfsupport.RDFResource;
import com.novbank.ndp.kernel.rdfsupport.RDFStream;
import com.novbank.ndp.kernel.rdfsupport.RDFTriple;
import com.novbank.ndp.kernel.util.convert.Converter;

import javax.jcr.Node;

/**
 * {@link RDFStream} that holds contexts related to a specific {@link Node}.
 *
 * Created by hp on 2015/5/18.
 */
public class NodeRDFContext extends RDFStream {
    private final Resource resource;

    private final RDFModelFactory factory;

    public NodeRDFContext(Resource resource, RDFModelFactory factory) {
        super();
        this.resource = resource;
        this.factory = factory;
    }

    /**
     * @return The {@link Node} in question
     */
    public Resource resource() {
        return resource;
    }

    /**
     * @return local {@link com.novbank.ndp.kernel.rdfsupport.RDFModelFactory}
     */
    public RDFModelFactory factory() {
        return factory;
    }

    public Converter<Node, RDFResource> nodeConverter() {
        return NodeResourceConverter.nodeToResource(factory);
    }


}
