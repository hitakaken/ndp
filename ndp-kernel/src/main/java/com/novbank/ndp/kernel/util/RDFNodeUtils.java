package com.novbank.ndp.kernel.util;

import com.novbank.ndp.kernel.mixin.RDFProperty;
import com.novbank.ndp.kernel.mixin.RDFResource;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.novbank.ndp.kernel.mixin.RDFValue;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.ValueFactoryImpl;

import java.util.Map;

/**
 * Created by CaoKe on 2015/5/12.
 */
public class RDFNodeUtils {
    private static ValueFactory factory = ValueFactoryImpl.getInstance();

    public static RDFResource resource(String namespace,String localName){
        return new RDFResource<>(createJenaResource(namespace, localName), createSesameURI(namespace, localName),namespace,localName);
    }

    public static RDFProperty property(String namespace,String localName){
        return new RDFProperty(createJenaProperty(namespace, localName), createSesameURI(namespace, localName),namespace,localName);
    }

    public static RDFResource resource(Resource resource){
        return new RDFResource<>(resource, createSesameURI(resource.getNameSpace(), resource.getLocalName()),resource.getNameSpace(),resource.getLocalName());
    }

    public static RDFResource property(Property property){
        return new RDFProperty(property, createSesameURI(property.getNameSpace(), property.getLocalName()),property.getNameSpace(),property.getLocalName());
    }

    public static RDFResource resource(URI uri){
        return new RDFResource<>(createJenaResource(uri.getNamespace(), uri.getLocalName()),uri,uri.getNamespace(),uri.getLocalName());
    }

    public static RDFResource property(URI uri){
        return new RDFProperty(createJenaProperty(uri.getNamespace(), uri.getLocalName()),uri,uri.getNamespace(),uri.getLocalName());
    }

    public static RDFValue value(){
        return null;
    }

    public static Resource createJenaResource(String namespace, String localName){
        return ResourceFactory.createProperty(namespace,localName);
    }

    public static Property createJenaProperty(String namespace, String localName){
        return ResourceFactory.createProperty(namespace,localName);
    }

    public static URI createSesameURI(String namespace, String localName){
        return factory.createURI(namespace, localName);
    }

    public static Value createSesameValue(){
        return null;
    }

    public static String abbreviation(RDFResource resource, Map<String,String> namespaces){
        return namespaces.containsKey(resource.getNameSpace())?
                namespaces.get(resource.getNameSpace()) + ":" + resource.getLocalName() : resource.toString();
    }
}
