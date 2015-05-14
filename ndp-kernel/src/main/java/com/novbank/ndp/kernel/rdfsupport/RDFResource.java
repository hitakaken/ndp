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

    public String getNameSpace() {
        return NS.getNamespace();
    }

    public String getPrefix(){
        return NS.getPrefix();
    }

    public String getLocalName() {
        return localName;
    }

    public String getAbbreviation(){
        return abbreviation;
    }
}
