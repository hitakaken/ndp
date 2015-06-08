package com.novbank.ndp.core.util.collect;

import com.novbank.ndp.core.util.function.Predicates;
import org.jooq.lambda.Seq;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by hp on 2015/6/8.
 */
public interface FunctionalList<S> extends List<S>{
    default S first(){
        return this.size()>=1 ? this.get(0) : null;
    }

    default S last(){
        return this.size()>=1 ? this.get(this.size()-1) : null;
    }

    default Seq<S> seq(){
        return Seq.seq(this);
    }

    default List<S> filter(Predicate<S> filter){
        return seq()
                .filter(filter != null ? filter : Predicates.ALWAYS_TRUE).toList();
    }

    default <T> List<T> map(Function<S,T> mapper){
        return seq().map(mapper).toList();
    }

    default <T> List<T> filterAndMap(Function<S,T> mapper, Predicate<S> before){
        return seq()
                .filter(before != null ? before : Predicates.ALWAYS_TRUE).map(mapper)
                .toList();
    }

    default <T> List<T> mapAndFilter(Function<S,T> mapper, Predicate<T> after){
        return seq()
                .map(mapper)
                .filter(after != null ? after : Predicates.ALWAYS_TRUE)
                .toList();
    }

    default <T> List<T> map(Function<S,T> mapper, Predicate<S> before, Predicate<T> after){
        return seq()
                .filter(before != null ? before : Predicates.ALWAYS_TRUE)
                .map(mapper)
                .filter(after != null ? after : Predicates.ALWAYS_TRUE)
                .toList();
    }


    default Set<S> toSet(){
        return seq().toSet();
    }

    default Set<S> filterToSet(Predicate<S> filter){
        return seq()
                .filter(filter!= null ? filter : Predicates.ALWAYS_TRUE).toSet();
    }

    default <T> Set<T> mapToSet(Function<S,T> mapper){
        return seq().map(mapper).toSet();
    }

    default <T> Set<T> filterAndMapToSet(Function<S,T> mapper, Predicate<S> before){
        return seq()
                .filter(before != null ? before : Predicates.ALWAYS_TRUE).map(mapper)
                .toSet();
    }

    default <T> Set<T> mapAndFilterToSet(Function<S,T> mapper, Predicate<T> after){
        return seq()
                .map(mapper)
                .filter(after != null ? after : Predicates.ALWAYS_TRUE)
                .toSet();
    }

    default <T> Set<T> mapToSet(Function<S,T> mapper, Predicate<S> before, Predicate<T> after){
        return seq()
                .filter(before != null ? before : Predicates.ALWAYS_TRUE)
                .map(mapper)
                .filter(after != null ? after : Predicates.ALWAYS_TRUE)
                .toSet();
    }

    default <K,V> Map<K,V> toMap(Function<S,K> keyMapper,Function<S,V> valueMapper){
        return seq().toMap(keyMapper,valueMapper);
    }

    default <K,V> Map<K,V> toMap(Function<S,K> keyMapper,Function<S,V> valueMapper, Predicate<S> filter){
        return seq().filter(filter).toMap(keyMapper,valueMapper);
    }
}
