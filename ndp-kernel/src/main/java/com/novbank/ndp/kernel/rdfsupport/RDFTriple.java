package com.novbank.ndp.kernel.rdfsupport;

import org.javatuples.Triplet;

/**
 * Created by hp on 2015/5/14.
 */
public class RDFTriple<S extends RDFResource,P extends RDFProperty,O extends RDFValue>{
    protected Triplet<S,P,O> triplet;
    public RDFTriple(S subj, P pred, O obj) {
        triplet = new Triplet<>(subj, pred, obj);
    }

    public final S getSubject(){
        return triplet.getValue0();
    }

    public final P getPredicate(){
        return triplet.getValue1();
    }

    public final O getObject(){
        return triplet.getValue2();
    }
}
