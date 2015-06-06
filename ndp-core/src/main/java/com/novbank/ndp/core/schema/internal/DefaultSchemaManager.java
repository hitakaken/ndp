package com.novbank.ndp.core.schema.internal;

import com.novbank.ndp.core.schema.Property;
import com.novbank.ndp.core.schema.SchemaManager;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.util.Utf8;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by CaoKe on 2015/6/5.
 */
public class DefaultSchemaManager implements SchemaManager {
    protected Map<String,Schema> schemas;

    protected Map<String, Set<? extends Property>> propertyDomainMap;

    protected Map<String, Set<? extends Property>> propertyRangeMap;

    public DefaultSchemaManager() {

    }

    @Override
    public void addSchema(String fullName, Schema schema) throws IllegalArgumentException {
        schemas.put(fullName, schema);
    }


    public Property createProperty(String namespaceOrEmpty, String name, Iterable<String> domain, Iterable<String> range, boolean multiple, boolean sortable, boolean labeling) {

        return null;
    }


}
