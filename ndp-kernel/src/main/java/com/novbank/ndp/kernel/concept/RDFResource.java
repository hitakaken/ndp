package com.novbank.ndp.kernel.concept;

import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import org.openrdf.model.URI;

/**
 * Created by hp on 2015/5/12.
 */
public class RDFResource<T extends Resource> implements RDFType<T> {
    protected final T resource;
    protected final URI uri;
    protected final String nameSpace;
    protected final String localName;
    protected final String url;

    public RDFResource(T resource, URI uri, String nameSpace, String localName) {
        this.resource = resource;
        this.uri = uri;
        this.nameSpace = nameSpace;
        this.localName = localName;
        this.url = nameSpace + localName;
    }

    @Override
    public boolean isProperty() {
        return resource instanceof Property;
    }

    @Override
    public T jena() {
        return resource;
    }

    @Override
    public URI sesame() {
        return uri;
    }

    @Override
    public String getNameSpace() {
        return nameSpace;
    }

    @Override
    public String getLocalName() {
        return localName;
    }

    @Override
    public String toString() {
        return url;
    }
}
