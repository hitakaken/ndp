package com.novbank.ndp.kernel.vocabulary;

import com.novbank.ndp.kernel.concept.RDFProperty;
import com.novbank.ndp.kernel.concept.RDFVocabulary;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by CaoKe on 2015/5/12.
 */
public class DC extends RDFVocabulary{
    public static final String NAMESPACE = "http://purl.org/dc/elements/1.1/";
    public static final String PREFIX = "dc";
    public static final DC INSTANCE = new DC();

    public final RDFProperty title;
    public final RDFProperty source;
    public final RDFProperty contributor;
    public final RDFProperty coverage;
    public final RDFProperty creator;
    public final RDFProperty date;
    public final RDFProperty description;
    public final RDFProperty format;
    public final RDFProperty identifier;
    public final RDFProperty language;
    public final RDFProperty publisher;
    public final RDFProperty relation;
    public final RDFProperty rights;
    public final RDFProperty subject;
    public final RDFProperty type;

    public DC() {
        super(NAMESPACE,PREFIX);
        title = addProperty("title");
        source = addProperty("source");
        contributor = addProperty("contributor");
        coverage = addProperty("coverage");
        creator = addProperty("creator");
        date = addProperty("date");
        description = addProperty("description");
        format = addProperty("format");
        identifier = addProperty("identifier");
        language = addProperty("language");
        publisher = addProperty("publisher");
        relation = addProperty("relation");
        rights = addProperty("rights");
        subject = addProperty("subject");
        type = addProperty("type");
    }

    public static final RDFProperty TITLE = INSTANCE.title;
    public static final RDFProperty SOURCE  = INSTANCE.source;
    public static final RDFProperty CONTRIBUTOR = INSTANCE.contributor;
    public static final RDFProperty COVERAGE = INSTANCE.coverage;
    public static final RDFProperty CREATOR = INSTANCE.creator;
    public static final RDFProperty DATE = INSTANCE.date;
    public static final RDFProperty DESCRIPTION = INSTANCE.description;
    public static final RDFProperty FORMAT = INSTANCE.format;
    public static final RDFProperty IDENTIFIER = INSTANCE.identifier;
    public static final RDFProperty LANGUAGE = INSTANCE.language;
    public static final RDFProperty PUBLISHER = INSTANCE.publisher;
    public static final RDFProperty RELATION = INSTANCE.relation;
    public static final RDFProperty RIGHTS = INSTANCE.rights;
    public static final RDFProperty SUBJECT = INSTANCE.subject;
    public static final RDFProperty TYPE = INSTANCE.type;

}
