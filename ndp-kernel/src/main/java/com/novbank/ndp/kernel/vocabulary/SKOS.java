package com.novbank.ndp.kernel.vocabulary;

import com.novbank.ndp.kernel.rdfsupport.RDFVocabulary;

/**
 * Created by CaoKe on 2015/5/12.
 */
public class SKOS extends RDFVocabulary{
    public static final String NAMESPACE = "http://www.w3.org/2004/02/skos/core#";
    public static final String PREFIX = "skos";
    public static final SKOS INSTANCE = new SKOS();

    private SKOS() {
        super(NAMESPACE,PREFIX);
    }
}
