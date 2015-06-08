package com.novbank.ndp.core.handler;

import com.novbank.ndp.core.record.Record;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * 行对象
 * i.e.对应 POJO
 *
 * Created by hp on 2015/6/7.
 */
public interface Row {
    /**
     * @return 行定义
     */
    RowDefinition definition();

    /**
     * @return 字段列表
     */
    Map<String,List> fields();

    /**
     * @return 转换为记录
     */
    Record toRecord();

    /**
     * @param filter 过滤器
     * @return
     */
    Record toRecord(Predicate<Map.Entry<String,Object>> filter);
}
