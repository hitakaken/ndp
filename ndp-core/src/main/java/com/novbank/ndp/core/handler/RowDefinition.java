package com.novbank.ndp.core.handler;

import com.novbank.ndp.core.record.Record;

import javax.validation.Validator;
import java.util.Map;
import java.util.function.Function;

/**
 * 行定义
 * i.e.面向应用
 * i.e.实现与应用的对接
 *
 * Created by hp on 2015/6/7.
 */
public interface RowDefinition {
    /**
     * @return 字段定义列表
     */
    Iterable<FieldDefinition> fields();

    /**
     * @return 校验器
     */
    Validator validator();

    /**
     * @return 处理器，字符串->属性
     */
    Function<Map<String,Object>, Record> handler();
}
