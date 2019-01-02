package com.ilvdo.event_track_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by sjy on 2018/12/28
 * Describe 注解容器，解决java7 的多重注解的问题
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface Events {
    Path[] value();
}
