package com.novbank.ndp.kernel.rdfsupport;

import com.novbank.ndp.kernel.exception.MalformedRdfException;

import javax.jcr.AccessDeniedException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hp on 2015/5/18.
 */
public interface RDFModelChangedListener {
    /**
     Method to call when a single statement has been added to the attached model.
     @param s the statement that has been presented for addition.
     */
    void addedStatement( RDFTriple s );

    /**
     Method to call when an array of statements has been added to the attached
     model. NOTE. This array need not be == to the array added using
     Model::add(Statement[]).
     @param statements the array of added statements
     */
    void addedStatements( RDFTriple [] statements );

    /**
     Method to call when a list of statements has been added to the attached model.
     NOTE. This list need not be == to the list added using Model::add(List).
     @param statements the list of statements that has been removed.
     */
    void addedStatements( List<RDFTriple> statements );

    /**
     Method to call when a statement iterator has supplied elements to be added
     to the attached model. <code>statements</code> is a copy of the
     original iterator.
     @param statements
     */
    void addedStatements( Iterator<RDFTriple> statements );

    /**
     Method to call when a model has been used to define the statements to
     be added to our attached model.
     @param m a model equivalent to [and sharing with] the added model
     */
    void addedStatements( RDFModel m );

    /**
     Method to call when a single statement has been removed from the attached model.
     @param s the statement that has been presented for removal.
     */
    void removedStatement( RDFTriple s );

    /**
     Method to call when an array of statements has been removed from the
     attached model. NOTE. This array need not be == to the array added using
     Model::remove(Statement[]).
     @param statements the array of removed statements
     */
    void removedStatements( RDFTriple [] statements );

    /**
     Method to call when a list of statements has been deleted from the attached
     model. NOTE. This list need not be == to the list added using
     Model::remov(List).
     @param statements the list of statements that have been removed.
     */
    void removedStatements( List<RDFTriple> statements );

    /**
     Method to call when a statement iterator has been used to remove
     statements from the attached model. The iterator will be a copy, in the
     correct order, of the iterator supplied for the removal.
     @param statements a statement-type copy of the updating iterator
     */
    void removedStatements( Iterator<RDFTriple> statements );

    /**
     Method to call when a model has been used to remove statements from
     our attached model.
     @param m a model equivalent to [and sharing with] the one removed
     */

    void removedStatements( RDFModel m );

    void notifyEvent( RDFModel m, Object event );

    /**
     * Assert that no exceptions were thrown while this listener was processing change
     * @throws MalformedRdfException if malformed rdf exception occurred
     * @throws javax.jcr.AccessDeniedException if access denied exception occurred
     */
    void assertNoExceptions() throws MalformedRdfException, AccessDeniedException;
}
