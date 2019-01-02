package com.ilvdo.event_track_annotation;

/**
 * Created by sjy on 2018/12/28
 * Describe 方法事件注解，运行时注解
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({ElementType.METHOD})
public @interface EventAspect {
    //切点标识id
    String pathId();
}
