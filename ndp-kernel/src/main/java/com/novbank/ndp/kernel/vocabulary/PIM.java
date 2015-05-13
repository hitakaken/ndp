package com.novbank.ndp.kernel.vocabulary;

import com.novbank.ndp.kernel.mixin.RDFProperty;
import com.novbank.ndp.kernel.mixin.RDFVocabulary;

/**
 * PRISM Inline Markup
 *
 * Created by CaoKe on 2015/5/13.
 */
public class PIM extends RDFVocabulary{
    public static final String NAMESPACE = "http://prismstandard.org/namespaces/1.2/pim/";
    public static final String PREFIX = "pim";
    public static final PIM INSTANCE = new PIM();

    public final RDFProperty event;
    public final RDFProperty industry;
    public final RDFProperty location;
    public final RDFProperty objectTitle;
    public final RDFProperty organization;
    public final RDFProperty person;
    public final RDFProperty quote;

    public PIM() {
        super(NAMESPACE,PREFIX);
        event = addProperty("event");
        industry = addProperty("industry");
        location = addProperty("location");
        objectTitle = addProperty("objectTitle");
        organization = addProperty("organization");
        person = addProperty("person");
        quote = addProperty("quote");
    }

    public static final RDFProperty EVENT = INSTANCE.event;
    public static final RDFProperty INDUSTRY = INSTANCE.industry;
    public static final RDFProperty LOCATION = INSTANCE.location;
    public static final RDFProperty OBJECT_TITLE = INSTANCE.objectTitle;
    public static final RDFProperty ORGANIZATION = INSTANCE.organization;
    public static final RDFProperty PERSON = INSTANCE.person;
    public static final RDFProperty QUOTE = INSTANCE.quote;
}
