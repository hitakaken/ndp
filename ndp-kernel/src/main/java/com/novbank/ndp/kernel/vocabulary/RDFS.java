package com.novbank.ndp.kernel.vocabulary;

import com.novbank.ndp.kernel.concept.RDFProperty;
import com.novbank.ndp.kernel.concept.RDFResource;
import com.novbank.ndp.kernel.concept.RDFVocabulary;

/**
 * Created by CaoKe on 2015/5/12.
 */
public class RDFS extends RDFVocabulary{
    public static final String NAMESPACE = "http://www.w3.org/2000/01/rdf-schema#";
    public static final String PREFIX = "rdfs";
    public static final RDFS INSTANCE = new RDFS();

    //Class
    public final RDFResource Resource;
    public final RDFResource Class;
    public final RDFResource Literal;
    public final RDFResource Container;
    public final RDFResource ContainerMembershipProperty;
    public final RDFResource Datatype;
    //Property
    public final RDFProperty subClassOf;
    public final RDFProperty subPropertyOf;
    public final RDFProperty comment;
    public final RDFProperty label;
    public final RDFProperty domain;
    public final RDFProperty range;
    public final RDFProperty seeAlso;
    public final RDFProperty isDefinedBy;
    public final RDFProperty member;

    public RDFS() {
        super(NAMESPACE,PREFIX);
        Resource = addClass("Resource");
        Class = addClass("Class");
        Literal = addClass("Literal");
        Container = addClass("Container");
        ContainerMembershipProperty = addClass("ContainerMembershipProperty");
        Datatype = addClass("Datatype");
        subClassOf = addProperty("subClassOf");
        subPropertyOf = addProperty("subPropertyOf");
        comment = addProperty("comment");
        label = addProperty("label");
        domain = addProperty("domain");
        range = addProperty("range");
        seeAlso = addProperty("seeAlso");
        isDefinedBy = addProperty("isDefinedBy");
        member = addProperty("member");
    }

    public static final RDFResource RESOURCE = INSTANCE.Resource;
    public static final RDFResource CLASS = INSTANCE.Class;
    public static final RDFResource LITERAL = INSTANCE.Literal;
    public static final RDFResource CONTAINER = INSTANCE.Container;
    public static final RDFResource CONTAINER_MEMBERSHIP_PROPERTY = INSTANCE.ContainerMembershipProperty;
    public static final RDFResource DATATYPE = INSTANCE.Datatype;
    public static final RDFProperty SUB_CLASS_OF = INSTANCE.subClassOf;
    public static final RDFProperty SUB_PROPERTY_OF = INSTANCE.subPropertyOf;
    public static final RDFProperty COMMENT = INSTANCE.comment;
    public static final RDFProperty LABEL = INSTANCE.label;
    public static final RDFProperty DOMAIN = INSTANCE.domain;
    public static final RDFProperty RANGE = INSTANCE.range;
    public static final RDFProperty SEE_ALSO = INSTANCE.seeAlso;
    public static final RDFProperty IS_DEFINED_BY = INSTANCE.isDefinedBy;
    public static final RDFProperty MEMBER = INSTANCE.member;
}
