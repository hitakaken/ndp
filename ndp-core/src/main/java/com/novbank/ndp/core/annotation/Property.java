package com.novbank.ndp.core.annotation;

/**
 * Created by hp on 2015/6/2.
 */
public @interface Property {
    String name() default "";
    String display() default "";
    String hint() default "";
    boolean nullable() default true;
}
