package com.novbank.ndp.kernel.model;

/**
 * Created by CaoKe on 2015/5/16.
 */
public interface NonRDFSourceDescription extends Resource {

    /**
     * @return The binary stored in this dataStream
     */
    NonRDFSource getDescribedResource();

}
