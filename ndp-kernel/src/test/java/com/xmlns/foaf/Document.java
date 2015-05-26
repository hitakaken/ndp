package com.xmlns.foaf;

import java.util.Set;

/**
 * Created by hp on 2015/5/26.
 */
public class Document {
    protected String topic;
    protected String sha1;
    protected Agent maker;

    public Document() {
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    public Agent getMaker() {
        return maker;
    }

    public void setMaker(Agent maker) {
        this.maker = maker;
    }
}
