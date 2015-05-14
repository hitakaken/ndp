package com.novbank.ndp.kernel.rdfsupport;

/**
 * Created by hp on 2015/5/13.
 */
public class RDFValue implements RDFNode {
    protected Object value;

    public RDFValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
