package com.novbank.ndp.kernel.impl.rdf;

import com.novbank.ndp.kernel.model.Resource;
import com.novbank.ndp.kernel.rdfsupport.RDFModelFactory;
import com.novbank.ndp.kernel.service.result.FixityResult;

import java.net.URI;

/**
 * An {@link com.novbank.ndp.kernel.rdfsupport.RDFStream} containing information about the fixity of a
 * {@link com.novbank.ndp.kernel.model.Binary}.
 *
 * Created by hp on 2015/5/18.
 */
public class FixityRDFContext extends NodeRDFContext {
    public FixityRDFContext(final Resource resource,
                            final RDFModelFactory factory,
                            final Iterable<FixityResult> blobs,
                            final URI digest,
                            final long size){
        super(resource, factory);
       /* concat(Iterators.concat(Iterators.transform(blobs.iterator(),
                new FixityResultIteratorFunction(resource, idTranslator, digest, size))));*/
    }

   /* private class FixityResultIteratorFunction implements Function<FixityResult, Iterator<Triple>> {

        private final FedoraResource resource;
        private final IdentifierConverter<Resource, FedoraResource> idTranslator;
        private URI digest;
        private final long size;

        public FixityResultIteratorFunction(final FedoraResource resource,
                                            final IdentifierConverter<Resource, FedoraResource> idTranslator,
                                            final URI digest,  final long size) {
            this.resource = resource;
            this.idTranslator = idTranslator;
            this.digest = digest;
            this.size = size;
        }

        @Override
        public Iterator<Triple> apply(final FixityResult blob) {
            final com.hp.hpl.jena.graph.Node resultSubject = getTransientFixitySubject();
            final ImmutableSet.Builder<Triple> b = builder();
            try {
                b.add(create(idTranslator.reverse().convert(resource).asNode(),
                        HAS_FIXITY_RESULT.asNode(), resultSubject));
                b.add(create(resultSubject, RDF.type.asNode(), FIXITY_TYPE.asNode()));
                final String storeIdentifier = blob.getStoreIdentifier();
                final com.hp.hpl.jena.graph.Node contentLocation = createResource(storeIdentifier)
                        .asNode();

                for (final FixityResult.FixityState state : blob.getStatus(size, digest)) {
                    b.add(create(resultSubject, HAS_FIXITY_STATE
                            .asNode(), createLiteral(state
                            .toString())));
                }
                final String checksum =
                        blob.getComputedChecksum().toString();
                b.add(create(resultSubject, HAS_MESSAGE_DIGEST
                        .asNode(), createURI(checksum)));
                b.add(create(resultSubject, HAS_SIZE.asNode(),
                        createTypedLiteral(
                                blob.getComputedSize())
                                .asNode()));
                b.add(create(resultSubject, HAS_CONTENT_LOCATION.asNode(),
                        contentLocation));
                b.add(create(contentLocation,
                        RDF.type.asNode(),
                        CONTENT_LOCATION_TYPE.asNode()));
                b.add(create(contentLocation,
                        HAS_CONTENT_LOCATION_VALUE.asNode(),
                        createLiteral(storeIdentifier)));

                return b.build().iterator();
            } catch (final RepositoryException e) {
                throw new RepositoryRuntimeException(e);
            }
        }

    }

    private com.hp.hpl.jena.graph.Node getTransientFixitySubject() {
        return createURI(subject().getURI() + "#fixity/" + Calendar.getInstance().getTimeInMillis());
    }*/
}
