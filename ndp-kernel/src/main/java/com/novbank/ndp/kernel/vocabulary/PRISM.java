package com.novbank.ndp.kernel.vocabulary;

import com.novbank.ndp.kernel.rdfsupport.RDFProperty;
import com.novbank.ndp.kernel.rdfsupport.RDFVocabulary;

/**
 * Publishing Requirements for Industry Standard Metadata
 *
 * Created by CaoKe on 2015/5/13.
 */
public class PRISM extends RDFVocabulary{
    public static final String NAMESPACE = "http://prismstandard.org/namespaces/1.2/basic/";
    public static final String PREFIX = "prism";
    public static final PRISM INSTANCE = new PRISM();

    public final RDFProperty byteCount;
    public final RDFProperty category;
    public final RDFProperty complianceProfile;
    public final RDFProperty copyright;
    public final RDFProperty corporateEntity;
    public final RDFProperty coverDate;
    public final RDFProperty coverDisplayDate;
    public final RDFProperty distributor;
    public final RDFProperty edition;
    public final RDFProperty eIssn;
    public final RDFProperty embargoDate;
    public final RDFProperty endingPage;
    public final RDFProperty event;
    public final RDFProperty expirationDate;
    public final RDFProperty hasAlternative;
    public final RDFProperty hasCorrection;
    public final RDFProperty hasFormat;
    public final RDFProperty hasPart;
    public final RDFProperty hasPreviousVersion;
    public final RDFProperty hasTranslation;
    public final RDFProperty industry;
    public final RDFProperty isCorrectionOf;
    public final RDFProperty isFormatOf;
    public final RDFProperty isPartOf;
    public final RDFProperty isReferencedBy;
    public final RDFProperty isRequiredBy;
    public final RDFProperty issn;
    public final RDFProperty issueIdentifier;
    public final RDFProperty issueName;
    public final RDFProperty isTranslationOf;
    public final RDFProperty isVersionOf;
    public final RDFProperty location;
    public final RDFProperty modificationDate;
    public final RDFProperty number;
    public final RDFProperty objectTitle;
    public final RDFProperty organization;
    public final RDFProperty person;
    public final RDFProperty publicationDate;
    public final RDFProperty publicationName;
    public final RDFProperty receptionDate;
    public final RDFProperty references;
    public final RDFProperty requires;
    public final RDFProperty rightsAgent;
    public final RDFProperty section;
    public final RDFProperty startingPage;
    public final RDFProperty subsection1;
    public final RDFProperty subsection2;
    public final RDFProperty teaser;
    public final RDFProperty volume;
    public final RDFProperty wordCount;

    private PRISM() {
        super(NAMESPACE,PREFIX);
        byteCount = addProperty("byteCount");
        category = addProperty("category");
        complianceProfile = addProperty("complianceProfile");
        copyright = addProperty("copyright");
        corporateEntity = addProperty("corporateEntity");
        coverDate = addProperty("coverDate");
        coverDisplayDate = addProperty("coverDisplayDate");
        distributor = addProperty("distributor");
        edition = addProperty("edition");
        eIssn = addProperty("eIssn");
        embargoDate = addProperty("embargoDate");
        endingPage = addProperty("endingPage");
        event = addProperty("event");
        expirationDate = addProperty("expirationDate");
        hasAlternative = addProperty("hasAlternative");
        hasCorrection = addProperty("hasCorrection");
        hasFormat = addProperty("hasFormat");
        hasPart = addProperty("hasPart");
        hasPreviousVersion = addProperty("hasPreviousVersion");
        hasTranslation = addProperty("hasTranslation");
        industry = addProperty("industry");
        isCorrectionOf = addProperty("isCorrectionOf");
        isFormatOf = addProperty("isFormatOf");
        isPartOf = addProperty("isPartOf");
        isReferencedBy = addProperty("isReferencedBy");
        isRequiredBy = addProperty("isRequiredBy");
        issn = addProperty("issn");
        issueIdentifier = addProperty("issueIdentifier");
        issueName = addProperty("issueName");
        isTranslationOf = addProperty("isTranslationOf");
        isVersionOf = addProperty("isVersionOf");
        location = addProperty("location");
        modificationDate = addProperty("modificationDate");
        number = addProperty("number");
        objectTitle = addProperty("objectTitle");
        organization = addProperty("organization");
        person = addProperty("person");
        publicationDate = addProperty("publicationDate");
        publicationName = addProperty("publicationName");
        receptionDate = addProperty("receptionDate");
        references = addProperty("references");
        requires = addProperty("requires");
        rightsAgent = addProperty("rightsAgent");
        section = addProperty("section");
        startingPage = addProperty("startingPage");
        subsection1 = addProperty("subsection1");
        subsection2 = addProperty("subsection2");
        teaser = addProperty("teaser");
        volume = addProperty("volume");
        wordCount = addProperty("wordCount");
        lock();
    }

    public static final RDFProperty BYTE_COUNT = INSTANCE.byteCount;
    public static final RDFProperty CATEGORY = INSTANCE.category;
    public static final RDFProperty COMPLIANCE_PROFILE = INSTANCE.complianceProfile;
    public static final RDFProperty COPYRIGHT = INSTANCE.copyright;
    public static final RDFProperty CORPORATE_ENTITY = INSTANCE.corporateEntity;
    public static final RDFProperty COVER_DATE = INSTANCE.coverDate;
    public static final RDFProperty COVER_DISPLAY_DATE = INSTANCE.coverDisplayDate;
    public static final RDFProperty DISTRIBUTOR = INSTANCE.distributor;
    public static final RDFProperty EDITION = INSTANCE.edition;
    public static final RDFProperty E_ISSN = INSTANCE.eIssn;
    public static final RDFProperty EMBARGO_DATE = INSTANCE.embargoDate;
    public static final RDFProperty ENDING_PAGE = INSTANCE.endingPage;
    public static final RDFProperty EVENT = INSTANCE.event;
    public static final RDFProperty EXPIRATION_DATE = INSTANCE.expirationDate;
    public static final RDFProperty HAS_ALTERNATIVE = INSTANCE.hasAlternative;
    public static final RDFProperty HAS_CORRECTION = INSTANCE.hasCorrection;
    public static final RDFProperty HAS_FORMAT = INSTANCE.hasFormat;
    public static final RDFProperty HAS_PART = INSTANCE.hasPart;
    public static final RDFProperty HAS_PREVIOUS_VERSION = INSTANCE.hasPreviousVersion;
    public static final RDFProperty HAS_TRANSLATION = INSTANCE.hasTranslation;
    public static final RDFProperty INDUSTRY = INSTANCE.industry;
    public static final RDFProperty IS_CORRECTION_OF = INSTANCE.isCorrectionOf;
    public static final RDFProperty IS_FORMAT_OF = INSTANCE.isFormatOf;
    public static final RDFProperty IS_PART_OF = INSTANCE.isPartOf;
    public static final RDFProperty IS_REFERENCED_BY = INSTANCE.isReferencedBy;
    public static final RDFProperty IS_REQUIRED_BY = INSTANCE.isRequiredBy;
    public static final RDFProperty ISSN = INSTANCE.issn;
    public static final RDFProperty ISSUE_IDENTIFIER = INSTANCE.issueIdentifier;
    public static final RDFProperty ISSUE_NAME = INSTANCE.issueName;
    public static final RDFProperty IS_TRANSLATION_OF = INSTANCE.isTranslationOf;
    public static final RDFProperty IS_VERSION_OF = INSTANCE.isVersionOf;
    public static final RDFProperty LOCATION = INSTANCE.location;
    public static final RDFProperty MODIFICATION_DATE = INSTANCE.modificationDate;
    public static final RDFProperty NUMBER = INSTANCE.number;
    public static final RDFProperty OBJECT_TITLE = INSTANCE.objectTitle;
    public static final RDFProperty ORGANIZATION = INSTANCE.organization;
    public static final RDFProperty PERSON = INSTANCE.person;
    public static final RDFProperty PUBLICATION_DATE = INSTANCE.publicationDate;
    public static final RDFProperty PUBLICATION_NAME = INSTANCE.publicationName;
    public static final RDFProperty RECEPTION_DATE = INSTANCE.receptionDate;
    public static final RDFProperty REFERENCES = INSTANCE.references;
    public static final RDFProperty REQUIRES = INSTANCE.requires;
    public static final RDFProperty RIGHTS_AGENT = INSTANCE.rightsAgent;
    public static final RDFProperty SECTION = INSTANCE.section;
    public static final RDFProperty STARTING_PAGE = INSTANCE.startingPage;
    public static final RDFProperty SUBSECTION1 = INSTANCE.subsection1;
    public static final RDFProperty SUBSECTION2 = INSTANCE.subsection2;
    public static final RDFProperty TEASER = INSTANCE.teaser;
    public static final RDFProperty VOLUME = INSTANCE.volume;
    public static final RDFProperty WORD_COUNT = INSTANCE.wordCount;
}
