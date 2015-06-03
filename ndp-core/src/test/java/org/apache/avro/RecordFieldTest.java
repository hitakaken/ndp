package org.apache.avro;

import org.codehaus.jackson.node.ArrayNode;
import org.jooq.lambda.Seq;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by hp on 2015/6/3.
 */
public class RecordFieldTest {

    @Test
    public void testGetValidators() throws Exception {
        ArrayNode array = Schema.MAPPER.createArrayNode();
        array.add("13241");
        array.add("131");
        array.add("132412");

    }
}