package com.novbank.ndp.kernel.vocabulary;

import com.novbank.ndp.kernel.rdfsupport.RDFProperty;
import com.novbank.ndp.kernel.rdfsupport.RDFResource;
import com.novbank.ndp.kernel.rdfsupport.RDFVocabulary;

/**
 * Created by CaoKe on 2015/5/12.
 */
public class RDF extends RDFVocabulary{
    public static final String NAMESPACE = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
    public static final String PREFIX = "rdf";
    public static final RDF INSTANCE = new RDF();
    //Class
    public final RDFResource Property;
    public final RDFResource Statement;
    public final RDFResource Bag;
    public final RDFResource Seq;
    public final RDFResource Alt;
    public final RDFResource List;
    //Datatype
    public final RDFResource html;
    public final RDFResource langString;
    public final RDFResource PlainLiteral;
    public final RDFResource XMLLiteral;
    //Property
    public final RDFProperty type;
    public final RDFProperty subject;
    public final RDFProperty predicate;
    public final RDFProperty object;
    public final RDFProperty value;
    public final RDFProperty first;
    public final RDFProperty rest;
    //Other
    public final RDFResource nil;

    private RDF() {
        super(NAMESPACE,PREFIX);
        Property = addClass("Property");
        Statement = addClass("Statement");
        Bag = addClass("Bag");
        Seq = addClass("Seq");
        Alt = addClass("Alt");
        List = addClass("List");
        html = addResource("HTML");
        langString = addResource("langString");
        PlainLiteral = addResource("PlainLiteral");
        XMLLiteral = addResource("XMLLiteral");
        type = addProperty("type");
        subject = addProperty("subject");
        predicate = addProperty("predicate");
        object = addProperty("object");
        value = addProperty("value");
        first = addProperty("first");
        rest = addProperty("rest");
        nil = addResource("nil");
        lock();
    }
    
    public static final RDFResource PROPERTY = INSTANCE.Property;
    public static final RDFResource STATEMENT = INSTANCE.Statement;
    public static final RDFResource BAG = INSTANCE.Bag;
    public static final RDFResource SEQ = INSTANCE.Seq;
    public static final RDFResource ALT = INSTANCE.Alt;
    public static final RDFResource LIST = INSTANCE.List;
    public static final RDFResource HTML = INSTANCE.html;
    public static final RDFResource LANG_STRING = INSTANCE.langString;
    public static final RDFResource PLAIN_LITERAL = INSTANCE.PlainLiteral;
    public static final RDFResource XML_LITERAL = INSTANCE.XMLLiteral;
    public static final RDFProperty TYPE = INSTANCE.type;
    public static final RDFProperty SUBJECT = INSTANCE.subject;
    public static final RDFProperty PREDICATE = INSTANCE.predicate;
    public static final RDFProperty OBJECT = INSTANCE.object;
    public static final RDFProperty VALUE = INSTANCE.value;
    public static final RDFProperty FIRST = INSTANCE.first;
    public static final RDFProperty REST = INSTANCE.rest;
    public static final RDFResource NIL = INSTANCE.nil;
}
