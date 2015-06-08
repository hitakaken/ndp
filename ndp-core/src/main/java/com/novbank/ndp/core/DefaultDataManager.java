package com.novbank.ndp.core;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.novbank.ndp.core.schema.NamespaceManager;
import com.novbank.ndp.core.schema.SchemaManager;
import com.novbank.ndp.core.schema.internal.DefaultNamespaceManager;
import com.novbank.ndp.core.schema.internal.DefaultSchemaManager;

/**
 * Created by hp on 2015/6/8.
 */
public class DefaultDataManager implements DataManager{
    protected NamespaceManager namespaces;
    protected SchemaManager schemas;

    public DefaultDataManager() {
        this(new DefaultNamespaceManager(),new DefaultSchemaManager());

    }

    public DefaultDataManager(NamespaceManager namespaces, SchemaManager schemas, Module... modules) {
        this.namespaces = namespaces;
        this.schemas = schemas;
        this.extensions = new DefaultExtensionManager(this);
    }


    @Override
    public NamespaceManager namespaces() {
        return namespaces;
    }

    @Override
    public SchemaManager schemas() {
        return schemas;
    }

    @Override
    public ExtensionManager extensions() {
        return extensions;
    }


}
