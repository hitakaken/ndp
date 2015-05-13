package com.novbank.ndp.kernel.mixin;

/**
 * RDF 资源节点
 * Jena/Sesame Composite Backend
 *
 * Created by CaoKe on 2015/5/12.
 */
public interface RDFMixNode<J extends com.hp.hpl.jena.rdf.model.RDFNode,S extends org.openrdf.model.Value> {
    J jena();
    S sesame();

    @Override
    String toString();
}
