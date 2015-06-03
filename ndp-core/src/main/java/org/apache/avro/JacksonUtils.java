package org.apache.avro;


import org.apache.avro.AvroRuntimeException;
import org.apache.avro.JsonProperties;
import org.apache.avro.Schema;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.util.TokenBuffer;
import org.jooq.lambda.Seq;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.function.Function;

/**
 * Jackson Utilities Only For Avro
 * Reason: Avro Use Jackson 1.9, But Ndp use 2.x
 *
 * Created by hp on 2015/6/3.
 */
public class JacksonUtils {
    static final String BYTES_CHARSET = "UTF-8";
    public static final Null NULL_VALUE = new Null();
    public static class Null {
        private Null() {}
    }
    private JacksonUtils() {
    }

    public static JsonNode toJsonNode(Object datum) {
        if (datum == null) {
            return null;
        }
        try {
            TokenBuffer generator = new TokenBuffer(new ObjectMapper());
            toJson(datum, generator);
            return new ObjectMapper().readTree(generator.asParser());
        } catch (IOException e) {
            throw new AvroRuntimeException(e);
        }
    }

    @SuppressWarnings(value="unchecked")
    static void toJson(Object datum, JsonGenerator generator) throws IOException {
        if (datum == NULL_VALUE) { // null
            generator.writeNull();
        } else if (datum instanceof Map) { // record, map
            generator.writeStartObject();
            for (Map.Entry<Object,Object> entry : ((Map<Object,Object>) datum).entrySet()) {
                generator.writeFieldName(entry.getKey().toString());
                toJson(entry.getValue(), generator);
            }
            generator.writeEndObject();
        } else if (datum instanceof Collection) { // array
            generator.writeStartArray();
            for (Object element : (Collection<?>) datum) {
                toJson(element, generator);
            }
            generator.writeEndArray();
        } else if (datum instanceof byte[]) { // bytes, fixed
            generator.writeString(new String((byte[]) datum, BYTES_CHARSET));
        } else if (datum instanceof CharSequence || datum instanceof Enum<?>) { // string, enum
            generator.writeString(datum.toString());
        } else if (datum instanceof Double) { // double
            generator.writeNumber((Double) datum);
        } else if (datum instanceof Float) { // float
            generator.writeNumber((Float) datum);
        } else if (datum instanceof Long) { // long
            generator.writeNumber((Long) datum);
        } else if (datum instanceof Integer) { // int
            generator.writeNumber((Integer) datum);
        } else if (datum instanceof Boolean) { // boolean
            generator.writeBoolean((Boolean) datum);
        }
    }

    public static Object toObject(JsonNode jsonNode) {
        return toObject(jsonNode, null);
    }

    public static Object toObject(JsonNode jsonNode, Schema schema) {
        if (schema != null && schema.getType().equals(Schema.Type.UNION)) {
            return toObject(jsonNode, schema.getTypes().get(0));
        }
        if (jsonNode == null) {
            return null;
        } else if (jsonNode.isNull()) {
            return NULL_VALUE;
        } else if (jsonNode.isBoolean()) {
            return jsonNode.asBoolean();
        } else if (jsonNode.isInt()) {
            if (schema == null || schema.getType().equals(Schema.Type.INT)) {
                return jsonNode.asInt();
            } else if (schema.getType().equals(Schema.Type.LONG)) {
                return jsonNode.asLong();
            }
        } else if (jsonNode.isLong()) {
            return jsonNode.asLong();
        } else if (jsonNode.isDouble()) {
            if (schema == null || schema.getType().equals(Schema.Type.DOUBLE)) {
                return jsonNode.asDouble();
            } else if (schema.getType().equals(Schema.Type.FLOAT)) {
                return (float) jsonNode.asDouble();
            }
        } else if (jsonNode.isTextual()) {
            if (schema == null || schema.getType().equals(Schema.Type.STRING) ||
                    schema.getType().equals(Schema.Type.ENUM)) {
                return jsonNode.asText();
            } else if (schema.getType().equals(Schema.Type.BYTES)) {
                try {
                    return jsonNode.getTextValue().getBytes(BYTES_CHARSET);
                } catch (UnsupportedEncodingException e) {
                    throw new AvroRuntimeException(e);
                }
            }
        } else if (jsonNode.isArray()) {
            List l = new ArrayList();
            for (JsonNode node : jsonNode) {
                l.add(toObject(node, schema == null ? null : schema.getElementType()));
            }
            return l;
        } else if (jsonNode.isObject()) {
            Map m = new LinkedHashMap();
            for (Iterator<String> it = jsonNode.getFieldNames(); it.hasNext(); ) {
                String key = it.next();
                Schema s = null;
                if (schema == null) {
                    s = null;
                } else if (schema.getType().equals(Schema.Type.MAP)) {
                    s = schema.getValueType();
                } else if (schema.getType().equals(Schema.Type.RECORD)) {
                    s = schema.getField(key).schema();
                }
                Object value = toObject(jsonNode.get(key), s);
                m.put(key, value);
            }
            return m;
        }
        return null;
    }

    public static class ArrayNodeList<T> implements List<T>{
        private final ArrayNode array;
        private final Function<T,JsonNode> forward;
        private final Function<JsonNode,T> backward;

        public ArrayNodeList(final ArrayNode array, final Function<T, JsonNode> forward, final Function<JsonNode, T> backward) {
            this.array = array;
            this.forward = forward;
            this.backward = backward;
        }

        @Override
        public int size() {
            return array.size();
        }

        @Override
        public boolean isEmpty() {
            return array.size() == 0;
        }

        @Override
        public boolean contains(Object o) {
            return indexOf(o) > -1;
        }

        @Override
        public Iterator<T> iterator() {
            return Seq.seq(array.getElements()).map(backward).iterator();
        }

        @Override
        public Object[] toArray() {
            return Seq.seq(array.getElements()).map(backward).toArray();
        }

        @Override
        public <T1> T1[] toArray(T1[] a) {
            return Seq.seq(array.getElements()).map(backward).toList().toArray(a);
        }

        @Override
        public boolean add(T t) {
            array.add(forward.apply(t));
            return true;
        }

        @Override
        public boolean remove(Object o) {
            int index = indexOf(o);
            while(index > -1){
                remove(index);
                index = indexOf(o);
            }
            return true;
        }

        @Override
        public boolean containsAll(Collection<?> c) {

            return true;
        }

        @Override
        public boolean addAll(Collection<? extends T> c) {
            array.addAll(Seq.toList(c.stream().map(forward)));
            return true;
        }

        @Override
        public boolean addAll(int index, Collection<? extends T> c) {

            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public T get(int index) {
            return null;
        }

        @Override
        public T set(int index, T element) {
            return null;
        }

        @Override
        public void add(int index, T element) {

        }

        @Override
        public T remove(int index) {
            return backward.apply(array.remove(index));
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public ListIterator<T> listIterator() {
            return null;
        }

        @Override
        public ListIterator<T> listIterator(int index) {
            return null;
        }

        @Override
        public List<T> subList(int fromIndex, int toIndex) {
            return null;
        }
    }
}
