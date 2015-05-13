package com.novbank.ndp.kernel.mixin;

import com.hp.hpl.jena.rdf.model.Property;

/**
 * Created by CaoKe on 2015/5/12.
 */
public class RDFResource<J extends com.hp.hpl.jena.rdf.model.Resource, S extends org.openrdf.model.Resource> extends RDFValue<J,S> {
    protected final String nameSpace;
    protected final String localName;
    protected final String url;

    public RDFResource(J jena, S sesame, String nameSpace, String localName) {
        super(jena,sesame);
        this.nameSpace = nameSpace;
        this.localName = localName;
        this.url = (nameSpace.endsWith("#")||nameSpace.endsWith("/")?nameSpace:"{"+nameSpace+"}") + localName;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public String getLocalName() {
        return localName;
    }

    @Override
    public String toString() {
        return url;
    }
}
