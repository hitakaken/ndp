package com.novbank.ndp.kernel.rdfsupport;

/**
 * Created by hp on 2015/5/14.
 */
public class Namespace {
    protected String namespace;
    protected String prefix;

    public Namespace(String namespace, String prefix) {
        this.namespace = namespace;
        this.prefix = prefix;
    }

    public String getNamespace() {
        return namespace;
    }

    public String getPrefix() {
        return prefix;
    }

    @Override
    public String toString(){
        return namespace;
    }

    @Override
    public int hashCode(){
        return namespace.hashCode();
    }

    @Override
    public boolean equals(Object other){
        return other!=null && other instanceof Namespace && namespace.equals(other.toString());
    }


}
