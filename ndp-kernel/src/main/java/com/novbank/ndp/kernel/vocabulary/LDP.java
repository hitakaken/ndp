package com.novbank.ndp.kernel.vocabulary;

import com.novbank.ndp.kernel.concept.RDFVocabulary;

/**
 * Created by hp on 2015/5/12.
 */
public class LDP extends RDFVocabulary{
    public static final String NAMESPACE = "http://www.w3.org/ns/ldp#";
    public static final String PREFIX = "ldp";
    public static final LDP INSTANCE = new LDP();

    public LDP(){
        super(NAMESPACE,PREFIX);
    }
}
