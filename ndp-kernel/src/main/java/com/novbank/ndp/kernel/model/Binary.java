package com.novbank.ndp.kernel.model;

import com.novbank.ndp.kernel.exception.InvalidChecksumException;
import com.novbank.ndp.kernel.exception.PathNotFoundRuntimeException;
import com.novbank.ndp.kernel.exception.RepositoryRuntimeException;
import com.novbank.ndp.kernel.rdfsupport.RDFModelFactory;
import com.novbank.ndp.kernel.rdfsupport.RDFStream;
import com.novbank.ndp.kernel.service.policy.StoragePolicyDecisionPoint;
import com.novbank.ndp.kernel.util.ContentDigest;

import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import static com.novbank.ndp.kernel.vocabulary.Vocabularies.JCR;
import static com.novbank.ndp.kernel.vocabulary.Vocabularies.NDP;
import static com.novbank.ndp.kernel.vocabulary.Vocabularies.PREMIS;

/**
 * Created by CaoKe on 2015/5/16.
 */
public interface Binary extends NonRDFSource {
    /**
     * @return The InputStream of content associated with this datastream.
     */
    default InputStream getContent(){
        try {
            return getBinaryContent().getStream();
        } catch (final RepositoryException e) {
            throw new RepositoryRuntimeException(e);
        }
    }


    /**
     * @return The Binary content associated with this datastream.
     */
    default javax.jcr.Binary getBinaryContent(){
        try {
            return getProperty(JCR.data.abbr()).getBinary();
        } catch (final PathNotFoundException e) {
            throw new PathNotFoundRuntimeException(e);
        } catch (final RepositoryException e) {
            throw new RepositoryRuntimeException(e);
        }
    }

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
    default long getContentSize(){
        try {
            if (hasProperty(PREMIS.hasSize.abbr())) {
                return getProperty(PREMIS.hasSize.abbr()).getLong();
            }
        } catch (final RepositoryException e) {
            getLogger().info("Could not get contentSize(): {}", e.getMessage());
        }
        return -1L;
    }

    /**
     * Get the pre-calculated content digest for the binary payload
     *
     * @return a URI with the format algorithm:value
     */
    default URI getContentDigest(){
        try {
            if (hasProperty(NDP.digest.abbr())) {
                return new URI(getProperty(NDP.digest.abbr()).getString());
            }
        } catch (final RepositoryException | URISyntaxException e) {
            getLogger().info("Could not get content digest: {}", e.getMessage());
        }
        return ContentDigest.missingChecksum();
    }

    /**
     * @return The MimeType of content associated with this datastream.
     */
    default String getMimeType() {
        try {
            if (hasProperty(JCR.mimeType.abbr())) {
                return getProperty(JCR.mimeType.abbr()).getString();
            }
            return "application/octet-stream";
        } catch (final RepositoryException e) {
            throw new RepositoryRuntimeException(e);
        }
    }

    /**
     * Return the file name for the binary content
     *
     * @return original file name for the binary content, or the object's id.
     */
    default String getFilename() {
        try {
            if (hasProperty(PREMIS.hasOriginalName.abbr())) {
                return getProperty(PREMIS.hasOriginalName.abbr()).getString();
            }
            return getNode().getParent().getName();
        } catch (final RepositoryException e) {
            throw new RepositoryRuntimeException(e);
        }
    }

    /**
     * Get the fixity of this dataStream compared to metadata stored in the repository
     *
     * @param factory
     * @return the fixity of this dataStream compared to metadata stored in the repository
     */
    RDFStream getFixity(RDFModelFactory factory);

    /**
     * Get the fixity of this dataStream in a given repository's binary store.
     *
     * @param factory
     * @param contentDigest the checksum to compare against
     * @param size the expected size of the binary
     * @return the fixity of the datastream
     */
    RDFStream getFixity(RDFModelFactory factory, URI contentDigest, long size);
}
