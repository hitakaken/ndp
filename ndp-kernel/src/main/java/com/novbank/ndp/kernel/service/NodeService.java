package com.novbank.ndp.kernel.service;

import com.novbank.ndp.kernel.model.Resource;
import com.novbank.ndp.kernel.rdfsupport.RDFStream;

import javax.jcr.Session;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by CaoKe on 2015/5/16.
 */
public interface NodeService extends DataService<Resource>{
    /**
     * Copy an existing object from the source path to the destination path
     * @param session the session
     * @param source the source
     * @param destination the destination
     */
    void copyObject(Session session, String source, String destination);

    /**
     * Move an existing object from the source path to the destination path
     * @param session the session
     * @param source the source
     * @param destination the destination
     */
    void moveObject(Session session, String source, String destination);

    /**
     * @param session the session
     * @return RDFStream of node types
     */
    RDFStream getNodeTypes(final Session session);

    /**
     * @param session the session
     * @param cndStream the cnd stream
     * @throws IOException if IO exception occurred
     */
    void registerNodeTypes(final Session session, final InputStream cndStream) throws IOException;
}
