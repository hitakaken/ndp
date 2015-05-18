package com.novbank.ndp.kernel.impl.model;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.Timer;
import com.novbank.ndp.kernel.exception.InvalidChecksumException;
import com.novbank.ndp.kernel.exception.RepositoryRuntimeException;
import com.novbank.ndp.kernel.impl.rdf.FixityRDFContext;
import com.novbank.ndp.kernel.impl.service.result.CacheEntryFactory;
import com.novbank.ndp.kernel.util.SecureHash;
import com.novbank.ndp.kernel.model.Binary;
import com.novbank.ndp.kernel.model.NonRDFSourceDescription;
import com.novbank.ndp.kernel.rdfsupport.RDFModelFactory;
import com.novbank.ndp.kernel.rdfsupport.RDFStream;
import com.novbank.ndp.kernel.service.policy.StoragePolicyDecisionPoint;
import com.novbank.ndp.kernel.service.result.FixityResult;
import com.novbank.ndp.kernel.util.ContentDigest;
import com.novbank.ndp.metrics.RegistryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.ValueFactory;
import javax.jcr.version.Version;
import javax.jcr.version.VersionHistory;
import java.io.InputStream;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;

import static com.codahale.metrics.MetricRegistry.name;
import static com.novbank.ndp.kernel.util.jcr.JcrTypesUtils.isBinary;
import static com.novbank.ndp.kernel.vocabulary.Vocabularies.*;

/**
 * Created by CaoKe on 2015/5/17.
 */
public class BinaryImpl extends ResourceImpl implements Binary {
    private static final Logger LOGGER = LoggerFactory.getLogger(BinaryImpl.class);

    static final RegistryService registryService = RegistryService.getInstance();
    static final Counter fixityCheckCounter
            = registryService.getMetrics().counter(name(Binary.class, "fixity-check-counter"));
    static final Timer timer = registryService.getMetrics().timer(
            name(NonRDFSourceDescription.class, "fixity-check-time"));
    static final Histogram contentSizeHistogram =
            registryService.getMetrics().histogram(name(Binary.class, "content-size"));

    /**
     * Wrap an existing Node as a NDP Binary
     * @param node the node
     */
    public BinaryImpl(final Node node) {
        super(node);
        if (node.isNew()) {
            initializeNewBinaryProperties();
        }
    }

    private void initializeNewBinaryProperties() {
        try {
            decorateContentNode(node);
        } catch (RepositoryException e) {
            LOGGER.warn("Count not decorate {} with NDP Binary properties: {}", node, e);
        }
    }

    @Override
    public NonRDFSourceDescription getDescription() {
        try {
            return new NonRDFSourceDescriptionImpl(getNode().getParent());
        } catch (final RepositoryException e) {
            throw new RepositoryRuntimeException(e);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.fcrepo.kernel.Datastream#setContent(java.io.InputStream,
     * java.lang.String, java.net.URI, java.lang.String,
     * org.fcrepo.kernel.services.policy.StoragePolicyDecisionPoint)
     */
    @Override
    public void setContent(final InputStream content, final String contentType,
                           final URI checksum, final String originalFileName,
                           final StoragePolicyDecisionPoint storagePolicyDecisionPoint)    throws InvalidChecksumException {
        try {
            final Node contentNode = getNode();
            if (contentNode.canAddMixin(NDP.Binary.abbr()))
                contentNode.addMixin(NDP.Binary.abbr());
            if (contentType != null)
                contentNode.setProperty(JCR.mimeType.abbr(), contentType);
            if (originalFileName != null)
                contentNode.setProperty(PREMIS.hasOriginalName.abbr(), originalFileName);
            LOGGER.debug("Created content node at path: {}", contentNode.getPath());
            String hint = null;
            if (storagePolicyDecisionPoint != null)
                hint = storagePolicyDecisionPoint.evaluatePolicies(node);
            final ValueFactory modevf = (ValueFactory) node.getSession().getValueFactory();
            final SecureHash.HashingInputStream his = content instanceof SecureHash.HashingInputStream ?
                    (SecureHash.HashingInputStream)content: SecureHash.createHashingStream(SecureHash.Algorithm.SHA_1,content);
            final javax.jcr.Binary binary = modevf.createBinary(his);
        /*
         * This next line of code deserves explanation. If we chose for the
         * simpler line: Property dataProperty =
         * contentNode.setProperty(JCR_DATA, requestBodyStream); then the JCR
         * would not block on the stream's completion, and we would return to
         * the requester before the mutation to the repo had actually completed.
         * So instead we use createBinary(requestBodyStream), because its
         * contract specifies: "The passed InputStream is closed before this
         * method returns either normally or because of an exception." which
         * lets us block and not return until the job is done! The simpler code
         * may still be useful to us for an asynchronous method that we develop
         * later.
         */
            final Property dataProperty = contentNode.setProperty(JCR.data.abbr(), binary);

            final String dsChecksum = his.getHashAsHexString();
            final URI uriChecksumString = ContentDigest.asURI("SHA-1", dsChecksum);
            if (checksum != null &&
                    !checksum.equals(uriChecksumString)) {
                LOGGER.debug("Failed checksum test");
                throw new InvalidChecksumException("Checksum Mismatch of " +
                        uriChecksumString + " and " + checksum);
            }

            decorateContentNode(contentNode);
            contentNode.setProperty(NDP.digest.abbr(), ContentDigest.asURI("SHA-1", dsChecksum).toString());
            contentNode.setProperty(NDP.hint.abbr(),hint);

            LOGGER.debug("Created data property at path: {}", dataProperty.getPath());

        } catch (final RepositoryException|NoSuchAlgorithmException e ) {
            throw new RepositoryRuntimeException(e);
        }
    }


    @Override
    public RDFStream getFixity(final RDFModelFactory factory) {
        return getFixity(factory, getContentDigest(), getContentSize());
    }

    @Override
    public RDFStream getFixity(final RDFModelFactory factory, final URI digestUri, final long size) {
        fixityCheckCounter.inc();
        try (final Timer.Context context = timer.time()) {
            final Repository repo = node.getSession().getRepository();
            LOGGER.debug("Checking resource: " + getPath());
            final String algorithm = ContentDigest.getAlgorithm(digestUri);
            final Collection<FixityResult> fixityResults
                    = CacheEntryFactory.forProperty(repo, getProperty(JCR.data.abbr())).checkFixity(algorithm);
            return new FixityRDFContext(this, factory, fixityResults, digestUri, size);
        } catch (final RepositoryException e) {
            throw new RepositoryRuntimeException(e);
        }
    }

    /**
     * When deleting the binary, we also need to clean up the description document.
     */
    @Override
    public void delete() {
        final NonRDFSourceDescription description = getDescription();
        super.delete();
        description.delete();
    }

    @Override
    public Version getBaseVersion() {
        return getDescription().getBaseVersion();
    }

    private static void decorateContentNode(final Node contentNode) throws RepositoryException {
        if (contentNode == null) {
            LOGGER.warn("{} node appears to be null!", JCR.content.abbr());
            return;
        }
        if (contentNode.canAddMixin(NDP.Binary.abbr()))
            contentNode.addMixin(NDP.Binary.abbr());
        if (contentNode.hasProperty(JCR.data.abbr())) {
            final Property dataProperty = contentNode.getProperty(JCR.data.abbr());
            final javax.jcr.Binary binary = dataProperty.getBinary();

            contentSizeHistogram.update(dataProperty.getLength());

            contentNode.setProperty(PREMIS.hasSize.abbr(), dataProperty.getLength());
            //TODO Binary 校验码的存储和读取
            LOGGER.debug("Decorated data property at path: {}", dataProperty.getPath());
        }
    }

    /*
     * (non-Javadoc)
     * @see com.novbank.ndp.kernel.model.Resource#getVersionHistory()
     */
    @Override
    public VersionHistory getVersionHistory() {
        try {
            return getSession().getWorkspace().getVersionManager().getVersionHistory(getDescription().getPath());
        } catch (final RepositoryException e) {
            throw new RepositoryRuntimeException(e);
        }
    }


    @Override
    public boolean isVersioned() {
        return getDescription().isVersioned();
    }

    @Override
    public void enableVersioning() {
        super.enableVersioning();
        getDescription().enableVersioning();
    }

    @Override
    public void disableVersioning() {
        super.disableVersioning();
        getDescription().disableVersioning();
    }

    /**
     * Check if the given node is a NDP binary
     * @param node the given node
     * @return whether the given node is a NDP binary
     */
    public static boolean hasMixin(final Node node) {
        return isBinary.test(node);
    }
}
