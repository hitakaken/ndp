package com.novbank.ndp.core.rdf;

/**
 * Created by CaoKe on 2015/6/8.
 */
public class PlainLiteral {
    private String value;
    private String dataType;
    private String language;

    public PlainLiteral() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
