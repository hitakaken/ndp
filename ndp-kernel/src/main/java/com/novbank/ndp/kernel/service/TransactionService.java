package com.novbank.ndp.kernel.service;

import com.novbank.ndp.kernel.service.transaction.Transaction;

import javax.jcr.Session;

/**
 * Created by CaoKe on 2015/5/16.
 */
public interface TransactionService {
    /**
     * Check for expired transactions and remove them
     */
    void removeAndRollbackExpired();

    /**
     * Create a new Transaction and add it to the currently open ones
     *
     * @param sess The session to use for this Transaction
     * @param userName the user name
     * @return the {@link Transaction}
     */
    Transaction beginTransaction(Session sess, String userName);

    /**
     * Receive an open {@link Transaction} for a given user
     *
     * @param txId the Id of the {@link Transaction}
     * @param userName the name  of the {@link java.security.Principal}
     * @return the {@link Transaction}
     * with this user
     */
    Transaction getTransaction(final String txId, final String userName);

    /**
     * Get the current Transaction for a session
     *
     * @param session the session
     * @return transaction
     */
    Transaction getTransaction(Session session);

    /**
     * Check if a Transaction exists
     *
     * @param txid the Id of the {@link Transaction}
     * @return the {@link Transaction}
     */
    boolean exists(String txid);

    /**
     * Commit a {@link Transaction} with the given id
     *
     * @param txid the id of the {@link Transaction}
     * @return transaction
     */
    Transaction commit(String txid);

    /**
     * Roll a {@link Transaction} back
     *
     * @param txid the id of the {@link Transaction}
     * @return the {@link Transaction} object
     */
    Transaction rollback(String txid);
}
