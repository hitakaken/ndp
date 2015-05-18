package com.novbank.ndp.kernel.impl.model;

import com.google.common.collect.Iterators;
import com.novbank.ndp.kernel.exception.MalformedRdfException;
import com.novbank.ndp.kernel.exception.RepositoryRuntimeException;
import com.novbank.ndp.kernel.model.Binary;
import com.novbank.ndp.kernel.model.NonRDFSourceDescript;
import com.novbank.ndp.kernel.model.Resource;
import com.novbank.ndp.kernel.rdfsupport.RDFStream;
import com.novbank.ndp.kernel.util.convert.Converter;
import com.novbank.ndp.kernel.util.convert.FunctionBasedConverter;
import com.novbank.ndp.kernel.util.jcr.JcrTypesUtils;
import org.apache.jackrabbit.commons.JcrUtils;
import org.jooq.lambda.Seq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.*;
import java.util.*;
import java.util.function.Predicate;

import static com.novbank.ndp.kernel.util.jcr.JcrPropertyFunctions.*;
import static com.novbank.ndp.kernel.vocabulary.Vocabularies.*;
import static com.novbank.ndp.kernel.impl.model.NodeResourceConverter.nodeConverter;

/**
 * Created by CaoKe on 2015/5/16.
 */
public class ResourceImpl implements Resource{
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceImpl.class);
    protected Node node;

    /**
     * Construct a {@link com.novbank.ndp.kernel.model.Resource} from an existing JCR Node
     * @param node an existing JCR node to treat as an ndp object
     */
    public ResourceImpl(Node node) {
        this.node = node;
    }

    /* (non-Javadoc)
     * @see com.novbank.ndp.kernel.model.Resource#getNode()
     */
    @Override
    public Node getNode() {
        return node;
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

    /* (non-Javadoc)
         * @see com.novbank.ndp.kernel.model.Resource#getChildren()
         */
    @Override
    public Iterator<Resource> getChildren() {
        try {
            return Iterators.concat(nodeToGoodChildren(node));
        } catch (final RepositoryException e) {
            throw new RepositoryRuntimeException(e);
        }
    }

    /**
     * Get the "good" children for a node by skipping all pairtree nodes in the way.
     * @param input
     * @return
     * @throws RepositoryException
     */
    private Iterator<Iterator<Resource>> nodeToGoodChildren(final Node input) throws RepositoryException {
        final Iterator<Node> allChildren = input.getNodes();
        return Seq.seq(allChildren).filter(nastyChildren.negate()).map(n -> {
            try {
                if (input.isNodeType(NDP.PairTree.abbr())) {
                    return Iterators.concat(nodeToGoodChildren(input));
                }
                return Collections.singleton(nodeToObjectBinaryConverter.convert(input)).iterator();
            } catch (final RepositoryException e) {
                throw new RepositoryRuntimeException(e);
            }
        }).iterator();
    }
    /**
     * Children for whom we will not generate triples.
     */
    private static Predicate<Node> nastyChildren =
            n -> {
                LOGGER.trace("Testing child node {}", n);
                try {
                    return JcrTypesUtils.isInternalNode.test(n)
                            || n.getName().equals(JCR.content.abbr())
                            || TombstoneImpl.hasMixin(n)
                            || n.getName().equals("#");
                } catch (final RepositoryException e) {
                    throw new RepositoryRuntimeException(e);
                }
            };


    private static final Converter<Resource, Resource> dataStreamToBinary
            = new FunctionBasedConverter<>(
            r -> (r instanceof NonRDFSourceDescript) ?((NonRDFSourceDescript)r).getDescribedResource() : r ,
            r -> (r instanceof Binary) ?((Binary)r).getDescription() : r );

    private static final Converter<Node, Resource> nodeToObjectBinaryConverter
            = nodeConverter.andThen(dataStreamToBinary);

    @Override
    public Resource getContainer() {
        try {
            if (getNode().getDepth() == 0) {
                return null;
            }
            Node container = getNode().getParent();
            while (container.getDepth() > 0) {
                if (container.isNodeType(NDP.PairTree.abbr())
                        || container.isNodeType(NDP.NonRDFSourceDescription.abbr())) {
                    container = container.getParent();
                } else {
                    return nodeConverter.convert(container);
                }
            }

            return nodeConverter.convert(container);
        } catch (final RepositoryException e) {
            throw new RepositoryRuntimeException(e);
        }
    }

    @Override
    public Resource getChild(final String relPath) {
        try {
            return nodeConverter.convert(getNode().getNode(relPath));
        } catch (final RepositoryException e) {
            throw new RepositoryRuntimeException(e);
        }
    }

    @Override
    public void delete() {
        try {
            final Iterator<Property> references = node.getReferences();
            final Iterator<Property> weakReferences = node.getWeakReferences();
            final Iterator<Property> inboundProperties = Iterators.concat(references, weakReferences);

            while (inboundProperties.hasNext()) {
                final Property prop = inboundProperties.next();
                final List<Value> newVals = new ArrayList<>();
                final Iterator<Value> propIt = property2values.apply(prop);
                while (propIt.hasNext()) {
                    final Value v = propIt.next();
                    if (!node.equals(node.getSession().getNodeByIdentifier(v.getString()))) {
                        newVals.add(v);
                        LOGGER.trace("Keeping multivalue reference property when deleting node");
                    }
                }
                if (newVals.size() == 0) {
                    prop.remove();
                } else {
                    prop.setValue(newVals.toArray(new Value[newVals.size()]));
                }
            }

            final Node parent;

            if (getNode().getDepth() > 0) {
                parent = getNode().getParent();
            } else {
                parent = null;
            }
            final String name = getNode().getName();

            node.remove();

            if (parent != null) {
                createTombstone(parent, name);
            }

        } catch (final RepositoryException e) {
            throw new RepositoryRuntimeException(e);
        }
    }

    private void createTombstone(final Node parent, final String path) throws RepositoryException {
        JcrUtils.getOrAddNode(parent, path, NDP.Tombstone.abbr());
    }

    @Override
    public Resource getVersionedAncestor() {
        try {
            if (!isFrozenResource()) return null;
            Node versionableFrozenNode = getNode();
            Resource unfrozenResource = getUnfrozenResource();
            // traverse the frozen tree looking for a node whose unfrozen equivalent is versioned
            while (!unfrozenResource.isVersioned()) {
                if (versionableFrozenNode.getDepth() == 0)  return null;
                // node in the frozen tree
                versionableFrozenNode = versionableFrozenNode.getParent();
                // unfrozen equivalent
                unfrozenResource = new ResourceImpl(versionableFrozenNode).getUnfrozenResource();
            }
            return new ResourceImpl(versionableFrozenNode);
        } catch (final RepositoryException e) {
            throw new RepositoryRuntimeException(e);
        }
    }

    @Override
    public Resource getUnfrozenResource() {
        if (!isFrozenResource()) {
            return this;
        }
        try {
            return new ResourceImpl(getNode().getSession().getNodeByIdentifier(
                    getProperty(JCR.frozenUuid.abbr()).getString()));
        } catch (final RepositoryException e) {
            throw new RepositoryRuntimeException(e);
        }
    }

    @Override
    public boolean equals(final Object object) {
        if (object instanceof ResourceImpl) {
            return ((ResourceImpl) object).getNode().equals(this.getNode());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return getNode().hashCode();
    }

    @Override
    public String toString() {
        return getNode().toString();
    }

    /* (non-Javadoc)
     * @see com.novbank.ndp.kernel.model.Resource#updateProperties
     *     (java.lang.String, RdfStream)
     */
    @Override
    public void updateProperties(final String sparqlUpdateStatement, final RDFStream originalTriples)
            throws MalformedRdfException, AccessDeniedException {

        final Model model = originalTriples.asModel();

        final JcrPropertyStatementListener listener =
                new JcrPropertyStatementListener(idTranslator, getSession());

        model.register(listener);

        final UpdateRequest request = create(sparqlUpdateStatement,
                idTranslator.reverse().convert(this).toString());
        model.setNsPrefixes(request.getPrefixMapping());
        execute(request, model);
        listener.assertNoExceptions();
    }

    @Override
    public RDFStream getTriples(final Iterable<Class<? extends RDFStream>> contexts) {
        final RDFStream stream = new RDFStream();

        for (final Class<? extends RDFStream> context : contexts) {
            try {
                final Constructor<? extends RDFStream> declaredConstructor
                        = context.getDeclaredConstructor(Resource.class, IdentifierConverter.class);

                final RdfStream rdfStream = declaredConstructor.newInstance(this, idTranslator);
                rdfStream.session(getSession());

                stream.concat(rdfStream);
            } catch (final NoSuchMethodException |
                    InstantiationException |
                    IllegalAccessException e) {
                // Shouldn't happen.
                throw propagate(e);
            } catch (final InvocationTargetException e) {
                final Throwable cause = e.getCause();
                if (cause instanceof RepositoryException) {
                    throw new RepositoryRuntimeException(cause);
                }
                throw propagate(cause);
            }
        }

        return stream;
    }

    /* (non-Javadoc)
     * @see com.novbank.ndp.kernel.model.Resource#replaceProperties
     *     (org.fcrepo.kernel.identifiers.IdentifierConverter, com.hp.hpl.jena.rdf.model.Model)
     */
    @Override
    public void replaceProperties(final Model inputModel, final RdfStream originalTriples) throws MalformedRdfException {

        final RdfStream replacementStream = new RdfStream().namespaces(inputModel.getNsPrefixMap());

        final GraphDifferencingIterator differencer =
                new GraphDifferencingIterator(inputModel, originalTriples);

        final StringBuilder exceptions = new StringBuilder();
        try {
            new RdfRemover(idTranslator, getSession(), replacementStream
                    .withThisContext(differencer)).consume();
        } catch (final MalformedRdfException e) {
            exceptions.append(e.getMessage());
            exceptions.append("\n");
        }

        try {
            new RdfAdder(idTranslator, getSession(), replacementStream
                    .withThisContext(differencer.notCommon())).consume();
        } catch (final MalformedRdfException e) {
            exceptions.append(e.getMessage());
        }

        if (exceptions.length() > 0) {
            throw new MalformedRdfException(exceptions.toString());
        }
    }
}
