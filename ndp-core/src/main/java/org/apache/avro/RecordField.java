package org.apache.avro;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.TextNode;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.apache.avro.AvroConstants.*;

/**
 * Created by hp on 2015/6/3.
 */
public class RecordField extends Schema.Field{
    public RecordField(String name, Schema schema, String doc, Object defaultValue) {
        super(name, schema, doc,  JacksonUtils.toJsonNode(defaultValue), Order.ASCENDING);
    }

    public RecordField(String name, Schema schema, String doc, Object defaultValue, Order order) {
        super(name, schema, doc, JacksonUtils.toJsonNode(defaultValue), order);
    }

    public String getDisplay(){
        return getProp(FIELD_DISPLAY_KEY);
    }

    public void setDisplay(String display){
        props.put(FIELD_DISPLAY_KEY, TextNode.valueOf(display));
    }

    public String getHint(){
        return getProp(FIELD_HINT_KEY);
    }

    public void setHint(String hint){
        props.put(FIELD_HINT_KEY, TextNode.valueOf(hint));
    }

    public List<Map> getValidators(){
        return null;
    }

    public void addValidator(Map<String,String> validator){
        if(!props.containsKey(FIELD_VALIDATORS_KEY))
            props.put(FIELD_VALIDATORS_KEY,Schema.MAPPER.createArrayNode());
        ArrayNode array = (ArrayNode) props.get(FIELD_VALIDATORS_KEY);
        //array.getElements()

    }
}
