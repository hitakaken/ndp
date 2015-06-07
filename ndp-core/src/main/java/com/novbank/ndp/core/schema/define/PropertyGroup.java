package com.novbank.ndp.core.schema.define;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 定义相同的属性组
 *
 * Created by hp on 2015/6/7.
 */
public interface PropertyGroup extends List<Property>{
    /**
     * @return 属性定义
     */
    PropertyDefinition definition();

    /**
     * @return 命名空间
     */
    default String namespace(){ return definition().namespace();};

    /**
     * @return 属性名
     */
    default String name(){ return definition().name();};

    /**
     * @return 是否单例
     */
    default boolean isSingle(){
        return this.size() <= 1;
    }

    Property single();

    List<Property> toList();
    List<Property> toList(Predicate<Property> filter);
    <T> List<T> toList(Function<Property,T> mapper);
    <T> List<T> toList(Function<Property,T> mapper, Predicate<Property> filter);

    Set<Property> toSet();
    Set<Property> toSet(Predicate<Property> filter);
    <T> Set<T> toSet(Function<Property,T> mapper);
    <T> Set<T> toSet(Function<Property,T> mapper, Predicate<Property> filter);

    <K,V> Map<K,V> toMap(Function<Property,K> keyMapper,Function<Property,V> valueMapper);
    <K,V> Map<K,V> toMap(Function<Property,K> keyMapper,Function<Property,V> valueMapper, Predicate<Property> filter);
}
