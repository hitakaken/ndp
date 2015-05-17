package com.novbank.ndp.kernel.service.policy;

import javax.jcr.Node;

/**
 * A binary storage policy definition and evaluation machinery
 *
 * Created by CaoKe on 2015/5/16.
 */
public interface StoragePolicy {
    /**
     * Evaluate the policy; if the policy matches, return the
     * binary storage hint. If not, return null.
     * @param n the node
     * @return the binary storage hint
     */
    String evaluatePolicy(Node n);
}
