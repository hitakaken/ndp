package com.novbank.ndp.core.handler;


import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.function.Function;

/**
 * 字段定义
 * i.e.面向应用
 *
 * Created by hp on 2015/6/7.
 */
public interface FieldDefinition {
    /**
     * @return 对应的属性
     */
    String property();

    /**
     * @return 显示
     */
    String display();

    /**
     * @return 提示
     */
    String hint();

    /**
     * @return 约束条件，用于校验
     */
    Set<ConstraintViolation> constraint();

    /**
     * @return 处理器，对象->属性
     */
    /*Function<Object,Property> handler();*/
}
