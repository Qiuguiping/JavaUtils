package com.nascent.utils.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author guiping.Qiu
 * @version V1.0
 * @Package com.nascent.utils.annotation
 * @Description: TODO
 * @date 2018/4/20 13:34
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CSVField {

    /**
     * CSV文件列名
     */
    public String name() default "";
}
