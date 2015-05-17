package com.novbank.ndp.kernel.service;

import javax.jcr.Node;
import javax.jcr.Session;

/**
 * Created by CaoKe on 2015/5/16.
 */
public interface DataService<T> {
    /**
     * Test whether T exists at the given path in the
     * repository
     *
     * @param path the path
     * @param session the session
     * @return whether T exists at the given path
     */
    boolean exists(final Session session, final String path);

    /**
     * Retrieve an existing T instance by session and path
     *
     * @param path jcr path to the node
     * @param session the session
     * @return retrieved T
     */
    T find(final Session session, final String path);

    /**
     * Retrieve a T instance by session and path
     *
     * @param session the session
     * @param path jcr path to the node
     * @return retrieved T
     */
    T findOrCreate(final Session session, final String path);

    /**
     * Retrieve a T instance from a node
     *
     * @param node the node
     * @return node as T
     */
    T cast(Node node);
}
