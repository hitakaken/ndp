package com.novbank.ndp.kernel.rdfsupport;


import com.google.common.collect.BiMap;
import com.google.common.collect.ForwardingMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by hp on 2015/5/14.
 */
public class Namespaces extends ForwardingMap<String,String> implements BiMap<String,String> {
    public final BiMap<String,String> prefixMap;
    public final Map<String,Namespace> namespaces;
    public Namespaces() {
        prefixMap = HashBiMap.create();
        namespaces = Maps.newHashMap();
    }

    @Override
    public String forcePut(String prefix, String uri) {
        return delegate().forcePut(prefix,uri);
    }

    @Override
    public Set<String> values() {
        return inverse().keySet();
    }

    @Override
    public BiMap<String, String> inverse() {
        return delegate().inverse();
    }

    @Override
    protected BiMap<String, String> delegate() {
        return prefixMap;
    }

}
