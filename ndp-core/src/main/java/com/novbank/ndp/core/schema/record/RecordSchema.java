package com.novbank.ndp.core.schema.record;

import com.novbank.ndp.core.schema.namespace.NamespaceContainer;
import com.novbank.ndp.core.schema.type.RecordType;
import com.novbank.ndp.core.schema.type.RecordTypeContainer;
import org.apache.avro.Schema;
import org.apache.parquet.schema.MessageType;

/**
 * Created by CaoKe on 2015/6/1.
 */
public interface RecordSchema extends NamespaceContainer, RecordTypeContainer {
    int countClass();

    RecordType createClass(Class<?> clazz);

    RecordType createClass(Schema avroSchema);

    RecordType createClass(MessageType parquetSchema);

    RecordType createClasses(Class... classes);

    RecordType createClasses(Package... packages);

    RecordType createClasses(Schema... avroSchemas);

    RecordType createClasses(MessageType... parquetSchemas);

    void dropClass(String className);

    void dropClass(String namespace, String name);
}
