package com.novbank.ndp.kernel.vocabulary;

import com.novbank.ndp.kernel.rdfsupport.RDFResource;
import com.novbank.ndp.kernel.rdfsupport.RDFVocabulary;

/**
 * Created by CaoKe on 2015/5/13.
 */
public class JCR_MIX extends RDFVocabulary{
    public static final String NAMESPACE = "http://www.jcp.org/jcr/mix/1.0";
    public static final String PREFIX = "mix";
    public static final JCR_MIX INSTANCE = new JCR_MIX();

    public final RDFResource title;
    public final RDFResource created;
    public final RDFResource lastModified;
    public final RDFResource language;
    public final RDFResource mimeType;
    public final RDFResource nodeType;
    public final RDFResource propertyDefinition;
    public final RDFResource childNodeDefinition;
    public final RDFResource shareable;
    public final RDFResource lockable;
    public final RDFResource lifecycle;
    public final RDFResource simpleVersionable;
    public final RDFResource versionable;

    public JCR_MIX() {
        super(NAMESPACE,PREFIX);
        title = addResource("title");
        created = addResource("created");
        lastModified = addResource("lastModified");
        language = addResource("language");
        mimeType = addResource("mimeType");
        nodeType = addResource("nodeType");
        propertyDefinition = addResource("propertyDefinition");
        childNodeDefinition = addResource("childNodeDefinition");
        shareable = addResource("shareable");
        lockable = addResource("lockable");
        lifecycle = addResource("lifecycle");
        simpleVersionable = addResource("simpleVersionable");
        versionable = addResource("versionable");
        lock();
    }

    public static final RDFResource TITLE = INSTANCE.title;
    public static final RDFResource CREATED = INSTANCE.created;
    public static final RDFResource LAST_MODIFIED = INSTANCE.lastModified;
    public static final RDFResource LANGUAGE = INSTANCE.language;
    public static final RDFResource MIME_TYPE = INSTANCE.mimeType;
    public static final RDFResource NODE_TYPE = INSTANCE.nodeType;
    public static final RDFResource PROPERTY_DEFINITION = INSTANCE.propertyDefinition;
    public static final RDFResource CHILD_NODE_DEFINITION = INSTANCE.childNodeDefinition;
    public static final RDFResource SHAREABLE = INSTANCE.shareable;
    public static final RDFResource LOCKABLE = INSTANCE.lockable;
    public static final RDFResource LIFECYCLE = INSTANCE.lifecycle;
    public static final RDFResource SIMPLE_VERSIONABLE = INSTANCE.simpleVersionable;
    public static final RDFResource VERSIONABLE = INSTANCE.versionable;
}
