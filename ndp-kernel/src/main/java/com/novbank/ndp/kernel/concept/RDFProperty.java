package com.novbank.ndp.kernel.concept;

import com.hp.hpl.jena.rdf.model.Property;
import org.openrdf.model.URI;

/**
 * Created by hp on 2015/5/12.
 */
public class RDFProperty extends RDFResource<Property>{
    public RDFProperty(Property resource, URI uri, String nameSpace, String localName) {
        super(resource, uri, nameSpace, localName);
    }

    @Override
    public boolean isProperty() {
        return true;
    }
}
