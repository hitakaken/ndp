package com.novbank.ndp.kernel.impl.model;

import com.novbank.ndp.kernel.exception.RepositoryRuntimeException;
import com.novbank.ndp.kernel.model.NonRDFSource;
import com.novbank.ndp.kernel.model.NonRDFSourceDescription;
import com.novbank.ndp.kernel.util.jcr.JcrTypesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import static com.novbank.ndp.kernel.vocabulary.Vocabularies.*;

/**
 * Created by CaoKe on 2015/5/17.
 */
public class NonRDFSourceDescriptionImpl extends ResourceImpl implements NonRDFSourceDescription{
    private static final Logger LOGGER = LoggerFactory.getLogger(NonRDFSourceDescriptionImpl.class);

    public NonRDFSourceDescriptionImpl(Node node) {
        super(node);
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

    @Override
    public NonRDFSource getDescribedResource() {
        return new BinaryImpl(getContentNode());
    }

    private Node getContentNode() {
        LOGGER.trace("Retrieved datastream content node.");
        try {
            return node.getNode(JCR.content.abbr());
        } catch (final RepositoryException e) {
            throw new RepositoryRuntimeException(e);
        }
    }

    /**
     * Check if the node has a ndp:datastream mixin
     *
     * @param node node to check
     * @return whether the node has a ndp:dataStream mixin
     */
    public static boolean hasMixin(final Node node) {
        return JcrTypesUtils.isNonRDFSourceDescription.test(node);
    }
}
