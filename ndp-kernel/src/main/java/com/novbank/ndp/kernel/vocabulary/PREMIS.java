package com.novbank.ndp.kernel.vocabulary;

import com.novbank.ndp.kernel.rdfsupport.RDFProperty;
import com.novbank.ndp.kernel.rdfsupport.RDFResource;
import com.novbank.ndp.kernel.rdfsupport.RDFVocabulary;

/**
 * Created by CaoKe on 2015/5/16.
 */
public class PREMIS extends RDFVocabulary{
    public static final String NAMESPACE = "http://www.loc.gov/premis/rdf/v1#";
    public static final String PREFIX = "premis";
    public static final PREMIS INSTANCE = new PREMIS();

    public final RDFResource Agent;
    public final RDFResource ApplicableDates;
    public final RDFResource Bitstream;
    public final RDFResource ContentLocation;
    public final RDFResource CopyrightInformation;
    public final RDFResource CreatingApplication;
    public final RDFResource Dependency;
    public final RDFResource Environment;
    public final RDFResource Event;
    public final RDFResource EventOutcomeDetail;
    public final RDFResource EventOutcomeInformation;
    public final RDFResource File;
    public final RDFResource Fixity;
    public final RDFResource Format;
    public final RDFResource FormatDesignation;
    public final RDFResource FormatRegistry;
    public final RDFResource Hardware;
    public final RDFResource Identifier;
    public final RDFResource Inhibitors;
    public final RDFResource IntellectualEntity;
    public final RDFResource LicenseInformation;
    public final RDFResource Object;
    public final RDFResource ObjectCharacteristics;
    public final RDFResource PremisEntity;
    public final RDFResource PreservationLevel;
    public final RDFResource RelatedObjectIdentification;
    public final RDFResource Representation;
    public final RDFResource RightsDocumentation;
    public final RDFResource RightsGranted;
    public final RDFResource RightsStatement;
    public final RDFResource Signature;
    public final RDFResource SignificantProperties;
    public final RDFResource Software;
    public final RDFResource StatuteInformation;
    public final RDFResource Storage;
    public final RDFResource TermOfGrant;
    public final RDFResource TermOfRestriction;

    public final RDFProperty hasAct;
    public final RDFProperty hasAgent;
    public final RDFProperty hasAgentName;
    public final RDFProperty hasAgentNote;
    public final RDFProperty hasAgentType;
    public final RDFProperty hasApplicableDates;
    public final RDFProperty hasCompositionLevel;
    public final RDFProperty hasContentLocation;
    public final RDFProperty hasContentLocationType;
    public final RDFProperty hasContentLocationValue;
    public final RDFProperty hasCopyrightJurisdiction;
    public final RDFProperty hasCopyrightStatus;
    public final RDFProperty hasCopyrightStatusDeterminationDate;
    public final RDFProperty hasCreatingApplication;
    public final RDFProperty hasCreatingApplicationName;
    public final RDFProperty hasCreatingApplicationVersion;
    public final RDFProperty hasDateCreatedByApplication;
    public final RDFProperty hasDependency;
    public final RDFProperty hasDependencyName;
    public final RDFProperty hasEndDate;
    public final RDFProperty hasEnvironment;
    public final RDFProperty hasEnvironmentCharacteristic;
    public final RDFProperty hasEnvironmentNote;
    public final RDFProperty hasEnvironmentPurpose;
    public final RDFProperty hasEvent;
    public final RDFProperty hasEventDateTime;
    public final RDFProperty hasEventDetail;
    public final RDFProperty hasEventOutcome;
    public final RDFProperty hasEventOutcomeDetail;
    public final RDFProperty hasEventOutcomeDetailNote;
    public final RDFProperty hasEventOutcomeInformation;
    public final RDFProperty hasEventRelatedAgent;
    public final RDFProperty hasEventRelatedObject;
    public final RDFProperty hasEventType;
    public final RDFProperty hasFixity;
    public final RDFProperty hasFormat;
    public final RDFProperty hasFormatDesignation;
    public final RDFProperty hasFormatName;
    public final RDFProperty hasFormatNote;
    public final RDFProperty hasFormatRegistry;
    public final RDFProperty hasFormatRegistryKey;
    public final RDFProperty hasFormatRegistryName;
    public final RDFProperty hasFormatRegistryRole;
    public final RDFProperty hasFormatVersion;
    public final RDFProperty hasHardware;
    public final RDFProperty hasHardwareName;
    public final RDFProperty hasHardwareOtherInformation;
    public final RDFProperty hasHardwareType;
    public final RDFProperty hasIdentifier;
    public final RDFProperty hasIdentifierType;
    public final RDFProperty hasIdentifierValue;
    public final RDFProperty hasInhibitorKey;
    public final RDFProperty hasInhibitors;
    public final RDFProperty hasInhibitorTarget;
    public final RDFProperty hasInhibitorType;
    public final RDFProperty hasIntellectualEntity;
    public final RDFProperty hasKeyInformation;
    public final RDFProperty hasLicenseTerms;
    public final RDFProperty hasMessageDigest;
    public final RDFProperty hasMessageDigestAlgorithm;
    public final RDFProperty hasMessageDigestOriginator;
    public final RDFProperty hasObject;
    public final RDFProperty hasObjectCharacteristics;
    public final RDFProperty hasOriginalName;
    public final RDFProperty hasPreservationLevel;
    public final RDFProperty hasPreservationLevelDateAssigned;
    public final RDFProperty hasPreservationLevelRationale;
    public final RDFProperty hasPreservationLevelRole;
    public final RDFProperty hasPreservationLevelValue;
    public final RDFProperty hasRelatedObject;
    public final RDFProperty hasRelatedObjectSequence;
    public final RDFProperty hasRelatedStatuteInformation;
    public final RDFProperty hasRelationship;
    public final RDFProperty hasRestriction;
    public final RDFProperty hasRightsDocumentation;
    public final RDFProperty hasRightsDocumentationRole;
    public final RDFProperty hasRightsGranted;
    public final RDFProperty hasRightsGrantedNote;
    public final RDFProperty hasRightsRelatedAgent;
    public final RDFProperty hasRightsStatement;
    public final RDFProperty hasRightsStatementNote;
    public final RDFProperty hasSignature;
    public final RDFProperty hasSignatureEncoding;
    public final RDFProperty hasSignatureMethod;
    public final RDFProperty hasSignatureProperties;
    public final RDFProperty hasSignatureValidationRules;
    public final RDFProperty hasSignatureValue;
    public final RDFProperty hasSigner;
    public final RDFProperty hasSignificantProperties;
    public final RDFProperty hasSignificantPropertiesType;
    public final RDFProperty hasSignificantPropertiesValue;
    public final RDFProperty hasSize;
    public final RDFProperty hasSoftware;
    public final RDFProperty hasSoftwareDependency;
    public final RDFProperty hasSoftwareName;
    public final RDFProperty hasSoftwareOtherInformation;
    public final RDFProperty hasSoftwareType;
    public final RDFProperty hasSoftwareVersion;
    public final RDFProperty hasStartDate;
    public final RDFProperty hasStatuteCitation;
    public final RDFProperty hasStatuteInformationDeterminationDate;
    public final RDFProperty hasStatuteJurisdiction;
    public final RDFProperty hasStorage;
    public final RDFProperty hasStorageMedium;
    public final RDFProperty hasTermOfGrant;
    public final RDFProperty hasTermOfRestriction;

    private PREMIS() {
        super(NAMESPACE,PREFIX);

        Agent = addClass("Agent");
        ApplicableDates = addClass("ApplicableDates");
        Bitstream = addClass("Bitstream");
        ContentLocation = addClass("ContentLocation");
        CopyrightInformation = addClass("CopyrightInformation");
        CreatingApplication = addClass("CreatingApplication");
        Dependency = addClass("Dependency");
        Environment = addClass("Environment");
        Event = addClass("Event");
        EventOutcomeDetail = addClass("EventOutcomeDetail");
        EventOutcomeInformation = addClass("EventOutcomeInformation");
        File = addClass("File");
        Fixity = addClass("Fixity");
        Format = addClass("Format");
        FormatDesignation = addClass("FormatDesignation");
        FormatRegistry = addClass("FormatRegistry");
        Hardware = addClass("Hardware");
        Identifier = addClass("Identifier");
        Inhibitors = addClass("Inhibitors");
        IntellectualEntity = addClass("IntellectualEntity");
        LicenseInformation = addClass("LicenseInformation");
        Object = addClass("Object");
        ObjectCharacteristics = addClass("ObjectCharacteristics");
        PremisEntity = addClass("PremisEntity");
        PreservationLevel = addClass("PreservationLevel");
        RelatedObjectIdentification = addClass("RelatedObjectIdentification");
        Representation = addClass("Representation");
        RightsDocumentation = addClass("RightsDocumentation");
        RightsGranted = addClass("RightsGranted");
        RightsStatement = addClass("RightsStatement");
        Signature = addClass("Signature");
        SignificantProperties = addClass("SignificantProperties");
        Software = addClass("Software");
        StatuteInformation = addClass("StatuteInformation");
        Storage = addClass("Storage");
        TermOfGrant = addClass("TermOfGrant");
        TermOfRestriction = addClass("TermOfRestriction");

        hasAct = addProperty("hasAct");
        hasAgent = addProperty("hasAgent");
        hasAgentName = addProperty("hasAgentName");
        hasAgentNote = addProperty("hasAgentNote");
        hasAgentType = addProperty("hasAgentType");
        hasApplicableDates = addProperty("hasApplicableDates");
        hasCompositionLevel = addProperty("hasCompositionLevel");
        hasContentLocation = addProperty("hasContentLocation");
        hasContentLocationType = addProperty("hasContentLocationType");
        hasContentLocationValue = addProperty("hasContentLocationValue");
        hasCopyrightJurisdiction = addProperty("hasCopyrightJurisdiction");
        hasCopyrightStatus = addProperty("hasCopyrightStatus");
        hasCopyrightStatusDeterminationDate = addProperty("hasCopyrightStatusDeterminationDate");
        hasCreatingApplication = addProperty("hasCreatingApplication");
        hasCreatingApplicationName = addProperty("hasCreatingApplicationName");
        hasCreatingApplicationVersion = addProperty("hasCreatingApplicationVersion");
        hasDateCreatedByApplication = addProperty("hasDateCreatedByApplication");
        hasDependency = addProperty("hasDependency");
        hasDependencyName = addProperty("hasDependencyName");
        hasEndDate = addProperty("hasEndDate");
        hasEnvironment = addProperty("hasEnvironment");
        hasEnvironmentCharacteristic = addProperty("hasEnvironmentCharacteristic");
        hasEnvironmentNote = addProperty("hasEnvironmentNote");
        hasEnvironmentPurpose = addProperty("hasEnvironmentPurpose");
        hasEvent = addProperty("hasEvent");
        hasEventDateTime = addProperty("hasEventDateTime");
        hasEventDetail = addProperty("hasEventDetail");
        hasEventOutcome = addProperty("hasEventOutcome");
        hasEventOutcomeDetail = addProperty("hasEventOutcomeDetail");
        hasEventOutcomeDetailNote = addProperty("hasEventOutcomeDetailNote");
        hasEventOutcomeInformation = addProperty("hasEventOutcomeInformation");
        hasEventRelatedAgent = addProperty("hasEventRelatedAgent");
        hasEventRelatedObject = addProperty("hasEventRelatedObject");
        hasEventType = addProperty("hasEventType");
        hasFixity = addProperty("hasFixity");
        hasFormat = addProperty("hasFormat");
        hasFormatDesignation = addProperty("hasFormatDesignation");
        hasFormatName = addProperty("hasFormatName");
        hasFormatNote = addProperty("hasFormatNote");
        hasFormatRegistry = addProperty("hasFormatRegistry");
        hasFormatRegistryKey = addProperty("hasFormatRegistryKey");
        hasFormatRegistryName = addProperty("hasFormatRegistryName");
        hasFormatRegistryRole = addProperty("hasFormatRegistryRole");
        hasFormatVersion = addProperty("hasFormatVersion");
        hasHardware = addProperty("hasHardware");
        hasHardwareName = addProperty("hasHardwareName");
        hasHardwareOtherInformation = addProperty("hasHardwareOtherInformation");
        hasHardwareType = addProperty("hasHardwareType");
        hasIdentifier = addProperty("hasIdentifier");
        hasIdentifierType = addProperty("hasIdentifierType");
        hasIdentifierValue = addProperty("hasIdentifierValue");
        hasInhibitorKey = addProperty("hasInhibitorKey");
        hasInhibitors = addProperty("hasInhibitors");
        hasInhibitorTarget = addProperty("hasInhibitorTarget");
        hasInhibitorType = addProperty("hasInhibitorType");
        hasIntellectualEntity = addProperty("hasIntellectualEntity");
        hasKeyInformation = addProperty("hasKeyInformation");
        hasLicenseTerms = addProperty("hasLicenseTerms");
        hasMessageDigest = addProperty("hasMessageDigest");
        hasMessageDigestAlgorithm = addProperty("hasMessageDigestAlgorithm");
        hasMessageDigestOriginator = addProperty("hasMessageDigestOriginator");
        hasObject = addProperty("hasObject");
        hasObjectCharacteristics = addProperty("hasObjectCharacteristics");
        hasOriginalName = addProperty("hasOriginalName");
        hasPreservationLevel = addProperty("hasPreservationLevel");
        hasPreservationLevelDateAssigned = addProperty("hasPreservationLevelDateAssigned");
        hasPreservationLevelRationale = addProperty("hasPreservationLevelRationale");
        hasPreservationLevelRole = addProperty("hasPreservationLevelRole");
        hasPreservationLevelValue = addProperty("hasPreservationLevelValue");
        hasRelatedObject = addProperty("hasRelatedObject");
        hasRelatedObjectSequence = addProperty("hasRelatedObjectSequence");
        hasRelatedStatuteInformation = addProperty("hasRelatedStatuteInformation");
        hasRelationship = addProperty("hasRelationship");
        hasRestriction = addProperty("hasRestriction");
        hasRightsDocumentation = addProperty("hasRightsDocumentation");
        hasRightsDocumentationRole = addProperty("hasRightsDocumentationRole");
        hasRightsGranted = addProperty("hasRightsGranted");
        hasRightsGrantedNote = addProperty("hasRightsGrantedNote");
        hasRightsRelatedAgent = addProperty("hasRightsRelatedAgent");
        hasRightsStatement = addProperty("hasRightsStatement");
        hasRightsStatementNote = addProperty("hasRightsStatementNote");
        hasSignature = addProperty("hasSignature");
        hasSignatureEncoding = addProperty("hasSignatureEncoding");
        hasSignatureMethod = addProperty("hasSignatureMethod");
        hasSignatureProperties = addProperty("hasSignatureProperties");
        hasSignatureValidationRules = addProperty("hasSignatureValidationRules");
        hasSignatureValue = addProperty("hasSignatureValue");
        hasSigner = addProperty("hasSigner");
        hasSignificantProperties = addProperty("hasSignificantProperties");
        hasSignificantPropertiesType = addProperty("hasSignificantPropertiesType");
        hasSignificantPropertiesValue = addProperty("hasSignificantPropertiesValue");
        hasSize = addProperty("hasSize");
        hasSoftware = addProperty("hasSoftware");
        hasSoftwareDependency = addProperty("hasSoftwareDependency");
        hasSoftwareName = addProperty("hasSoftwareName");
        hasSoftwareOtherInformation = addProperty("hasSoftwareOtherInformation");
        hasSoftwareType = addProperty("hasSoftwareType");
        hasSoftwareVersion = addProperty("hasSoftwareVersion");
        hasStartDate = addProperty("hasStartDate");
        hasStatuteCitation = addProperty("hasStatuteCitation");
        hasStatuteInformationDeterminationDate = addProperty("hasStatuteInformationDeterminationDate");
        hasStatuteJurisdiction = addProperty("hasStatuteJurisdiction");
        hasStorage = addProperty("hasStorage");
        hasStorageMedium = addProperty("hasStorageMedium");
        hasTermOfGrant = addProperty("hasTermOfGrant");
        hasTermOfRestriction = addProperty("hasTermOfRestriction");
        lock();
    }

    public static final RDFResource AGENT = INSTANCE.Agent;
    public static final RDFResource APPLICABLE_DATES = INSTANCE.ApplicableDates;
    public static final RDFResource BIT_STREAM = INSTANCE.Bitstream;
    public static final RDFResource CONTENT_LOCATION = INSTANCE.ContentLocation;
    public static final RDFResource COPYRIGHT_INFORMATION = INSTANCE.CopyrightInformation;
    public static final RDFResource CREATING_APPLICATION = INSTANCE.CreatingApplication;
    public static final RDFResource DEPENDENCY = INSTANCE.Dependency;
    public static final RDFResource ENVIRONMENT = INSTANCE.Environment;
    public static final RDFResource EVENT = INSTANCE.Event;
    public static final RDFResource EVENT_OUTCOME_DETAIL = INSTANCE.EventOutcomeDetail;
    public static final RDFResource EVENT_OUTCOME_INFORMATION = INSTANCE.EventOutcomeInformation;
    public static final RDFResource FILE = INSTANCE.File;
    public static final RDFResource FIXITY = INSTANCE.Fixity;
    public static final RDFResource FORMAT = INSTANCE.Format;
    public static final RDFResource FORMAT_DESIGNATION = INSTANCE.FormatDesignation;
    public static final RDFResource FORMAT_REGISTRY = INSTANCE.FormatRegistry;
    public static final RDFResource HARDWARE = INSTANCE.Hardware;
    public static final RDFResource IDENTIFIER = INSTANCE.Identifier;
    public static final RDFResource INHIBITORS = INSTANCE.Inhibitors;
    public static final RDFResource INTELLECTUAL_ENTITY = INSTANCE.IntellectualEntity;
    public static final RDFResource LICENSE_INFORMATION = INSTANCE.LicenseInformation;
    public static final RDFResource OBJECT = INSTANCE.Object;
    public static final RDFResource OBJECT_CHARACTERISTICS = INSTANCE.ObjectCharacteristics;
    public static final RDFResource PREMIS_ENTITY = INSTANCE.PremisEntity;
    public static final RDFResource PRESERVATION_LEVEL = INSTANCE.PreservationLevel;
    public static final RDFResource RELATED_OBJECT_IDENTIFICATION = INSTANCE.RelatedObjectIdentification;
    public static final RDFResource REPRESENTATION = INSTANCE.Representation;
    public static final RDFResource RIGHTS_DOCUMENTATION = INSTANCE.RightsDocumentation;
    public static final RDFResource RIGHTS_GRANTED = INSTANCE.RightsGranted;
    public static final RDFResource RIGHTS_STATEMENT = INSTANCE.RightsStatement;
    public static final RDFResource SIGNATURE = INSTANCE.Signature;
    public static final RDFResource SIGNIFICANT_PROPERTIES = INSTANCE.SignificantProperties;
    public static final RDFResource SOFTWARE = INSTANCE.Software;
    public static final RDFResource STATUTE_INFORMATION = INSTANCE.StatuteInformation;
    public static final RDFResource STORAGE = INSTANCE.Storage;
    public static final RDFResource TERM_OF_GRANT = INSTANCE.TermOfGrant;
    public static final RDFResource TERM_OF_RESTRICTION = INSTANCE.TermOfRestriction;

    public static final RDFProperty HAS_ACT = INSTANCE.hasAct;
    public static final RDFProperty HAS_AGENT = INSTANCE.hasAgent;
    public static final RDFProperty HAS_AGENT_NAME = INSTANCE.hasAgentName;
    public static final RDFProperty HAS_AGENT_NOTE = INSTANCE.hasAgentNote;
    public static final RDFProperty HAS_AGENT_TYPE = INSTANCE.hasAgentType;
    public static final RDFProperty HAS_APPLICABLE_DATES = INSTANCE.hasApplicableDates;
    public static final RDFProperty HAS_COMPOSITION_LEVEL = INSTANCE.hasCompositionLevel;
    public static final RDFProperty HAS_CONTENT_LOCATION = INSTANCE.hasContentLocation;
    public static final RDFProperty HAS_CONTENT_LOCATION_TYPE = INSTANCE.hasContentLocationType;
    public static final RDFProperty HAS_CONTENT_LOCATION_VALUE = INSTANCE.hasContentLocationValue;
    public static final RDFProperty HAS_Copyright_Jurisdiction = INSTANCE.hasCopyrightJurisdiction;
    public static final RDFProperty HAS_COPYRIGHT_STATUS = INSTANCE.hasCopyrightStatus;
    public static final RDFProperty HAS_COPYRIGHT_STATUS_DETERMINATION_DATE = INSTANCE.hasCopyrightStatusDeterminationDate;
    public static final RDFProperty HAS_CREATING_APPLICATION = INSTANCE.hasCreatingApplication;
    public static final RDFProperty HAS_CREATING_APPLICATION_NAME = INSTANCE.hasCreatingApplicationName;
    public static final RDFProperty HAS_CREATING_APPLICATION_VERSION = INSTANCE.hasCreatingApplicationVersion;
    public static final RDFProperty HAS_DATE_CREATED_BY_APPLICATION = INSTANCE.hasDateCreatedByApplication;
    public static final RDFProperty HAS_DEPENDENCY = INSTANCE.hasDependency;
    public static final RDFProperty HAS_DEPENDENCY_NAME = INSTANCE.hasDependencyName;
    public static final RDFProperty HAS_END_DATE = INSTANCE.hasEndDate;
    public static final RDFProperty HAS_ENVIRONMENT = INSTANCE.hasEnvironment;
    public static final RDFProperty HAS_ENVIRONMENT_CHARACTERISTIC = INSTANCE.hasEnvironmentCharacteristic;
    public static final RDFProperty HAS_ENVIRONMENT_NOTE = INSTANCE.hasEnvironmentNote;
    public static final RDFProperty HAS_ENVIRONMENT_PURPOSE = INSTANCE.hasEnvironmentPurpose;
    public static final RDFProperty HAS_EVENT = INSTANCE.hasEvent;
    public static final RDFProperty HAS_EVENT_DATE_TIME = INSTANCE.hasEventDateTime;
    public static final RDFProperty HAS_EVENT_DETAIL = INSTANCE.hasEventDetail;
    public static final RDFProperty HAS_EVENT_OUTCOME = INSTANCE.hasEventOutcome;
    public static final RDFProperty HAS_EVENT_OUTCOME_DETAIL = INSTANCE.hasEventOutcomeDetail;
    public static final RDFProperty HAS_EVENT_OUTCOME_DETAIL_NOTE = INSTANCE.hasEventOutcomeDetailNote;
    public static final RDFProperty HAS_EVENT_OUTCOME_INFORMATION = INSTANCE.hasEventOutcomeInformation;
    public static final RDFProperty HAS_EVENT_RELATED_AGENT = INSTANCE.hasEventRelatedAgent;
    public static final RDFProperty HAS_EVENT_RELATED_OBJECT = INSTANCE.hasEventRelatedObject;
    public static final RDFProperty HAS_EVENT_TYPE = INSTANCE.hasEventType;
    public static final RDFProperty HAS_FIXITY = INSTANCE.hasFixity;
    public static final RDFProperty HAS_FORMAT = INSTANCE.hasFormat;
    public static final RDFProperty HAS_FORMAT_DESIGNATION = INSTANCE.hasFormatDesignation;
    public static final RDFProperty HAS_FORMAT_NAME = INSTANCE.hasFormatName;
    public static final RDFProperty HAS_FORMAT_NOTE = INSTANCE.hasFormatNote;
    public static final RDFProperty HAS_FORMAT_REGISTRY = INSTANCE.hasFormatRegistry;
    public static final RDFProperty HAS_FORMAT_REGISTRY_KEY = INSTANCE.hasFormatRegistryKey;
    public static final RDFProperty HAS_FORMAT_REGISTRY_NAME = INSTANCE.hasFormatRegistryName;
    public static final RDFProperty HAS_FORMAT_REGISTRY_ROLE = INSTANCE.hasFormatRegistryRole;
    public static final RDFProperty HAS_FORMAT_VERSION = INSTANCE.hasFormatVersion;
    public static final RDFProperty HAS_HARDWARE = INSTANCE.hasHardware;
    public static final RDFProperty HAS_HARDWARE_NAME = INSTANCE.hasHardwareName;
    public static final RDFProperty HAS_HARDWARE_OTHER_INFORMATION = INSTANCE.hasHardwareOtherInformation;
    public static final RDFProperty HAS_HARDWARE_TYPE = INSTANCE.hasHardwareType;
    public static final RDFProperty HAS_IDENTIFIER = INSTANCE.hasIdentifier;
    public static final RDFProperty HAS_IDENTIFIER_TYPE = INSTANCE.hasIdentifierType;
    public static final RDFProperty HAS_IDENTIFIER_VALUE = INSTANCE.hasIdentifierValue;
    public static final RDFProperty HAS_INHIBITOR_KEY = INSTANCE.hasInhibitorKey;
    public static final RDFProperty HAS_INHIBITORS = INSTANCE.hasInhibitors;
    public static final RDFProperty HAS_INHIBITOR_TARGET = INSTANCE.hasInhibitorTarget;
    public static final RDFProperty HAS_INHIBITOR_TYPE = INSTANCE.hasInhibitorType;
    public static final RDFProperty HAS_INTELLECTUAL_ENTITY = INSTANCE.hasIntellectualEntity;
    public static final RDFProperty HAS_KEY_INFORMATION = INSTANCE.hasKeyInformation;
    public static final RDFProperty HAS_LICENSE_TERMS = INSTANCE.hasLicenseTerms;
    public static final RDFProperty HAS_MESSAGE_DIGEST = INSTANCE.hasMessageDigest;
    public static final RDFProperty HAS_MESSAGE_DIGEST_ALGORITHM = INSTANCE.hasMessageDigestAlgorithm;
    public static final RDFProperty HAS_MESSAGE_DIGEST_ORIGINATOR = INSTANCE.hasMessageDigestOriginator;
    public static final RDFProperty HAS_OBJECT = INSTANCE.hasObject;
    public static final RDFProperty HAS_OBJECT_CHARACTERISTICS = INSTANCE.hasObjectCharacteristics;
    public static final RDFProperty HAS_ORIGINAL_NAME = INSTANCE.hasOriginalName;
    public static final RDFProperty HAS_PRESERVATION_LEVEL = INSTANCE.hasPreservationLevel;
    public static final RDFProperty HAS_PRESERVATION_LEVEL_DATE_ASSIGNED = INSTANCE.hasPreservationLevelDateAssigned;
    public static final RDFProperty HAS_PRESERVATION_LEVEL_RATIONALE = INSTANCE.hasPreservationLevelRationale;
    public static final RDFProperty HAS_PRESERVATION_LEVEL_ROLE = INSTANCE.hasPreservationLevelRole;
    public static final RDFProperty HAS_PRESERVATION_LEVEL_VALUE = INSTANCE.hasPreservationLevelValue;
    public static final RDFProperty HAS_RELATED_OBJECT = INSTANCE.hasRelatedObject;
    public static final RDFProperty HAS_RELATED_OBJECT_SEQUENCE = INSTANCE.hasRelatedObjectSequence;
    public static final RDFProperty HAS_RELATED_STATUTE_INFORMATION = INSTANCE.hasRelatedStatuteInformation;
    public static final RDFProperty HAS_RELATIONSHIP = INSTANCE.hasRelationship;
    public static final RDFProperty HAS_RESTRICTION = INSTANCE.hasRestriction;
    public static final RDFProperty HAS_RIGHTS_DOCUMENTATION = INSTANCE.hasRightsDocumentation;
    public static final RDFProperty HAS_RIGHTS_DOCUMENTATION_ROLE = INSTANCE.hasRightsDocumentationRole;
    public static final RDFProperty HAS_RIGHTS_GRANTED = INSTANCE.hasRightsGranted;
    public static final RDFProperty HAS_RIGHTS_GRANTED_NOTE = INSTANCE.hasRightsGrantedNote;
    public static final RDFProperty HAS_RIGHTS_RELATED_AGENT = INSTANCE.hasRightsRelatedAgent;
    public static final RDFProperty HAS_RIGHTS_STATEMENT = INSTANCE.hasRightsStatement;
    public static final RDFProperty HAS_RIGHTS_STATEMENT_NOTE = INSTANCE.hasRightsStatementNote;
    public static final RDFProperty HAS_SIGNATURE = INSTANCE.hasSignature;
    public static final RDFProperty HAS_SIGNATURE_ENCODING = INSTANCE.hasSignatureEncoding;
    public static final RDFProperty HAS_SIGNATURE_METHOD = INSTANCE.hasSignatureMethod;
    public static final RDFProperty HAS_SIGNATURE_PROPERTIES = INSTANCE.hasSignatureProperties;
    public static final RDFProperty HAS_SIGNATURE_VALIDATION_RULES = INSTANCE.hasSignatureValidationRules;
    public static final RDFProperty HAS_SIGNATURE_VALUE = INSTANCE.hasSignatureValue;
    public static final RDFProperty HAS_SIGNER = INSTANCE.hasSigner;
    public static final RDFProperty HAS_SIGNIFICANT_PROPERTIES = INSTANCE.hasSignificantProperties;
    public static final RDFProperty HAS_SIGNIFICANT_PROPERTIES_TYPE = INSTANCE.hasSignificantPropertiesType;
    public static final RDFProperty HAS_SIGNIFICANT_PROPERTIES_VALUE = INSTANCE.hasSignificantPropertiesValue;
    public static final RDFProperty HAS_SIZE = INSTANCE.hasSize;
    public static final RDFProperty HAS_SOFTWARE = INSTANCE.hasSoftware;
    public static final RDFProperty HAS_SOFTWARE_DEPENDENCY = INSTANCE.hasSoftwareDependency;
    public static final RDFProperty HAS_SOFTWARE_NAME = INSTANCE.hasSoftwareName;
    public static final RDFProperty HAS_SOFTWARE_OTHER_INFORMATION = INSTANCE.hasSoftwareOtherInformation;
    public static final RDFProperty HAS_SOFTWARE_TYPE = INSTANCE.hasSoftwareType;
    public static final RDFProperty HAS_SOFTWARE_VERSION = INSTANCE.hasSoftwareVersion;
    public static final RDFProperty HAS_START_DATE = INSTANCE.hasStartDate;
    public static final RDFProperty HAS_STATUTE_CITATION = INSTANCE.hasStatuteCitation;
    public static final RDFProperty HAS_STATUTE_INFORMATION_DETERMINATION_DATE = INSTANCE.hasStatuteInformationDeterminationDate;
    public static final RDFProperty HAS_STATUTE_JURISDICTION = INSTANCE.hasStatuteJurisdiction;
    public static final RDFProperty HAS_STORAGE = INSTANCE.hasStorage;
    public static final RDFProperty HAS_STORAGE_MEDIUM = INSTANCE.hasStorageMedium;
    public static final RDFProperty HAS_TERM_OF_GRANT = INSTANCE.hasTermOfGrant;
    public static final RDFProperty HAS_TERM_OF_RESTRICTION = INSTANCE.hasTermOfRestriction;

}