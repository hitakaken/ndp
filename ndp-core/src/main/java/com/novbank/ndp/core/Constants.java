package com.novbank.ndp.core;

import java.util.Arrays;
import java.util.Calendar;
import java.util.TimeZone;

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

    /**
     * 字符集常量
     */
    public static final String CHARACTER_LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    public static final String CHARACTER_UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String CHARACTER_DIGITS = "0123456789";
    public static final String SEPARATOR_PERIOD = ".";
    public static final String SEPARATOR_UNDERSCORE = "_";
    public static final String SEPARATOR_ABBREVIATION = ":";
    public static final String SEPARATOR_PATH = "/";
    public static final String SEPARATOR_LINE = System.lineSeparator();
    public static final String CHARACTER_VALID_COMMON = CHARACTER_LOWERCASE + CHARACTER_UPPERCASE +CHARACTER_DIGITS;
    public static final String CHARACTER_VALID_ABBREVIATION = CHARACTER_VALID_COMMON + SEPARATOR_UNDERSCORE + SEPARATOR_ABBREVIATION;
    public static final String CHARACTER_VALID_NAMESPACE = CHARACTER_VALID_COMMON + SEPARATOR_UNDERSCORE + SEPARATOR_PERIOD;

    /**
     * 字段名常量
     */
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


    public static final TimeZone GMT_TIME_ZONE = TimeZone.getTimeZone("GMT");
    public static final Calendar UNIX_EPOCH = Calendar.getInstance(GMT_TIME_ZONE);
}
