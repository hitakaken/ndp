package com.novbank.ndp.core.record;

import com.novbank.ndp.core.schema.PropertyDefinition;
import com.novbank.ndp.core.util.collect.FunctionalList;

/**
 * 定义相同的属性组
 *
 * Created by hp on 2015/6/7.
 */
public interface PropertyGroup extends FunctionalList<Property> {
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
        return this.size() <= 1 || !definition().multiple();
    }

    default Property single(){
        return isSingle() ? first() : last();
    }

}
