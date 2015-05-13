package com.novbank.ndp.kernel.vocabulary;

import com.novbank.ndp.kernel.mixin.RDFResource;
import com.novbank.ndp.kernel.mixin.RDFVocabulary;

/**
 * PRISM Controlled Vocabulary
 *
 * Created by CaoKe on 2015/5/13.
 */
public class PCV extends RDFVocabulary{
    public static final String NAMESPACE = "http://prismstandard.org/namespaces/1.2/pcv/";
    public static final String PREFIX = "pcv";
    public static final PCV INSTANCE = new PCV();

    public final RDFResource broaderTerm;
    public final RDFResource code;
    public final RDFResource definition;
    public final RDFResource Descriptor;
    public final RDFResource label;
    public final RDFResource narrowerTerm;
    public final RDFResource relatedTerm;
    public final RDFResource synonym;
    public final RDFResource vocabulary;


    public PCV() {
        super(NAMESPACE,PREFIX);
        broaderTerm = addResource("broaderTerm");
        code = addResource("code");
        definition = addResource("definition");
        Descriptor = addResource("Descriptor");
        label = addResource("label");
        narrowerTerm = addResource("narrowerTerm");
        relatedTerm = addResource("relatedTerm");
        synonym = addResource("synonym");
        vocabulary = addResource("vocabulary");
    }

    public static final RDFResource BROADER_TERM = INSTANCE.broaderTerm;
    public static final RDFResource CODE = INSTANCE.code;
    public static final RDFResource DEFINITION = INSTANCE.definition;
    public static final RDFResource DESCRIPTOR = INSTANCE.Descriptor;
    public static final RDFResource LABEL = INSTANCE.label;
    public static final RDFResource NARROWER_TERM = INSTANCE.narrowerTerm;
    public static final RDFResource RELATED_TERM = INSTANCE.relatedTerm;
    public static final RDFResource SYNONYM = INSTANCE.synonym;
    public static final RDFResource VOCABULARY = INSTANCE.vocabulary;


}
