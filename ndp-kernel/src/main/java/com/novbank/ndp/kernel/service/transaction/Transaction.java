package com.novbank.ndp.kernel.service.transaction;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.Date;

/**
 * Created by CaoKe on 2015/5/16.
 */
public interface Transaction {
    enum State {
        DIRTY, NEW, COMMITED, ROLLED_BACK
    }

    /**
     * Get the transaction-aware session
     * @return transaction-aware session
     */
    Session getSession();

    /**
     * Get the date this transaction was created
     * @return creation date
     */
    Date getCreated();

    /**
     * Get the transaction identifier
     * @return transaction id
     */
    String getId();

    /**
     * Get the state of this transaction
     * @return transaction state
     * @throws RepositoryException if repository exception occurred
     */
    State getState() throws RepositoryException;

    /**
     * Get the Date when this transaction is expired and can be
     * garbage-collected
     * @return transaction expiration date
     */
    Date getExpires();

    /**
     * "Commit" the transaction by saving the backing-session
     */
    void commit();

    /**
     * End the session, and mark for reaping
     */
    void expire();

    /**
     * Checks if this transaction is associated with a specific user.
     * If username is null, it is assumed that the transaction is
     * anonymous and thus not bound to any user.
     * @param userName the user
     * @return true if the userName is associated with this transaction
     */
    public boolean isAssociatedWithUser(final String userName);

    /**
     * Discard all unpersisted changes and expire
     */
    void rollback();

    /**
     * Roll forward the expiration date for recent activity
     */
    void updateExpiryDate();
}
