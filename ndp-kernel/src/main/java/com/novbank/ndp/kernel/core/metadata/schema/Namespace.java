package com.novbank.ndp.kernel.core.metadata.schema;

import com.novbank.ndp.kernel.core.exception.PropertyVetoRuntimeException;
import com.novbank.ndp.kernel.core.record.AbstractPropertyContainer;

import java.beans.PropertyVetoException;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by hp on 2015/5/27.
 */
public class Namespace extends AbstractPropertyContainer{
    private String space;
    private String url;
    private String prefix;

    public Namespace(String space, String url, String prefix) {
        this.space = space;
        this.url = url;
        this.prefix = prefix;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) throws PropertyVetoException {
        doSetProperty("space",this.space,space,(o,v)->((Namespace)o).space = (String) v);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) throws PropertyVetoException {
        doSetProperty("url",this.url,url,(o,v)->((Namespace)o).url = (String) v);
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) throws PropertyVetoException {
        doSetProperty("prefix",this.prefix,prefix,(o,v)->((Namespace)o).prefix = (String) v);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Namespace namespace = (Namespace) o;

        return !((space != null ? !space.equals(namespace.space) : namespace.space != null));
        /*return false;
        if (url != null ? !url.equals(namespace.url) : namespace.url != null) return false;
        return !(prefix != null ? !prefix.equals(namespace.prefix) : namespace.prefix != null);*/
    }

    @Override
    public int hashCode() {
        int result = space != null ? space.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (prefix != null ? prefix.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Namespace{" +
                "space='" + space + '\'' +
                ", url='" + url + '\'' +
                ", prefix='" + prefix + '\'' +
                '}';
    }
}
