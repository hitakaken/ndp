package com.novbank.ndp.kernel.vocabulary;

import com.novbank.ndp.kernel.mixin.RDFVocabulary;

/**
 * Created by CaoKe on 2015/5/13.
 */
public class RSS extends RDFVocabulary {
    public static final String NAMESPACE = "http://purl.org/rss/1.0/";
    public static final String PREFIX = "rss";
    public static final RSS INSTANCE = new RSS();

    public RSS() {
        super(NAMESPACE,PREFIX);
    }
}
