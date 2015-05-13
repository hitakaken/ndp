package com.novbank.ndp.kernel.mixin;

/**
 * Created by hp on 2015/5/13.
 */
public class RDFValue<J extends com.hp.hpl.jena.rdf.model.RDFNode, S extends org.openrdf.model.Value> implements RDFMixNode<J,S> {
    protected J jena;
    protected S sesame;

    public RDFValue(J jena, S sesame) {
        this.jena = jena;
        this.sesame = sesame;
    }

    @Override
    public J jena() {
        return jena;
    }

    @Override
    public S sesame() {
        return sesame;
    }

    @Override
    public String toString() {
        return sesame.stringValue();
    }
}
