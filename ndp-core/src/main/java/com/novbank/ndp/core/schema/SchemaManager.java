package com.novbank.ndp.core.schema;

import org.apache.avro.Schema;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Schema管理器
 * 基于Avro实现Schema的管理
 *
 * Created by hp on 2015/6/5.
 */
public interface SchemaManager {
    void addSchema(String fullName, Schema schema) throws IllegalArgumentException;

    void removeSchema(String fullName);

    Schema getSchema(String fullName);

    Schema parseSchema(String schemaString);

    Schema parseSchema(InputStream in)throws IOException;

    Schema parseSchema(File file)throws IOException;

    void addProperty(String namespaceOrEmpty, String name, Iterable<String> domain, Iterable<String> range, boolean multiple, boolean sortable,boolean labeling);

    void removeProperty(String namespaceOrEmpty, String name);

    void removePropertyFromDomain(String namespaceOrEmpty, String name, String domain);

    Iterable<Schema> getPropertiesByDomain(String domain);

    Schema getPropertyStyleSchema(String fullName);

    void parseSchema(Class pojoClass);

    void removeSchema(Class pojoClass);
}
