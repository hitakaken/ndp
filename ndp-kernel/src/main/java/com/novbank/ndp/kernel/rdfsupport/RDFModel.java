package com.novbank.ndp.kernel.rdfsupport;

import com.novbank.ndp.kernel.exception.MalformedRdfException;

import javax.jcr.AccessDeniedException;
import javax.jcr.Session;
import java.util.Set;

/**
 * Created by hp on 2015/5/18.
 */
public interface RDFModel {
    RDFModelFactory getFactory();
    Set<Namespace> setNsPrefixes(Set<Namespace> namespaces);
    Set<Namespace> getNsPrefixMap();
    void register(RDFModelChangedListener listener);
    void executeUpdateRequest(String sparqlUpdateStatement)  throws MalformedRdfException, AccessDeniedException;
    RDFStream replace(Session session, RDFStream originalTriples) throws MalformedRdfException;
}
