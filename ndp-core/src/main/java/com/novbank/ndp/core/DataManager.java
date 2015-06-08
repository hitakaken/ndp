package com.novbank.ndp.core;

import com.google.inject.Injector;
import com.novbank.ndp.core.extension.Extension;
import com.novbank.ndp.core.extension.ExtensionManager;
import com.novbank.ndp.core.schema.NamespaceManager;
import com.novbank.ndp.core.schema.SchemaManager;

/**
 * 数据管理器
 *
 * Created by hp on 2015/6/8.
 */
public interface DataManager extends Injector {
    /**
     * @return 命名空间管理器
     */
    NamespaceManager namespaces();

    /**
     * @return Schema管理器
     */
    SchemaManager schemas();

    DataManager load();

    DataManager reload();
}
