package com.novbank.ndp.kernel.util.collection;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * Created by CaoKe on 2015/5/14.
 */
public class DBLikeCollection<T> {
    protected Map<T, Map<String, Comparable>> values;
    protected Map<String, Function<T,Comparable>> functions;
    protected Map<String, Boolean> multiples;
    protected Map<String, Multimap<Comparable, T>> indexes;

    public DBLikeCollection() {
        values = Maps.newHashMap();
        functions = Maps.newHashMap();
        multiples = Maps.newHashMap();
        indexes = Maps.newHashMap();
    }

    public void addIndex(String indexName, Function<T,Comparable> indexFunction){
        addIndex(indexName,indexFunction,false);
    }

    public void addIndex(String indexName, Function<T, Comparable> indexFunction, boolean multiple) {
        functions.put(indexName, indexFunction);
        multiples.put(indexName, multiple);
    }

    public void rebuild(String indexName) throws IllegalArgumentException{
        if(indexName == null || functions.containsKey(indexName))
            throw new IllegalArgumentException(String.format("No Such Index: %s",indexName));
        final Multimap<Comparable, T> index = HashMultimap.create();
        final Function<T, Comparable> function = functions.get(indexName);
        values.forEach((k,v) -> index.put(function.apply(k),k));
        if(!multiples.get(indexName))
            for(Comparable key : index.keySet())
                if(index.get(key).size()>1) throw new IllegalArgumentException(String.format("Unique Index: %s is not unique",indexName));
        indexes.put(indexName,index);
        for(Comparable key : index.keySet())
            for (T value : index.get(key))
                values.get(value).put(indexName,key);
     }

    public void rebuild() throws IllegalArgumentException{

    }

    public void add(T value){

    }


}
