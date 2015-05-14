package com.novbank.ndp.kernel.vocabulary;

import com.novbank.ndp.kernel.rdfsupport.RDFVocabulary;

/**
 * Created by CaoKe on 2015/5/13.
 */
public class EVENT extends RDFVocabulary {
    public static final String NAMESPACE = "http://purl.org/NET/c4dm/event.owl#";
    public static final String PREFIX = "event";
    public static final EVENT INSTANCE = new EVENT();

    public EVENT() {
        super(NAMESPACE,PREFIX);
        lock();
    }
}
