package com.novbank.ndp.kernel.vocabulary;

import com.novbank.ndp.kernel.rdfsupport.RDFProperty;
import com.novbank.ndp.kernel.rdfsupport.RDFVocabulary;

/**
 * PRISM Rights Language
 *
 * Created by CaoKe on 2015/5/13.
 */
public class PRL extends RDFVocabulary{
    public static final String NAMESPACE = "http://prismstandard.org/namespaces/1.2/prl/";
    public static final String PREFIX = "prl";
    public static final PRL INSTANCE = new PRL();

    public final RDFProperty geography;
    public final RDFProperty industry;
    public final RDFProperty usage;

    public PRL() {
        super(NAMESPACE,PREFIX);
        geography = addProperty("geography");
        industry = addProperty("industry");
        usage = addProperty("usage");
        lock();
    }

    public static final RDFProperty GEOGRAPHY = INSTANCE.geography;
    public static final RDFProperty INDUSTRY = INSTANCE.industry;
    public static final RDFProperty USAGE = INSTANCE.usage;
}
