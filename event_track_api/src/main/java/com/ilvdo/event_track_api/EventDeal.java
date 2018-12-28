package com.ilvdo.event_track_api;

import android.util.Log;
import com.ilvdo.event_track_annotation.EventAspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * Created by sjy on 2018/12/28
 * Describe 事件处理
 */
@Aspect
public class EventDeal {
    private static final String TAG = "event_info";


    @Pointcut("execution(@com.ilvdo.event_track_lib.annotation.EventAspect * *(..))")
    public void method() {}


    /**
     * 方法执行之前调用
     * @param joinPoint
     * @throws Throwable
     */
    @Before("method()")
    public void logAndExecute(JoinPoint joinPoint) throws Throwable {
        getId(joinPoint);
    }


    /**
     * 获取切点Id
     */
    private void getId(JoinPoint joinPoint){
        //拦截的实体类
        Object target = joinPoint.getTarget();

        //拦截的方法名称
        String methodName = joinPoint.getSignature().getName();

        //拦截的方法参数
        Object[] argsa = joinPoint.getArgs();

        //拦截的放参数类型
        Class[] parameterTypes = ((MethodSignature)joinPoint.getSignature()).getMethod().getParameterTypes();

        try {
            Method method = target.getClass().getDeclaredMethod(methodName,parameterTypes);
            boolean methodHasAnno = method.isAnnotationPresent(EventAspect.class);
            if(methodHasAnno){
                //得到注解
                EventAspect methodAnno = method.getAnnotation(EventAspect.class);
                //输出注解属性
                int desc = methodAnno.id();
                Log.d(TAG,"代码执行到此处，当前id是"+desc);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }



}
