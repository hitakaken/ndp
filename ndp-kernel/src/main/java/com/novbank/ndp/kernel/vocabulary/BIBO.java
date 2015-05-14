package com.novbank.ndp.kernel.vocabulary;

import com.novbank.ndp.kernel.rdfsupport.RDFVocabulary;

/**
 * Created by CaoKe on 2015/5/13.
 */
public class BIBO extends RDFVocabulary {
    public static final String NAMESPACE = "http://purl.org/ontology/bibo/";
    public static final String PREFIX = "bibo";
    public static final BIBO INSTANCE = new BIBO();

    public BIBO() {
        super(NAMESPACE, PREFIX);
    }
}
