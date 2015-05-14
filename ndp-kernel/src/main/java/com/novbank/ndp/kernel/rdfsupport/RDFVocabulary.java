package com.novbank.ndp.kernel.rdfsupport;

import com.google.common.collect.Sets;

import java.util.*;

/**
 * Created by CaoKe on 2015/5/12.
 */
public class RDFVocabulary {
    public Namespace NS;
    protected Set<String> classNames;
    protected Set<String> propertyNames;
    protected Set<String> resourceNames;
    protected Map<String,RDFResource> classMap;
    protected Map<String,RDFProperty> propertyMap;
    protected Map<String,RDFResource> resourceMap;
    protected boolean locked = false;

    public RDFVocabulary(String nameSpace, String prefix){
        this.NS = new Namespace(nameSpace,prefix);
        classNames = new LinkedHashSet<>();
        propertyNames = new LinkedHashSet<>();
        resourceNames = new LinkedHashSet<>();
        classMap = new LinkedHashMap<>();
        propertyMap = new LinkedHashMap<>();
        resourceMap = new LinkedHashMap<>();
    }

    public RDFVocabulary(String nameSpace, String prefix, Set<String> classNames, Set<String> propertyNames) {
        this(nameSpace,prefix);
        for(String className : classNames)
            addClass(className);
        for(String propertyName : propertyNames)
            addProperty(propertyName);

    }

    public RDFVocabulary(String nameSpace, String prefix, String[] classNames, String[] propertyNames) {
        this(nameSpace,prefix, Sets.newHashSet(classNames), Sets.newHashSet(propertyNames));
    }

    public String getNameSpace() {
        return NS.getNamespace();
    }

    public String getPrefix() {
        return NS.getPrefix();
    }

    public Set<String> classNames() {
        return classNames;
    }

    public Set<String> propertyNames() {
        return propertyNames;
    }

    public Set<String> resourceNames() {
        return resourceNames;
    }

    public Collection<RDFResource> classes() {
        return classMap.values();
    }

    public Collection<RDFProperty> properties() {
        return propertyMap.values();
    }

    public Collection<RDFResource> resources() {
        return resourceMap.values();
    }

    public RDFResource getClass(String resourceName) {
        return classMap.containsKey(resourceName)?classMap.get(resourceName):null;
    }

    public RDFProperty getProperty(String propertyName) {
        return propertyMap.containsKey(propertyName)?propertyMap.get(propertyName):null;
    }

    public RDFResource getResource(String resourceName) {
        return resourceMap.containsKey(resourceName)?resourceMap.get(resourceName):null;
    }

    public RDFResource addClass(String resourceName){
        if(locked) return getClass(resourceName);
        RDFResource resource = resource(resourceName);
        classNames.add(resourceName);
        classMap.put(resourceName, resource);
        addResource(resourceName,resource);
        return resource;
    }

    public RDFProperty addProperty(String propertyName){
        if(locked) return getProperty(propertyName);
        RDFProperty property = property(propertyName);
        propertyNames.add(propertyName);
        propertyMap.put(propertyName, property);
        addResource(propertyName, property);
        return property;
    }



    public RDFResource addResource(String resourceName, RDFResource resource){
        if(locked) return getResource(resourceName);
        resource = resource!=null?resource:resource(resourceName);
        resourceNames.add(resourceName);
        resourceMap.put(resourceName, resource);
        return resource;
    }

    public RDFResource addResource(String resourceName){
        return addResource(resourceName,null);
    }

    public RDFVocabulary lock(){
        locked = true;
        return this;
    }

    public RDFVocabulary unlock(){
        locked = false;
        return this;
    }

    private RDFResource resource(String resourceName) {
        return new RDFResource(NS,resourceName);
    }

    private RDFProperty property(String propertyName) {
        return new RDFProperty(NS,propertyName);
    }
}
