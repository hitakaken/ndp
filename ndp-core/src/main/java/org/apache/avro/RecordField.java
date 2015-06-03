package org.apache.avro;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.node.TextNode;
import org.jooq.lambda.Seq;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.apache.avro.AvroConstants.*;

/**
 * 扩展 Avro Field
 * display: 显示名称 / Resource Key
 * hint: 输入提示 / Resource Key
 * validators: 用于生成校验器的属性表
 *
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

    protected ArrayNode getValidatorsAsArrayNode(){
        if(!props.containsKey(FIELD_VALIDATORS_KEY))
            props.put(FIELD_VALIDATORS_KEY,Schema.MAPPER.createArrayNode());
        JsonNode node = props.get(FIELD_VALIDATORS_KEY);
        if(node.isArray())  return (ArrayNode)node;
        ArrayNode array = Schema.MAPPER.createArrayNode();
        array.add(node);
        props.put(FIELD_VALIDATORS_KEY,array);
        return  array;
    }

    public Map<String,String> toMap(JsonNode n){
        return (Map<String, String>) JacksonUtils.toObject(n, Schema.createMap(Schema.create(Schema.Type.STRING)));
    }

    public List<Map<String,String>> getValidators(){
        return Seq.seq(getValidatorsAsArrayNode()).map(this::toMap).toList();
    }

    public void addValidator(Map<String,String> validator){
        getValidatorsAsArrayNode().add(JacksonUtils.toJsonNode(validator));
    }

    public boolean removeValidator(int index){
        return getValidatorsAsArrayNode().remove(index)!=null;
    }

    public void removeAllValidators(){
        getValidatorsAsArrayNode().removeAll();
    }
}
