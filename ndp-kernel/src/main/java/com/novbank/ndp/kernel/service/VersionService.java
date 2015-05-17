package com.novbank.ndp.kernel.service;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

/**
 * Created by CaoKe on 2015/5/16.
 */
public interface VersionService {
    /**
     * Explicitly creates a version for the nodes at each path provided.
     *
     * @param session the session in which the node resides
     * @param absPath absolute paths to the node
     * @param label a label to be applied to the new version
     * @throws RepositoryException if repository exception occurred
     * @return the identifier
     */
    String createVersion(Session session, String absPath, String label) throws RepositoryException;

    /**
     * Reverts the node to the version identified by the label.  This method
     * will throw a PathNotFoundException if no version with the given label is
     * found.
     *
     * @param session the session in which the node resides
     * @param absPath the path to the node whose version is to be reverted
     * @param label identifies the historic version
     * @throws RepositoryException if repository exception occurred
     */
    void revertToVersion(Session session, String absPath, String label)
            throws RepositoryException;

    /**
     * Remove a version of a node.  This method will throw a PathNotFoundException
     * if no version with the given label is found.
     *
     * @param session the session in which the node resides
     * @param absPath the path to the node whose version is to be removed
     * @param label identifies the historic version by label or id
     * @throws RepositoryException if repository exception occurred
     */
    void removeVersion(Session session, String absPath, String label)
            throws RepositoryException;

}
