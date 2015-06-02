package com.novbank.ndp.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by hp on 2015/6/2.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.PACKAGE,ElementType.TYPE})
public @interface NameSpaces {
    String base();
    NameSpace[] value() default {};
}
