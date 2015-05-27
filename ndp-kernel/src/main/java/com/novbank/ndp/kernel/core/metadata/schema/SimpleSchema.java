package com.novbank.ndp.kernel.core.metadata.schema;


import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.util.Map;

/**
 * Created by hp on 2015/5/27.
 */
public class SimpleSchema /*implements RecordSchema*/{
    private static final String NAMESPACE_MAP_NAME = "namespaces";
    private static final String CLASS_MAP_NAME = "classes";
    final DB mapDb;
    final Map<String,Namespace> namespaces;
    public SimpleSchema() {
        this(DBMaker.newMemoryDB().transactionDisable().closeOnJvmShutdown().make());
    }

    public SimpleSchema(DB mapDb) {
        this.mapDb = mapDb;
        namespaces = this.mapDb.createTreeMap("namespaces").counterEnable().makeOrGet();

    }

    public void initialize(){

    }


}
