package com.novbank.ndp.kernel.rdfsupport;

import com.novbank.ndp.kernel.model.Resource;
import com.novbank.ndp.kernel.util.convert.Converter;

/**
 * Created by hp on 2015/5/18.
 */
public interface RDFModelFactory extends Converter<RDFResource,Resource> {
    RDFModel asModel(Iterable<RDFTriple> triples);
    RDFModel asModel(RDFStream stream);
    RDFStream fromModel(RDFModel model);
}
