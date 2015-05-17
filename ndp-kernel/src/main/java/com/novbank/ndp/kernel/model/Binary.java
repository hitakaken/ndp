package com.novbank.ndp.kernel.model;

import com.novbank.ndp.kernel.exception.InvalidChecksumException;
import com.novbank.ndp.kernel.rdfsupport.RDFStream;

import java.io.InputStream;
import java.net.URI;

/**
 * Created by CaoKe on 2015/5/16.
 */
public interface Binary extends NonRDFSource {
    /**
     * @return The InputStream of content associated with this datastream.
     */
    InputStream getContent();


    /**
     * @return The Binary content associated with this datastream.
     */
    javax.jcr.Binary getBinaryContent();

    /**
     * Sets the content of this Datastream.
     *
     * @param content  InputStream of binary content to be stored
     * @param contentType MIME type of content (optional)
     * @param checksum Checksum URI of the content (optional)
     * @param originalFileName Original file name of the content (optional)
     * @param storagePolicyDecisionPoint Policy decision point for storing the content (optional)
     * @throws com.novbank.ndp.kernel.exception.InvalidChecksumException if invalid checksum exception occurred
     */
    void setContent(InputStream content, String contentType, URI checksum,
                    String originalFileName, StoragePolicyDecisionPoint storagePolicyDecisionPoint)
            throws InvalidChecksumException;

    /**
     * @return The size in bytes of content associated with this datastream.
     */
    long getContentSize();

    /**
     * Get the pre-calculated content digest for the binary payload
     *
     * @return a URI with the format algorithm:value
     */
    URI getContentDigest();

    /**
     * @return The MimeType of content associated with this datastream.
     */
    String getMimeType();

    /**
     * Return the file name for the binary content
     *
     * @return original file name for the binary content, or the object's id.
     */
    String getFilename();

    /**
     * Get the fixity of this datastream compared to metadata stored in the repository
     *
     * @return the fixity of this datastream compared to metadata stored in the repository
     */
    RDFStream getFixity();

    /**
     * Get the fixity of this datastream in a given repository's binary store.
     *
     * @param contentDigest the checksum to compare against
     * @param size the expected size of the binary
     * @return the fixity of the datastream
     */
    RDFStream getFixity(URI contentDigest, long size);
}
