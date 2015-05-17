package com.novbank.ndp.kernel.rdfsupport;

/**
 * Created by CaoKe on 2015/5/12.
 */
public class RDFResource extends RDFValue {
    protected final Namespace NS;
    protected final String localName;
    protected final String abbreviation;

    public RDFResource(Namespace NS, String localName) {
        super((NS.getNamespace().endsWith("#")||NS.getNamespace().endsWith("/")?NS.getNamespace():"{"+NS.getNamespace()+"}") + localName);
        this.NS = NS;
        this.localName = localName;
        this.abbreviation = NS.getPrefix() + ":" + localName;
    }

    public String namespace() {
        return NS.getNamespace();
    }

    public String prefix(){
        return NS.getPrefix();
    }

    public Namespace NS(){
        return NS;
    }

    public String localName() {
        return localName;
    }

    public String abbr(){
        return abbreviation;
    }
}
