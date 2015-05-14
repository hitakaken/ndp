package com.novbank.ndp.kernel.rdfsupport;

import com.google.common.collect.Lists;
import com.novbank.ndp.kernel.util.stream.ForwardingStream;
import com.novbank.ndp.kernel.util.stream.StreamUtils;
import org.apache.commons.collections4.map.MultiKeyMap;

import javax.jcr.Session;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by hp on 2015/5/14.
 */
public class RDFStream extends ForwardingStream<RDFTriple> {
    protected MultiKeyMap<String,Namespace> namespaces = new MultiKeyMap<>();
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
        namespaces(namespaces);
        this.triples = triples;
        this.context = context;
        this.topic = topic;
    }

    public MultiKeyMap namespaces(){
        return namespaces;
    }

    public RDFStream namespace(String prefix,String uri){
        namespaces.put(prefix, uri, new Namespace(uri, prefix));

        return this;
    }

    public RDFStream namespace(Namespace namespace){
        namespaces.put(namespace.getPrefix(), namespace.getNamespace(), namespace);
        return this;
    }

    public RDFStream namespaces(final Map<String,String> namespaceMap){
        if(namespaceMap!=null) namespaceMap.forEach((k, v)-> namespaces.put(k,v,new Namespace(v,k)));
        return this;
    }

    public RDFStream namespaces(final Collection<Namespace> namespaceSet){
        if(namespaceSet!=null) namespaceSet.forEach( n -> namespaces.put(n.getPrefix(),n.getNamespace(),n));
        return this;
    }

    public RDFStream namespaces(final MultiKeyMap<String,Namespace> namespaces){
        if(namespaces!=null) this.namespaces.putAll(namespaces);
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
        return new RDFStream(newStream).namespaces(namespaces).session(session()).topic(topic());
    }


}
