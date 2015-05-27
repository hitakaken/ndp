package com.novbank.ndp.kernel.core.metadata.schema;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by hp on 2015/5/27.
 */
public class Namespace implements Serializable{
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
}
