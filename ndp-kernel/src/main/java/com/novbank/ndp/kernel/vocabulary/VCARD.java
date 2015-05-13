package com.novbank.ndp.kernel.vocabulary;

import com.novbank.ndp.kernel.concept.RDFVocabulary;

/**
 * Created by CaoKe on 2015/5/13.
 */
public class VCARD extends RDFVocabulary {
    public static final String NAMESPACE = "http://www.w3.org/2001/vcard-rdf/3.0#";
    public static final String PREFIX = "vcard";
    public static final VCARD INSTANCE = new VCARD();

    public VCARD() {
        super(NAMESPACE,PREFIX);
    }
}
