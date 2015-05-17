package com.novbank.ndp.kernel.vocabulary;

import com.novbank.ndp.kernel.rdfsupport.RDFProperty;
import com.novbank.ndp.kernel.rdfsupport.RDFResource;
import com.novbank.ndp.kernel.rdfsupport.RDFVocabulary;

/**
 * NovBank Data Platform 词汇表
 *
 * Created by CaoKe on 2015/5/12.
 */
public class NDP extends RDFVocabulary{
    public static final String NAMESPACE = "http://www.novbank.com/rdfs/ndp#";
    public static final String PREFIX = "ndp";
    public static final NDP INSTANCE = new NDP();

    public final RDFResource Resource;
    public final RDFResource Tombstone;
    //public final RDFResource NonRDFSource;
    public final RDFResource NonRDFSourceDescription;
    public final RDFResource Binary;
    public final RDFResource PairTree;
    public final RDFResource Container;
    public final RDFResource BlankNode;

    public final RDFProperty digest;
    public final RDFProperty metadata;
    public final RDFProperty versions;

    private NDP() {
        super(NAMESPACE, PREFIX);
        Resource = addClass("Resource");
        Tombstone = addClass("Tombstone");
        //NonRDFSource = addClass("NonRDFSource");
        NonRDFSourceDescription = addClass("NonRDFSourceDescription");
        Binary = addClass("Binary");
        PairTree = addClass("PairTree");
        Container = addClass("Container");
        BlankNode = addClass("BlankNode");

        digest = addProperty("digest");
        metadata = addProperty("metadata");
        versions = addProperty("versions");
        lock();
    }

    public static final RDFResource RESOURCE = INSTANCE.Resource;
    public static final RDFResource TOMBSTONE = INSTANCE.Tombstone;
    //public static final RDFResource Non_RDF_Source = INSTANCE.NonRDFSource;
    public static final RDFResource NON_RDF_SOURCE_DESCRIPTION = INSTANCE.NonRDFSourceDescription;
    public static final RDFResource BINARY = INSTANCE.Binary;
    public static final RDFResource PAIR_TREE = INSTANCE.PairTree;
    public static final RDFResource CONTAINER = INSTANCE.Container;
    public static final RDFResource BLANK_NODE = INSTANCE.BlankNode;

    public static final RDFProperty DIGEST = INSTANCE.digest;
    public static final RDFProperty METADATA = INSTANCE.metadata;
    public static final RDFProperty VERSIONS = INSTANCE.versions;
}
