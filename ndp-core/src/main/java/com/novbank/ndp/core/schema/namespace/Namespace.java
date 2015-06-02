package com.novbank.ndp.core.schema.namespace;

import com.google.common.base.Strings;
import com.novbank.ndp.core.annotation.Indexed;
import com.novbank.ndp.core.annotation.NameSpace;


/**
 * Created by hp on 2015/6/2.
 */
public class Namespace {
    @Indexed
    private String space;
    @Indexed
    private String url;
    @Indexed
    private String prefix;

    public Namespace(String space, String url, String prefix) {
        this.space = space;
        this.url = url;
        this.prefix = prefix;
    }

    public Namespace(String space, NameSpace annotation){
        this.space = space;
        this.url = annotation!=null && !Strings.isNullOrEmpty(annotation.url())? annotation.url():null;
        this.prefix = annotation!=null && !Strings.isNullOrEmpty(annotation.prefix())? annotation.prefix():null;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
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
