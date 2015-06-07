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

    public static final String EMPTY_PROPERTY = "p[empty]";

    public static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";

    public static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static final String DIGITS = "0123456789";

    public static final String FIELD_NAME = "name";
    public static final String FIELD_NAMESPACE = "namespace";
    public static final String FIELD_MULTIPLE = "multiple";
    public static final String FIELD_SORTABLE = "sortable";
    public static final String FIELD_DUPLICATABLE = "duplicatable";
    public static final String FIELD_LABELING = "labeling";
    public static final String FIELD_DOMAIN = "domain";
    public static final String FIELD_RANGE = "range";
    public static final String FIELD_LABELS = "labels";
    public static final String FIELD_PROPERTIES = "properties";
    public static final String FIELD_EXTENSION = "ext";

}
