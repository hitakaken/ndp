package com.novbank.ndp.kernel.vocabulary;

import com.novbank.ndp.kernel.rdfsupport.RDFResource;
import com.novbank.ndp.kernel.rdfsupport.RDFVocabulary;

/**
 * JCR NodeType
 *
 * Created by CaoKe on 2015/5/13.
 */
public class JCR_NT extends RDFVocabulary{
    public static final String NAMESPACE = "http://www.jcp.org/jcr/nt/1.0";
    public static final String PREFIX = "nt";
    public static final JCR_NT INSTANCE = new JCR_NT();

    public final RDFResource base;
    public final RDFResource hierarchyNode;
    public final RDFResource folder;
    public final RDFResource file;
    public final RDFResource linkedFile;
    public final RDFResource resource;
    public final RDFResource unstructured;
    public final RDFResource address;

    public final RDFResource versionHistory;
    public final RDFResource version;
    public final RDFResource frozenNode;
    public final RDFResource activity;
    public final RDFResource configuration;
    public final RDFResource query;

    private JCR_NT() {
        super(NAMESPACE,PREFIX);
        base = addResource("base");
        hierarchyNode = addResource("hierarchyNode");
        folder = addResource("folder");
        file = addResource("file");
        linkedFile = addResource("linkedFile");
        resource = addResource("resource");
        unstructured = addResource("unstructured");
        address = addResource("address");
        versionHistory = addResource("versionHistory");
        version = addResource("version");
        frozenNode = addResource("frozenNode");
        activity = addResource("activity");
        configuration = addResource("configuration");
        query = addResource("query");
        lock();
    }

    public static final RDFResource BASE = INSTANCE.base;
    public static final RDFResource HIERARCHY_NODE = INSTANCE.hierarchyNode;
    public static final RDFResource FOLDER = INSTANCE.folder;
    public static final RDFResource FILE = INSTANCE.file;
    public static final RDFResource LINKED_FILE = INSTANCE.linkedFile;
    public static final RDFResource RESOURCE = INSTANCE.resource;
    public static final RDFResource UNSTRUCTURED = INSTANCE.unstructured;
    public static final RDFResource ADDRESS = INSTANCE.address;
    public static final RDFResource VERSION_HISTORY = INSTANCE.versionHistory;
    public static final RDFResource VERSION = INSTANCE.version;
    public static final RDFResource FROZEN_NODE = INSTANCE.frozenNode;
    public static final RDFResource ACTIVITY = INSTANCE.activity;
    public static final RDFResource CONFIGURATION = INSTANCE.configuration;
    public static final RDFResource QUERY = INSTANCE.query;
}
