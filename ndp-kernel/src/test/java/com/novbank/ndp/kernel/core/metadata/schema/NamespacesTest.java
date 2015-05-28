package com.novbank.ndp.kernel.core.metadata.schema;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ken on 15-5-28.
 */
public class NamespacesTest {

    @Test
    public void testAdd() throws Exception {
        Namespaces namespaces = new Namespaces();
        Namespace foaf = new Namespace("xmlns.foaf","http://xmlns.com/foaf/0.1/","foaf");
        Namespace rdf = new Namespace("org.w3.rdf","http://www.w3.org/1999/02/22-rdf-syntax-ns#","rdf");
        Namespace rdfs = new Namespace("org.w3.rdfs","http://www.w3.org/2000/01/rdf-schema#","rdfs");
        namespaces.add(foaf);
        namespaces.add(rdf);
        namespaces.add(rdfs);
        System.out.println(namespaces.size());
        try{
            rdf.setPrefix("rdfs");
        }catch (Exception e){
            //ignore
        }
        namespaces.remove(rdf);
        System.out.println(namespaces.size());
        rdf.setPrefix("rdfs");
        namespaces.add(rdf);
        System.out.println(namespaces.size());
        rdf.setPrefix("rdf");
        namespaces.add(rdf);
        System.out.println(namespaces.size());
        namespaces.forEach(System.out::println);
        for (Namespace namespace : namespaces)
            namespace.setPrefix(null);
        namespaces.forEach(System.out::println);
    }
}