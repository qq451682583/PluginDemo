package com.android.grouter_annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标记方法耗时
 * @author holy
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface GCost {
    String value();
}
