package com.novbank.ndp.core.schema.define;

import java.util.List;

/**
 * Created by hp on 2015/6/7.
 */
public interface Table extends List<Row> {
    /**
     * @return 表格名
     */
    String name();

    /**
     * @return 行定义
     */
    RowDefinition definition();
}
