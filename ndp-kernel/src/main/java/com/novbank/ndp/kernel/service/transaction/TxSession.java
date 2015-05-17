package com.novbank.ndp.kernel.service.transaction;

import javax.jcr.Session;

/**
 * Additional methods introduced by our transaction-aware session
 *
 * Created by CaoKe on 2015/5/16.
 */
public interface TxSession  extends Session {
    /**
     * @return the transaction identifier associated with this session
     */
    String getTxId();
}
