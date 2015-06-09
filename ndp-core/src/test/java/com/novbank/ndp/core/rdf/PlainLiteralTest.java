package com.novbank.ndp.core.rdf;

import org.apache.avro.Schema;
import org.apache.avro.reflect.ReflectData;
import org.junit.Test;
import org.kitesdk.data.Dataset;
import org.kitesdk.data.DatasetDescriptor;
import org.kitesdk.data.DatasetWriter;
import org.kitesdk.data.Datasets;

import static org.junit.Assert.*;

/**
 * Created by CaoKe on 2015/6/8.
 */
public class PlainLiteralTest {
    private static final String[] names = { "toaster", "teapot", "butter dish" };

    @Test
    public void testAvro(){
        DatasetDescriptor descriptor = new DatasetDescriptor.Builder()
                .schema(PlainLiteral.class)
                .build();
        Dataset<PlainLiteral> products = Datasets.create(
                "dataset:file://d:/Workspace/data/hadoop/literal", descriptor, PlainLiteral.class);

        // Get a writer for the dataset and write some products to it
        DatasetWriter<PlainLiteral> writer = null;
        try {
            writer = products.newWriter();
            int i = 0;
            for (String name : names) {
                PlainLiteral literal = new PlainLiteral();
                literal.setValue(name);
                writer.write(literal);
            }
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

}