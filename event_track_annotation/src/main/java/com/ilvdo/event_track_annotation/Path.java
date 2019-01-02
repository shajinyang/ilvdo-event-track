package com.ilvdo.event_track_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Created by sjy on 2018/12/28
 * Describe 事件注解，标记完整的事件序列
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface Path {
    //事件路径集合
    String[] path();
    //事件序列id，对应友盟
    String id();
}
