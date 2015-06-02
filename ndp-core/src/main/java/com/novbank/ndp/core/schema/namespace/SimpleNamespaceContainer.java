package com.novbank.ndp.core.schema.namespace;

import com.novbank.ndp.core.annotation.NameSpace;
import com.novbank.ndp.core.annotation.NameSpaces;

import java.util.function.Function;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by hp on 2015/6/2.
 */
public class SimpleNamespaceContainer implements NamespaceContainer {

    @Override
    public <V> Namespace getOrCreateNamespace(V object, Function<V, String> getSpaceFunction, Function<V, NameSpace> getAnnotationFunction) {
        checkNotNull(object);
        checkNotNull(getSpaceFunction);
        checkNotNull(getAnnotationFunction);
        String space = getSpaceFunction.apply(object);
        NameSpace annotation = getAnnotationFunction.apply(object);
        return null;
    }

    @Override
    public boolean existsNamespace(String space) {
        return false;
    }

    @Override
    public boolean existsNamespace(NameSpace namespace) {
        return false;
    }

    @Override
    public Namespace getNamespace(String space) {
        return null;
    }

    @Override
    public Namespace getNamespace(NameSpace namespace) {
        return null;
    }

    @Override
    public Namespace getNamespaceByUrl(String url) {
        return null;
    }

    @Override
    public Namespace getNamespaceByPrefix(String prefix) {
        return null;
    }

    @Override
    public NamespaceContainer addNamespaces(String space, NameSpaces nameSpaces) {
        return null;
    }

    @Override
    public NamespaceContainer addNamespace(String space, String url, String prefix) {
        return null;
    }

    @Override
    public NamespaceContainer addNamespace(String space, NameSpace namespace) {
        return null;
    }

    @Override
    public NamespaceContainer addNamespace(Namespace namespace) {
        return null;
    }
}
