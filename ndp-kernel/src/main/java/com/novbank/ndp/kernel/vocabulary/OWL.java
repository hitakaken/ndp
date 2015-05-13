package com.novbank.ndp.kernel.vocabulary;

import com.novbank.ndp.kernel.mixin.RDFProperty;
import com.novbank.ndp.kernel.mixin.RDFResource;
import com.novbank.ndp.kernel.mixin.RDFVocabulary;

/**
 * Created by CaoKe on 2015/5/12.
 */
public class OWL extends RDFVocabulary{
    public static final String NAMESPACE = "http://www.w3.org/2002/07/owl#";
    public static final String PREFIX = "owl";
    public static final OWL INSTANCE = new OWL();
    //Class
    public final RDFResource AllDifferent;
    public final RDFResource AllDisjointClasses;
    public final RDFResource AllDisjointProperties;
    public final RDFResource Annotation;
    public final RDFResource AnnotationProperty;
    public final RDFResource AsymmetricProperty;
    public final RDFResource Axiom;
    public final RDFResource Class;
    public final RDFResource DataRange;
    public final RDFResource DatatypeProperty;
    public final RDFResource DeprecatedClass;
    public final RDFResource DeprecatedProperty;
    public final RDFResource FunctionalProperty;
    public final RDFResource InverseFunctionalProperty;
    public final RDFResource IrreflexiveProperty;
    public final RDFResource NamedIndividual;
    public final RDFResource NegativePropertyAssertion;
    public final RDFResource Nothing;
    public final RDFResource ObjectProperty;
    public final RDFResource Ontology;
    public final RDFResource OntologyProperty;
    public final RDFResource ReflexiveProperty;
    public final RDFResource Restriction;
    public final RDFResource SymmetricProperty;
    public final RDFResource TransitiveProperty;
    public final RDFResource Thing;
    //Property
    public final RDFProperty allValuesFrom;
    public final RDFProperty annotatedProperty;
    public final RDFProperty annotatedSource;
    public final RDFProperty annotatedTarget;
    public final RDFProperty assertionProperty;
    public final RDFProperty backwardCompatibleWith;
    public final RDFProperty bottomDataProperty;
    public final RDFProperty bottomObjectProperty;
    public final RDFProperty cardinality;
    public final RDFProperty complementOf;
    public final RDFProperty datatypeComplementOf;
    public final RDFProperty deprecated;
    public final RDFProperty differentFrom;
    public final RDFProperty disjointUnionOf;
    public final RDFProperty disjointWith;
    public final RDFProperty distinctMembers;
    public final RDFProperty equivalentClass;
    public final RDFProperty equivalentProperty;
    public final RDFProperty hasKey;
    public final RDFProperty hasSelf;
    public final RDFProperty hasValue;
    public final RDFProperty imports;
    public final RDFProperty incompatibleWith;
    public final RDFProperty intersectionOf;
    public final RDFProperty inverseOf;
    public final RDFProperty maxCardinality;
    public final RDFProperty maxQualifiedCardinality;
    public final RDFProperty members;
    public final RDFProperty minCardinality;
    public final RDFProperty minQualifiedCardinality;
    public final RDFProperty onClass;
    public final RDFProperty onDataRange;
    public final RDFProperty onDatatype;
    public final RDFProperty oneOf;
    public final RDFProperty onProperties;
    public final RDFProperty onProperty;
    public final RDFProperty priorVersion;
    public final RDFProperty propertyChainAxiom;
    public final RDFProperty propertyDisjointWith;
    public final RDFProperty qualifiedCardinality;
    public final RDFProperty sameAs;
    public final RDFProperty someValuesFrom;
    public final RDFProperty sourceIndividual;
    public final RDFProperty targetIndividual;
    public final RDFProperty targetValue;
    public final RDFProperty topDataProperty;
    public final RDFProperty topObjectProperty;
    public final RDFProperty unionOf;
    public final RDFProperty versionInfo;
    public final RDFProperty versionIRI;
    public final RDFProperty withRestrictions;

    public OWL() {
        super(NAMESPACE,PREFIX);
        AllDifferent = addClass("AllDifferent");
        AllDisjointClasses = addClass("AllDisjointClasses");
        AllDisjointProperties = addClass("AllDisjointProperties");
        Annotation = addClass("Annotation");
        AnnotationProperty = addClass("AnnotationProperty");
        AsymmetricProperty = addClass("AsymmetricProperty");
        Axiom = addClass("Axiom");
        Class = addClass("Class");
        DataRange = addClass("DataRange");
        DatatypeProperty = addClass("DatatypeProperty");
        DeprecatedClass = addClass("DeprecatedClass");
        DeprecatedProperty = addClass("DeprecatedProperty");
        FunctionalProperty = addClass("FunctionalProperty");
        InverseFunctionalProperty = addClass("InverseFunctionalProperty");
        IrreflexiveProperty = addClass("IrreflexiveProperty");
        NamedIndividual = addClass("NamedIndividual");
        NegativePropertyAssertion = addClass("NegativePropertyAssertion");
        Nothing = addClass("Nothing");
        ObjectProperty = addClass("ObjectProperty");
        Ontology = addClass("Ontology");
        OntologyProperty = addClass("OntologyProperty");
        ReflexiveProperty = addClass("ReflexiveProperty");
        Restriction = addClass("Restriction");
        SymmetricProperty = addClass("SymmetricProperty");
        TransitiveProperty = addClass("TransitiveProperty");
        Thing = addClass("Thing");
        allValuesFrom = addProperty("allValuesFrom");
        annotatedProperty = addProperty("annotatedProperty");
        annotatedSource = addProperty("annotatedSource");
        annotatedTarget = addProperty("annotatedTarget");
        assertionProperty = addProperty("assertionProperty");
        backwardCompatibleWith = addProperty("backwardCompatibleWith");
        bottomDataProperty = addProperty("bottomDataProperty");
        bottomObjectProperty = addProperty("bottomObjectProperty");
        cardinality = addProperty("cardinality");
        complementOf = addProperty("complementOf");
        datatypeComplementOf = addProperty("datatypeComplementOf");
        deprecated = addProperty("deprecated");
        differentFrom = addProperty("differentFrom");
        disjointUnionOf = addProperty("disjointUnionOf");
        disjointWith = addProperty("disjointWith");
        distinctMembers = addProperty("distinctMembers");
        equivalentClass = addProperty("equivalentClass");
        equivalentProperty = addProperty("equivalentProperty");
        hasKey = addProperty("hasKey");
        hasSelf = addProperty("hasSelf");
        hasValue = addProperty("hasValue");
        imports = addProperty("imports");
        incompatibleWith = addProperty("incompatibleWith");
        intersectionOf = addProperty("intersectionOf");
        inverseOf = addProperty("inverseOf");
        maxCardinality = addProperty("maxCardinality");
        maxQualifiedCardinality = addProperty("maxQualifiedCardinality");
        members = addProperty("members");
        minCardinality = addProperty("minCardinality");
        minQualifiedCardinality = addProperty("minQualifiedCardinality");
        onClass = addProperty("onClass");
        onDataRange = addProperty("onDataRange");
        onDatatype = addProperty("onDatatype");
        oneOf = addProperty("oneOf");
        onProperties = addProperty("onProperties");
        onProperty = addProperty("onProperty");
        priorVersion = addProperty("priorVersion");
        propertyChainAxiom = addProperty("propertyChainAxiom");
        propertyDisjointWith = addProperty("propertyDisjointWith");
        qualifiedCardinality = addProperty("qualifiedCardinality");
        sameAs = addProperty("sameAs");
        someValuesFrom = addProperty("someValuesFrom");
        sourceIndividual = addProperty("sourceIndividual");
        targetIndividual = addProperty("targetIndividual");
        targetValue = addProperty("targetValue");
        topDataProperty = addProperty("topDataProperty");
        topObjectProperty = addProperty("topObjectProperty");
        unionOf = addProperty("unionOf");
        versionInfo = addProperty("versionInfo");
        versionIRI = addProperty("versionIRI");
        withRestrictions = addProperty("withRestrictions");
    }

    public static final RDFResource ALL_DIFFERENT = INSTANCE.AllDifferent;
    public static final RDFResource ALL_DISJOINT_CLASSES = INSTANCE.AllDisjointClasses;
    public static final RDFResource ALL_DISJOINT_PROPERTIES = INSTANCE.AllDisjointProperties;
    public static final RDFResource ANNOTATION = INSTANCE.Annotation;
    public static final RDFResource ANNOTATION_PROPERTY = INSTANCE.AnnotationProperty;
    public static final RDFResource ASYMMETRIC_PROPERTY = INSTANCE.AsymmetricProperty;
    public static final RDFResource AXIOM = INSTANCE.Axiom;
    public static final RDFResource CLASS = INSTANCE.Class;
    public static final RDFResource DATA_RANGE = INSTANCE.DataRange;
    public static final RDFResource DATATYPE_PROPERTY = INSTANCE.DatatypeProperty;
    public static final RDFResource DEPRECATED_CLASS = INSTANCE.DeprecatedClass;
    public static final RDFResource DEPRECATED_PROPERTY = INSTANCE.DeprecatedProperty;
    public static final RDFResource FUNCTIONAL_PROPERTY = INSTANCE.FunctionalProperty;
    public static final RDFResource INVERSE_FUNCTIONAL_PROPERTY = INSTANCE.InverseFunctionalProperty;
    public static final RDFResource IRREFLEXIVE_PROPERTY = INSTANCE.IrreflexiveProperty;
    public static final RDFResource NAMED_INDIVIDUAL = INSTANCE.NamedIndividual;
    public static final RDFResource NEGATIVE_PROPERTY_ASSERTION = INSTANCE.NegativePropertyAssertion;
    public static final RDFResource NOTHING = INSTANCE.Nothing;
    public static final RDFResource OBJECT_PROPERTY = INSTANCE.ObjectProperty;
    public static final RDFResource ONTOLOGY = INSTANCE.Ontology;
    public static final RDFResource ONTOLOGY_PROPERTY = INSTANCE.OntologyProperty;
    public static final RDFResource REFLEXIVE_PROPERTY = INSTANCE.ReflexiveProperty;
    public static final RDFResource RESTRICTION = INSTANCE.Restriction;
    public static final RDFResource SYMMETRIC_PROPERTY = INSTANCE.SymmetricProperty;
    public static final RDFResource TRANSITIVE_PROPERTY = INSTANCE.TransitiveProperty;
    public static final RDFResource THING = INSTANCE.Thing;
    public static final RDFProperty ALL_VALUES_FROM = INSTANCE.allValuesFrom;
    public static final RDFProperty ANNOTATED_PROPERTY = INSTANCE.annotatedProperty;
    public static final RDFProperty ANNOTATED_SOURCE = INSTANCE.annotatedSource;
    public static final RDFProperty ANNOTATED_TARGET = INSTANCE.annotatedTarget;
    public static final RDFProperty ASSERTION_PROPERTY = INSTANCE.assertionProperty;
    public static final RDFProperty BACKWARD_COMPATIBLE_WITH = INSTANCE.backwardCompatibleWith;
    public static final RDFProperty BOTTOM_DATA_PROPERTY = INSTANCE.bottomDataProperty;
    public static final RDFProperty BOTTOM_OBJECT_PROPERTY = INSTANCE.bottomObjectProperty;
    public static final RDFProperty CARDINALITY = INSTANCE.cardinality;
    public static final RDFProperty COMPLEMENT_OF = INSTANCE.complementOf;
    public static final RDFProperty DATATYPE_COMPLEMENT_OF = INSTANCE.datatypeComplementOf;
    public static final RDFProperty DEPRECATED = INSTANCE.deprecated;
    public static final RDFProperty DIFFERENT_FROM = INSTANCE.differentFrom;
    public static final RDFProperty DISJOINT_UNION_OF = INSTANCE.disjointUnionOf;
    public static final RDFProperty DISJOINT_WITH = INSTANCE.disjointWith;
    public static final RDFProperty DISTINCT_MEMBERS = INSTANCE.distinctMembers;
    public static final RDFProperty EQUIVALENT_CLASS = INSTANCE.equivalentClass;
    public static final RDFProperty EQUIVALENT_PROPERTY = INSTANCE.equivalentProperty;
    public static final RDFProperty HAS_KEY = INSTANCE.hasKey;
    public static final RDFProperty HAS_SELF = INSTANCE.hasSelf;
    public static final RDFProperty HAS_VALUE = INSTANCE.hasValue;
    public static final RDFProperty IMPORTS = INSTANCE.imports;
    public static final RDFProperty INCOMPATIBLE_WITH = INSTANCE.incompatibleWith;
    public static final RDFProperty INTERSECTION_OF = INSTANCE.intersectionOf;
    public static final RDFProperty INVERSE_OF = INSTANCE.inverseOf;
    public static final RDFProperty MAX_CARDINALITY = INSTANCE.maxCardinality;
    public static final RDFProperty MAX_QUALIFIED_CARDINALITY = INSTANCE.maxQualifiedCardinality;
    public static final RDFProperty MEMBERS = INSTANCE.members;
    public static final RDFProperty MIN_CARDINALITY = INSTANCE.minCardinality;
    public static final RDFProperty MIN_QUALIFIED_CARDINALITY = INSTANCE.minQualifiedCardinality;
    public static final RDFProperty ON_CLASS = INSTANCE.onClass;
    public static final RDFProperty ON_DATA_RANGE = INSTANCE.onDataRange;
    public static final RDFProperty ON_DATATYPE = INSTANCE.onDatatype;
    public static final RDFProperty ONE_OF = INSTANCE.oneOf;
    public static final RDFProperty ON_PROPERTIES = INSTANCE.onProperties;
    public static final RDFProperty ON_PROPERTY = INSTANCE.onProperty;
    public static final RDFProperty PRIOR_VERSION = INSTANCE.priorVersion;
    public static final RDFProperty PROPERTY_CHAIN_AXIOM = INSTANCE.propertyChainAxiom;
    public static final RDFProperty PROPERTY_DISJOINT_WITH = INSTANCE.propertyDisjointWith;
    public static final RDFProperty QUALIFIED_CARDINALITY = INSTANCE.qualifiedCardinality;
    public static final RDFProperty SAME_AS = INSTANCE.sameAs;
    public static final RDFProperty SOME_VALUES_FROM = INSTANCE.someValuesFrom;
    public static final RDFProperty SOURCE_INDIVIDUAL = INSTANCE.sourceIndividual;
    public static final RDFProperty TARGET_INDIVIDUAL = INSTANCE.targetIndividual;
    public static final RDFProperty TARGET_VALUE = INSTANCE.targetValue;
    public static final RDFProperty TOP_DATA_PROPERTY = INSTANCE.topDataProperty;
    public static final RDFProperty TOP_OBJECT_PROPERTY = INSTANCE.topObjectProperty;
    public static final RDFProperty UNION_OF = INSTANCE.unionOf;
    public static final RDFProperty VERSION_INFO = INSTANCE.versionInfo;
    public static final RDFProperty VERSION_IRI = INSTANCE.versionIRI;
    public static final RDFProperty WITH_RESTRICTIONS = INSTANCE.withRestrictions;
}
