package com.novbank.ndp.kernel.core.metadata.schema;

import com.novbank.ndp.kernel.entity.Schema;
import org.apache.parquet.schema.MessageType;

import java.util.Collection;

/**
 * Created by ken on 15-5-26.
 */
public interface RecordSchema {
    int countClass();

    String getNamespaceByPrefix(String prefix);

    String getNamespaceByUrl(String url);

    String getNamespacePrefix(String namespace);

    String getNamespacePrefixByUrl(String url);

    String getNamespaceUrl(String namespace);

    String getNamespaceUrlByPrefix(String prefix);

    RecordSchema addNamespace(String namespace, String url, String prefix);

    RecordClass createClass(Class<?> clazz);

    RecordClass createClass(Schema avroSchema);

    RecordClass createClass(MessageType parquetSchema);

    RecordClass createClasses(Class... classes);

    RecordClass createClasses(Package... packages);

    RecordClass createClasses(Schema... avroSchemas);

    RecordClass createClasses(MessageType... parquetSchemas);

    void dropClass(String className);

    void dropClass(String namespace, String name);

    boolean existsClass(String className);

    boolean existsClass(String namespace, String name);

    RecordClass getClass(String className);

    RecordClass getClass(String namespace, String name);

    RecordClass getOrCreateClass(String className);

    RecordClass getOrCreateClass(String namespace, String name);

    RecordClass getOrCreateClass(String className, RecordClass superClass);

    RecordClass getOrCreateClass(String namespace, String name, RecordClass superClass);

    Collection<RecordClass> getClasses();
}
