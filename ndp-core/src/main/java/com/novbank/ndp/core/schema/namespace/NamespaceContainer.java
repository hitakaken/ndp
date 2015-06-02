package com.novbank.ndp.core.schema.namespace;

import com.novbank.ndp.core.annotation.NameSpace;
import com.novbank.ndp.core.annotation.NameSpaces;

import java.util.function.Function;

/**
 * Created by hp on 2015/6/2.
 */
public interface NamespaceContainer {
    <V> Namespace getOrCreateNamespace(V object, Function<V,String> getSpaceFunction, Function<V,NameSpace> getAnnotationFunction);

    boolean existsNamespace(String space);

    boolean existsNamespace(NameSpace namespace);

    Namespace getNamespace(String space);

    Namespace getNamespace(NameSpace namespace);

    Namespace getNamespaceByUrl(String url);

    Namespace getNamespaceByPrefix(String prefix);

    NamespaceContainer addNamespaces(String space, NameSpaces nameSpaces);

    NamespaceContainer addNamespace(String space, String url, String prefix);

    NamespaceContainer addNamespace(String space, NameSpace namespace);

    NamespaceContainer addNamespace(Namespace namespace);
}
