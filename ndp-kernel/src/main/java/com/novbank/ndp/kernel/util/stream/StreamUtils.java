package com.novbank.ndp.kernel.util.stream;

import com.google.common.collect.Lists;
import com.novbank.ndp.kernel.rdfsupport.RDFTriple;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * Created by hp on 2015/5/14.
 */
public class StreamUtils {
    public static <T> Stream<T> toStream(T... array){
        return toStream(Arrays.asList(array));
    }

    public static <T> Stream<T> toStream(Iterator<T> array){
        return toStream(Lists.newArrayList(array));
    }

    public static <T> Stream<T> toStream(Iterable<T> array){
        return toStream(Lists.newArrayList(array));
    }

    public static <T> Stream<T> toStream(Collection<T> array){
        return array.stream();
    }
}
