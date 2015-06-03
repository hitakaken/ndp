package com.novbank.ndp.core.util;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.novbank.ndp.core.util.function.FunctionWrapUtils;

import java.util.Iterator;
import java.util.function.Function;

/**
 * Created by CaoKe on 2015/6/3.
 */
public class MapUtils {
    public static <K,V> ImmutableMap<K,V> uniqueIndex(Iterator<V> values, Function<? super V,K> keyFunction){
        return Maps.uniqueIndex(values, FunctionWrapUtils.wrap(keyFunction));
    }

    public static <K,V> ImmutableMap<K,V> uniqueIndex(Iterable<V> values, Function<? super V,K> keyFunction){
        return Maps.uniqueIndex(values, FunctionWrapUtils.wrap(keyFunction));
    }
}
