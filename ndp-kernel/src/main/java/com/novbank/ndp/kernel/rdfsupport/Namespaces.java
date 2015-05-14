package com.novbank.ndp.kernel.rdfsupport;


import com.google.common.collect.BiMap;
import com.google.common.collect.ForwardingMap;

import java.util.Map;
import java.util.Set;

/**
 * Created by hp on 2015/5/14.
 */
public class Namespaces implements BiMap<String,String> {

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public String get(Object key) {
        return null;
    }

    @Override
    public String put(String key, String value) {
        return null;
    }

    @Override
    public String remove(Object key) {
        return null;
    }

    @Override
    public String forcePut(String key, String value) {
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> map) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<String> keySet() {
        return null;
    }

    @Override
    public Set<String> values() {
        return null;
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        return null;
    }

    @Override
    public BiMap<String, String> inverse() {
        return null;
    }
}
