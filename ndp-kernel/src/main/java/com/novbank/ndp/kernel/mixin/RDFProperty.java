package com.novbank.ndp.kernel.mixin;

import com.hp.hpl.jena.rdf.model.Property;
import org.openrdf.model.URI;

/**
 * Created by CaoKe on 2015/5/12.
 */
public class RDFProperty extends RDFResource<Property,URI>{
    public RDFProperty(Property jena, URI sesame, String nameSpace, String localName) {
        super(jena, sesame, nameSpace, localName);
    }
}
