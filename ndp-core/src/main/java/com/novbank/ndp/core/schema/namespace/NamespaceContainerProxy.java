package com.novbank.ndp.core.schema.namespace;

import com.novbank.ndp.core.annotation.NameSpace;
import com.novbank.ndp.core.annotation.NameSpaces;

import java.util.function.Function;

/**
 * Created by hp on 2015/6/2.
 */
public interface NamespaceContainerProxy extends NamespaceContainer {
    NamespaceContainer underlyingNamespaceContainer();

    @Override
    default <V> Namespace getOrCreateNamespace(V object, Function<V, String> getSpaceFunction, Function<V, NameSpace> getAnnotationFunction){
        return underlyingNamespaceContainer().getOrCreateNamespace(object,getSpaceFunction,getAnnotationFunction);
    }

    @Override
    default boolean existsNamespace(String space){
        return underlyingNamespaceContainer().existsNamespace(space);
    }

    @Override
    default boolean existsNamespace(NameSpace namespace){
        return underlyingNamespaceContainer().existsNamespace(namespace);
    }

    @Override
    default Namespace getNamespace(String space){
        return underlyingNamespaceContainer().getNamespace(space);
    }

    @Override
    default Namespace getNamespace(NameSpace namespace){
        return underlyingNamespaceContainer().getNamespace(namespace);
    }

    @Override
    default Namespace getNamespaceByUrl(String url){
        return underlyingNamespaceContainer().getNamespaceByUrl(url);
    }

    @Override
    default Namespace getNamespaceByPrefix(String prefix){
        return underlyingNamespaceContainer().getNamespaceByPrefix(prefix);
    }

    @Override
    default NamespaceContainer addNamespaces(String space, NameSpaces nameSpaces){
        underlyingNamespaceContainer().addNamespaces(space,nameSpaces);
        return this;
    }

    @Override
    default NamespaceContainer addNamespace(String space, String url, String prefix){
        underlyingNamespaceContainer().addNamespace(space, url, prefix);
        return this;
    }

    @Override
    default NamespaceContainer addNamespace(String space, NameSpace namespace){
        underlyingNamespaceContainer().addNamespace(space, namespace);
        return this;
    }

    @Override
    default NamespaceContainer addNamespace(Namespace namespace){
        underlyingNamespaceContainer().addNamespace(namespace);
        return this;
    }
}
