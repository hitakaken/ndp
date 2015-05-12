package com.novbank.ndp.kernel.concept;

import com.google.common.collect.Sets;
import com.novbank.ndp.kernel.util.RDFNodeUtils;

import java.util.*;

/**
 * Created by hp on 2015/5/12.
 */
public class RDFVocabulary {
    public String namespace;
    public String prefix;
    protected Set<String> classNames;
    protected Set<String> propertyNames;
    protected Set<String> resourceNames;
    protected Map<String,RDFResource> classMap;
    protected Map<String,RDFProperty> propertyMap;
    protected Map<String,RDFResource> resourceMap;

    public RDFVocabulary(String nameSpace, String prefix){
        this.namespace = nameSpace;
        this.prefix = prefix;
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
        return namespace;
    }

    public String getPrefix() {
        return prefix;
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

    public RDFResource rdfClass(String resourceName) {
        return classMap.containsKey(resourceName)?classMap.get(resourceName):null;
    }

    public RDFProperty property(String propertyName) {
        return propertyMap.containsKey(propertyName)?propertyMap.get(propertyName):null;
    }

    public RDFResource resource(String resourceName) {
        return resourceMap.containsKey(resourceName)?resourceMap.get(resourceName):null;
    }

    public RDFResource addClass(String resourceName){
        RDFResource resource = RDFNodeUtils.resource(namespace, resourceName);
        classNames.add(resourceName);
        classMap.put(resourceName, resource);
        addResource(resourceName,resource);
        return resource;
    }

    public RDFProperty addProperty(String propertyName){
        RDFProperty property = RDFNodeUtils.property(namespace, propertyName);
        propertyNames.add(propertyName);
        propertyMap.put(propertyName, property);
        addResource(propertyName, property);
        return property;
    }

    public RDFResource addResource(String resourceName, RDFResource resource){
        resource = resource!=null?resource:RDFNodeUtils.resource(namespace, resourceName);
        resourceNames.add(resourceName);
        resourceMap.put(resourceName, resource);
        return resource;
    }

    public RDFResource addResource(String resourceName){
        return addResource(resourceName,null);
    }
}
