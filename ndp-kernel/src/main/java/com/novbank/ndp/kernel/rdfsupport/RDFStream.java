package com.novbank.ndp.kernel.rdfsupport;

import com.google.common.collect.Lists;
import com.novbank.ndp.kernel.util.stream.ForwardingStream;
import com.novbank.ndp.kernel.util.stream.StreamUtils;

import javax.jcr.Session;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by hp on 2015/5/14.
 */
public class RDFStream extends ForwardingStream<RDFTriple> {
    protected Set<Namespace> namespaces = new HashSet<>();
    protected Stream<RDFTriple> triples;
    protected Session context;
    protected RDFResource topic;

    private static final RDFTriple[] NONE = new RDFTriple[] {};

    public RDFStream(RDFTriple... triples){
        this(StreamUtils.toStream(triples));
    }

    public RDFStream(Iterator<RDFTriple> triples){
        this(StreamUtils.toStream(triples));
    }

    public RDFStream(Iterable<RDFTriple> triples){
        this(StreamUtils.toStream(triples));
    }

    public RDFStream(Collection<RDFTriple> triples){
        this(StreamUtils.toStream(triples));
    }

    public RDFStream(){
        this(NONE);
    }

    public RDFStream(Stream<RDFTriple> triples){
        this.triples = triples;
    }

    public RDFStream(Set<Namespace> namespaces, Stream<RDFTriple> triples, Session context, RDFResource topic) {
        this.namespaces = namespaces;
        this.triples = triples;
        this.context = context;
        this.topic = topic;
    }

    public Namespace namespaces(){
        return null;
    }

    public RDFStream namespaces(String prefix,String uri){
        return this;
    }

    public RDFStream namespaces(Map<String,String> namespaces){
        return this;
    }

    public RDFStream namespaces(Set<Namespace> namespaces){
        return this;
    }

    public Session session(){
        return context;
    }

    public RDFStream session(final Session session){
        this.context = session;
        return this;
    }

    public RDFResource topic(){
        return topic;
    }

    public RDFStream topic(final RDFResource topic){
        this.topic = topic;
        return this;
    }

    @Override
    protected Stream<RDFTriple> delegate() {
        return triples;
    }

    @Override
    protected RDFStream withThisContext(Stream<RDFTriple> newStream) {
        return new RDFStream(newStream).session(session()).topic(topic());
    }


}
