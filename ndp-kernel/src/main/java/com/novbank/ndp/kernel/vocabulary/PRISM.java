package com.novbank.ndp.kernel.vocabulary;

import com.novbank.ndp.kernel.concept.RDFVocabulary;

/**
 * Created by CaoKe on 2015/5/13.
 */
public class PRISM extends RDFVocabulary{
    public static final String NAMESPACE = "http://prismstandard.org/namespaces/1.2/basic/";
    public static final String PREFIX = "prism";
    public static final PRISM INSTANCE = new PRISM();

    public PRISM() {
        super(NAMESPACE,PREFIX);
    }
}
