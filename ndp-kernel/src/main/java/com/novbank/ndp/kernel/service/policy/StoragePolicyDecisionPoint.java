package com.novbank.ndp.kernel.service.policy;

import javax.jcr.Node;
import java.util.List;

/**
 * Service Interface implementation for managing and using {@link com.novbank.ndp.kernel.service.policy.StoragePolicy}
 *
 * Created by CaoKe on 2015/5/16.
 */
public interface StoragePolicyDecisionPoint extends List<StoragePolicy> {
    /**
     * Given a JCR node (likely a jcr:content node), determine which storage
     * policy should apply
     *
     * @param n the node
     * @return storage policy
     */
    String evaluatePolicies(final Node n);

    /**
     * Explicitly set the policies this PDP should use
     *
     * @param policies the policies
     */
    void setPolicies(final List<StoragePolicy> policies);
}
