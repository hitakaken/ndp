package com.novbank.ndp.kernel.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

/**
 * Created by CaoKe on 2015/5/16.
 */
public interface ExternalContentService {
    /**
     * Fetch the content body at a given URI
     * @param sourceUri the source uri
     * @return an InputStream of the content body
     * @throws IOException if IO exception occurred
     */
    InputStream retrieveExternalContent(URI sourceUri) throws IOException;
}
