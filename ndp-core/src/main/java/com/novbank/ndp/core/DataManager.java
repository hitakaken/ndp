package com.novbank.ndp.core;

import com.novbank.ndp.core.schema.NamespaceManager;
import com.novbank.ndp.core.schema.SchemaManager;

/**
 * 数据管理器
 *
 * Created by hp on 2015/6/8.
 */
public interface DataManager {
    /**
     * @return 命名空间管理器
     */
    NamespaceManager namespaces();

    /**
     * @return Schema管理器
     */
    SchemaManager schemas();

}
