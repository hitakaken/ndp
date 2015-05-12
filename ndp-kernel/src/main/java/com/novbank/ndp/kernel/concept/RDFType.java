package com.novbank.ndp.kernel.concept;

import com.hp.hpl.jena.rdf.model.Resource;
import org.openrdf.model.URI;

/**
 * RDF 资源节点
 * Jena/Sesame Composite Backend
 *
 * Created by hp on 2015/5/12.
 */
public interface RDFType<T extends Resource> {
    boolean isProperty();
    T jena();
    URI sesame();
    String getNameSpace();
    String getLocalName();

    @Override
    String toString();
}
