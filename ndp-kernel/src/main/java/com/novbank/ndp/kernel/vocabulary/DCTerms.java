package com.novbank.ndp.kernel.vocabulary;

import com.novbank.ndp.kernel.rdfsupport.RDFProperty;
import com.novbank.ndp.kernel.rdfsupport.RDFResource;
import com.novbank.ndp.kernel.rdfsupport.RDFVocabulary;

/**
 * Created by CaoKe on 2015/5/12.
 */
public class DCTerms extends RDFVocabulary {
    public static final String NAMESPACE = "http://purl.org/dc/terms/";
    public static final String PREFIX = "dcterms";
    public static final DCTerms INSTANCE = new DCTerms();

    public final RDFProperty _abstract;
    public final RDFProperty accessRights;
    public final RDFProperty accrualMethod;
    public final RDFProperty accrualPeriodicity;
    public final RDFProperty accrualPolicy;
    public final RDFProperty alternative;
    public final RDFProperty audience;
    public final RDFProperty available;
    public final RDFProperty bibliographicCitation;
    public final RDFProperty conformsTo;
    public final RDFProperty created;
    public final RDFProperty dateAccepted;
    public final RDFProperty dateCopyrighted;
    public final RDFProperty dateSubmitted;
    public final RDFProperty educationLevel;
    public final RDFProperty extent;
    public final RDFProperty hasFormat;
    public final RDFProperty hasPart;
    public final RDFProperty hasVersion;
    public final RDFProperty instructionalMethod;
    public final RDFProperty isFormatOf;
    public final RDFProperty isPartOf;
    public final RDFProperty isReferencedBy;
    public final RDFProperty isReplacedBy;
    public final RDFProperty isRequiredBy;
    public final RDFProperty isVersionOf;
    public final RDFProperty issued;
    public final RDFProperty license;
    public final RDFProperty mediator;
    public final RDFProperty medium;
    public final RDFProperty modified;
    public final RDFProperty provenance;
    public final RDFProperty references;
    public final RDFProperty replaces;
    public final RDFProperty requires;
    public final RDFProperty rightsHolder;
    public final RDFProperty spatial;
    public final RDFProperty tableOfContents;
    public final RDFProperty temporal;
    public final RDFProperty valid;
    public final RDFResource Agent;
    public final RDFResource AgentClass;
    public final RDFResource BibliographicResource;
    public final RDFResource FileFormat;
    public final RDFResource Frequency;
    public final RDFResource Jurisdiction;
    public final RDFResource LicenseDocument;
    public final RDFResource LinguisticSystem;
    public final RDFResource Location;
    public final RDFResource LocationPeriodOrJurisdiction;
    public final RDFResource MediaType;
    public final RDFResource MediaTypeOrExtent;
    public final RDFResource MethodOfAccrual;
    public final RDFResource MethodOfInstruction;
    public final RDFResource PeriodOfTime;
    public final RDFResource PhysicalMedium;
    public final RDFResource PhysicalResource;
    public final RDFResource Policy;
    public final RDFResource ProvenanceStatement;
    public final RDFResource RightsStatement;
    public final RDFResource SizeOrDuration;
    public final RDFResource Standard;

    public final RDFResource DCMIType;
    public final RDFResource DDC;
    public final RDFResource IMT;
    public final RDFResource LCC;
    public final RDFResource LCSH;
    public final RDFResource MESH;
    public final RDFResource NLM;
    public final RDFResource TGN;
    public final RDFResource UDC;

    public final RDFResource Box;
    public final RDFResource ISO3166;
    public final RDFResource ISO639_2;
    public final RDFResource ISO639_3;
    public final RDFResource Period;
    public final RDFResource Point;
    public final RDFResource RFC1766;
    public final RDFResource RFC3066;
    public final RDFResource RFC4646;
    public final RDFResource RFC5646;
    public final RDFResource URI;
    public final RDFResource W3CDTF;

    private DCTerms() {
        super(NAMESPACE, PREFIX);
        //Property
        _abstract = addProperty("abstract");
        accessRights = addProperty("accessRights");
        accrualMethod = addProperty("accrualMethod");
        accrualPeriodicity = addProperty("accrualPeriodicity");
        accrualPolicy = addProperty("accrualPolicy");
        alternative = addProperty("alternative");
        audience = addProperty("audience");
        available = addProperty("available");
        bibliographicCitation = addProperty("bibliographicCitation");
        conformsTo = addProperty("conformsTo");
        created = addProperty("created");
        dateAccepted = addProperty("dateAccepted");
        dateCopyrighted = addProperty("dateCopyrighted");
        dateSubmitted = addProperty("dateSubmitted");
        educationLevel = addProperty("educationLevel");
        extent = addProperty("extent");
        hasFormat = addProperty("hasFormat");
        hasPart = addProperty("hasPart");
        hasVersion = addProperty("hasVersion");
        instructionalMethod = addProperty("instructionalMethod");
        isFormatOf = addProperty("isFormatOf");
        isPartOf = addProperty("isPartOf");
        isReferencedBy = addProperty("isReferencedBy");
        isReplacedBy = addProperty("isReplacedBy");
        isRequiredBy = addProperty("isRequiredBy");
        isVersionOf = addProperty("isVersionOf");
        issued = addProperty("issued");
        license = addProperty("license");
        mediator = addProperty("mediator");
        medium = addProperty("medium");
        modified = addProperty("modified");
        provenance = addProperty("provenance");
        references = addProperty("references");
        replaces = addProperty("replaces");
        requires = addProperty("requires");
        rightsHolder = addProperty("rightsHolder");
        spatial = addProperty("spatial");
        tableOfContents = addProperty("tableOfContents");
        temporal = addProperty("temporal");
        valid = addProperty("valid");
        //Class
        Agent = addClass("Agent");
        AgentClass = addClass("AgentClass");
        BibliographicResource = addClass("BibliographicResource");
        FileFormat = addClass("FileFormat");
        Frequency = addClass("Frequency");
        Jurisdiction = addClass("Jurisdiction");
        LicenseDocument = addClass("LicenseDocument");
        LinguisticSystem = addClass("LinguisticSystem");
        Location = addClass("Location");
        LocationPeriodOrJurisdiction = addClass("LocationPeriodOrJurisdiction");
        MediaType = addClass("MediaType");
        MediaTypeOrExtent = addClass("MediaTypeOrExtent");
        MethodOfAccrual = addClass("MethodOfAccrual");
        MethodOfInstruction = addClass("MethodOfInstruction");
        PeriodOfTime = addClass("PeriodOfTime");
        PhysicalMedium = addClass("PhysicalMedium");
        PhysicalResource = addClass("PhysicalResource");
        Policy = addClass("Policy");
        ProvenanceStatement = addClass("ProvenanceStatement");
        RightsStatement = addClass("RightsStatement");
        SizeOrDuration = addClass("SizeOrDuration");
        Standard = addClass("Standard");
        //Vocabulary Encoding Schemes
        DCMIType = addResource("DCMIType");
        DDC = addResource("DDC");
        IMT = addResource("IMT");
        LCC = addResource("LCC");
        LCSH = addResource("LCSH");
        MESH = addResource("MESH");
        NLM = addResource("NLM");
        TGN = addResource("TGN");
        UDC = addResource("UDC");
        //Syntax Encoding Schemes
        Box = addResource("Box");
        ISO3166 = addResource("ISO3166");
        ISO639_2 = addResource("ISO639-2");
        ISO639_3 = addResource("ISO639-3");
        Period = addResource("Period");
        Point = addResource("Point");
        RFC1766 = addResource("RFC1766");
        RFC3066 = addResource("RFC3066");
        RFC4646 = addResource("RFC4646");
        RFC5646 = addResource("RFC5646");
        URI = addResource("URI");
        W3CDTF = addResource("W3CDTF");
        lock();
    }
    
    public static final RDFProperty ABSTRACT = INSTANCE._abstract;
    public static final RDFProperty ACCESS_RIGHTS = INSTANCE.accessRights;
    public static final RDFProperty ACCRUAL_METHOD = INSTANCE.accrualMethod;
    public static final RDFProperty ACCRUAL_PERIODICITY = INSTANCE.accrualPeriodicity;
    public static final RDFProperty ACCRUAL_POLICY = INSTANCE.accrualPolicy;
    public static final RDFProperty ALTERNATIVE = INSTANCE.alternative;
    public static final RDFProperty AUDIENCE = INSTANCE.audience;
    public static final RDFProperty AVAILABLE = INSTANCE.available;
    public static final RDFProperty BIBLIOGRAPHIC_CITATION = INSTANCE.bibliographicCitation;
    public static final RDFProperty CONFORMS_TO = INSTANCE.conformsTo;
    public static final RDFProperty CREATED = INSTANCE.created;
    public static final RDFProperty DATE_ACCEPTED = INSTANCE.dateAccepted;
    public static final RDFProperty DATE_COPYRIGHTED = INSTANCE.dateCopyrighted;
    public static final RDFProperty DATE_SUBMITTED = INSTANCE.dateSubmitted;
    public static final RDFProperty EDUCATION_LEVEL = INSTANCE.educationLevel;
    public static final RDFProperty EXTENT = INSTANCE.extent;
    public static final RDFProperty HAS_FORMAT = INSTANCE.hasFormat;
    public static final RDFProperty HAS_PART = INSTANCE.hasPart;
    public static final RDFProperty HAS_VERSION = INSTANCE.hasVersion;
    public static final RDFProperty INSTRUCTIONAL_METHOD = INSTANCE.instructionalMethod;
    public static final RDFProperty IS_FORMAT_OF = INSTANCE.isFormatOf;
    public static final RDFProperty IS_PART_OF = INSTANCE.isPartOf;
    public static final RDFProperty IS_REFERENCED_BY = INSTANCE.isReferencedBy;
    public static final RDFProperty IS_REPLACED_BY = INSTANCE.isReplacedBy;
    public static final RDFProperty IS_REQUIRED_BY = INSTANCE.isRequiredBy;
    public static final RDFProperty ISSUED = INSTANCE.issued;
    public static final RDFProperty IS_VERSION_OF = INSTANCE.isVersionOf;
    public static final RDFProperty LICENSE = INSTANCE.license;
    public static final RDFProperty MEDIATOR = INSTANCE.mediator;
    public static final RDFProperty MEDIUM = INSTANCE.medium;
    public static final RDFProperty MODIFIED = INSTANCE.modified;
    public static final RDFProperty PROVENANCE = INSTANCE.provenance;
    public static final RDFProperty REFERENCES = INSTANCE.references;
    public static final RDFProperty REPLACES = INSTANCE.replaces;
    public static final RDFProperty REQUIRES = INSTANCE.requires;
    public static final RDFProperty RIGHTS_HOLDER = INSTANCE.rightsHolder;
    public static final RDFProperty SPATIAL = INSTANCE.spatial;
    public static final RDFProperty TABLE_OF_CONTENTS = INSTANCE.tableOfContents;
    public static final RDFProperty TEMPORAL = INSTANCE.temporal;
    public static final RDFProperty VALID = INSTANCE.valid;

    public static final RDFResource AGENT = INSTANCE.Agent;
    public static final RDFResource AGENT_CLASS = INSTANCE.AgentClass;
    public static final RDFResource BIBLIOGRAPHIC_RESOURCE = INSTANCE.BibliographicResource;
    public static final RDFResource FILE_FORMAT = INSTANCE.FileFormat;
    public static final RDFResource FREQUENCY = INSTANCE.Frequency;
    public static final RDFResource JURISDICTION = INSTANCE.Jurisdiction;
    public static final RDFResource LICENSE_DOCUMENT = INSTANCE.LicenseDocument;
    public static final RDFResource LINGUISTIC_SYSTEM = INSTANCE.LinguisticSystem;
    public static final RDFResource LOCATION = INSTANCE.Location;
    public static final RDFResource LOCATION_PERIOD_OR_JURISDICTION = INSTANCE.LocationPeriodOrJurisdiction;
    public static final RDFResource MEDIA_TYPE = INSTANCE.MediaType;
    public static final RDFResource MEDIA_TYPE_OR_EXTENT = INSTANCE.MediaTypeOrExtent;
    public static final RDFResource METHOD_OF_ACCRUAL = INSTANCE.MethodOfAccrual;
    public static final RDFResource METHOD_OF_INSTRUCTION = INSTANCE.MethodOfInstruction;
    public static final RDFResource PERIOD_OF_TIME = INSTANCE.PeriodOfTime;
    public static final RDFResource PHYSICAL_MEDIUM = INSTANCE.PhysicalMedium;
    public static final RDFResource PHYSICAL_RESOURCE = INSTANCE.PhysicalResource;
    public static final RDFResource POLICY = INSTANCE.Policy;
    public static final RDFResource PROVENANCE_STATEMENT = INSTANCE.ProvenanceStatement;
    public static final RDFResource RIGHTS_STATEMENT = INSTANCE.RightsStatement;
    public static final RDFResource SIZE_OR_DURATION = INSTANCE.SizeOrDuration;
    public static final RDFResource STANDARD = INSTANCE.Standard;
    
}
