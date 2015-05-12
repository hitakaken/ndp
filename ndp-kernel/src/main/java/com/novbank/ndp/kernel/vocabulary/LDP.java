package com.novbank.ndp.kernel.vocabulary;

import com.novbank.ndp.kernel.concept.RDFProperty;
import com.novbank.ndp.kernel.concept.RDFResource;
import com.novbank.ndp.kernel.concept.RDFVocabulary;

/**
 * Created by CaoKe on 2015/5/12.
 */
public class LDP extends RDFVocabulary{
    public static final String NAMESPACE = "http://www.w3.org/ns/ldp#";
    public static final String PREFIX = "ldp";
    public static final LDP INSTANCE = new LDP();

    public final RDFResource BasicContainer;
    public final RDFResource Container;
    public final RDFResource DirectContainer;
    public final RDFResource IndirectContainer;
    public final RDFResource MemberSubject;
    public final RDFResource NonRDFSource;
    public final RDFResource PreferContainment;
    public final RDFResource PreferEmptyContainer;
    public final RDFResource PreferMembership;
    public final RDFResource RDFSource;
    public final RDFResource Resource;

    public final RDFProperty contains;
    public final RDFProperty hasMemberRelation;
    public final RDFProperty insertedContentRelation;
    public final RDFProperty isMemberOfRelation;
    public final RDFProperty member;
    public final RDFProperty membershipResource;


    public LDP(){
        super(NAMESPACE,PREFIX);
        BasicContainer = addClass("BasicContainer");
        Container =addClass("Container");        
        DirectContainer =addClass("DirectContainer");
        IndirectContainer =addClass("IndirectContainer");        
        MemberSubject =addClass("MemberSubject");
        NonRDFSource =addClass("NonRDFSource");
        PreferContainment =addClass("PreferContainment");
        PreferEmptyContainer =addClass("PreferEmptyContainer");
        PreferMembership =addClass("PreferMembership");
        RDFSource =addClass("RDFSource");
        Resource =addClass("Resource");

        contains =addProperty("contains");
        hasMemberRelation =addProperty("hasMemberRelation");
        insertedContentRelation =addProperty("insertedContentRelation");
        isMemberOfRelation =addProperty("isMemberOfRelation");
        member =addProperty("member");
        membershipResource =addProperty("membershipResource");
    }

    public static final RDFResource BASIC_CONTAINER = INSTANCE.BasicContainer;
    public static final RDFResource CONTAINER = INSTANCE.Container;
    public static final RDFResource DIRECT_CONTAINER = INSTANCE.DirectContainer;
    public static final RDFResource INDIRECT_CONTAINER = INSTANCE.IndirectContainer;
    public static final RDFResource MEMBER_SUBJECT = INSTANCE.MemberSubject;
    public static final RDFResource NON_RDF_SOURCE = INSTANCE.NonRDFSource;
    public static final RDFResource PREFER_CONTAINMENT = INSTANCE.PreferContainment;
    public static final RDFResource PREFER_EMPTY_CONTAINER = INSTANCE.PreferEmptyContainer;
    public static final RDFResource PREFER_MEMBERSHIP = INSTANCE.PreferMembership;
    public static final RDFResource RDF_SOURCE = INSTANCE.RDFSource;
    public static final RDFResource RESOURCE = INSTANCE.Resource;

    public static final RDFProperty CONTAINS = INSTANCE.contains;
    public static final RDFProperty HAS_MEMBER_RELATION = INSTANCE.hasMemberRelation;
    public static final RDFProperty INSERTED_CONTENT_RELATION = INSTANCE.insertedContentRelation;
    public static final RDFProperty IS_MEMBER_OF_RELATION = INSTANCE.isMemberOfRelation;
    public static final RDFProperty MEMBER = INSTANCE.member;
    public static final RDFProperty MEMBERSHIP_RESOURCE = INSTANCE.membershipResource;
}
