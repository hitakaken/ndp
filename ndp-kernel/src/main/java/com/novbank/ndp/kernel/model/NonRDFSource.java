package com.novbank.ndp.kernel.model;

/**
 * Created by CaoKe on 2015/5/12.
 */
public interface NonRDFSource extends Resource {
    /**
     * Get the description for this binary
     *
     * @return the description for this binary
     */
    NonRDFSourceDescript getDescription();
}
