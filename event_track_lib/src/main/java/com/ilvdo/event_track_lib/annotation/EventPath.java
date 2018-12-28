package com.ilvdo.event_track_lib.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sjy on 2018/12/28
 * Describe 事件注解，标记完整的事件序列
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EventPath {
    //事件路径集合
    String[] path();
}
