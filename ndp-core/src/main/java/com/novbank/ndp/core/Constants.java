package com.novbank.ndp.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hp on 2015/6/1.
 */
public class Constants {
    /**
     * Java 保留词
     */
    public static final String[] JAVA_KEYWORDS = {
            "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const",
            "continue", "default", "do", "double", "else", "extends", "false", "final", "finally",
            "float", "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long",
            "native", "new", "null", "package", "private", "protected", "public",  "return", "short", "static",
            "strictfp", "super", "switch", "synchronized", "this", "throw", "throws", "transient", "true", "try",
            "void", "volatile", "while" };
    public static boolean isJavaKeyword(String keyword) {
        return (Arrays.binarySearch(JAVA_KEYWORDS, keyword) >= 0);
    }

    private static final Set<String> SCHEMA_RESERVED = new HashSet<String>();
    static {
        Collections.addAll(SCHEMA_RESERVED,
                "doc", "fields", "items", "name", "namespace",
                "size", "symbols", "values", "type", "aliases");
    }

    private static final Set<String> MESSAGE_RESERVED = new HashSet<String>();
    static {
        Collections.addAll(MESSAGE_RESERVED,
                "doc", "response","request", "errors", "one-way");
    }

    private static final Set<String> PROTOCOL_RESERVED = new HashSet<String>();
    static {
        Collections.addAll(PROTOCOL_RESERVED,
                "namespace", "protocol", "doc",
                "messages", "types", "errors");
    }

    private static final Set<String> FIELD_RESERVED = new HashSet<String>();
    static {
        Collections.addAll(FIELD_RESERVED,
                "default","doc","name","order","type","aliases");
    }

    public static final String EMPTY_PROPERTY = "p[empty]";

}
