package com.ilvdo.event_track_api;

import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ilvdo.event_track_annotation.EventAspect;
import com.ilvdo.event_track_api.config.Constant;
import com.ilvdo.event_track_api.inter.IGetEventPaths;
import com.umeng.analytics.MobclickAgent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by sjy on 2018/12/28
 * Describe 事件处理
 */
@Aspect
public class EventDeal {

    private static final String TAG = "event_info";

    private static final String pathsfullname="com.ilvdo.event_track_compier.ProxyEventPaths";
    //自定义事件集合
    private static Map paths;

    //用户操作记录集合
    private static LinkedList<String> localPaths=new LinkedList<>();

    @Pointcut("execution(@com.ilvdo.event_track_annotation.EventAspect * *(..))")
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
     * 初始化获取设置的事件路径集合
     */
    private void initEventList(){
        if(paths!=null){
            return;
        }
        try {
            IGetEventPaths iGetEventPaths= (IGetEventPaths) Class.forName(pathsfullname).newInstance();
            String str=iGetEventPaths.getPathList();
            if(null!=str&&!str.isEmpty()){
               JSONObject jsonObject= JSON.parseObject(str);
               paths= jsonObject.toJavaObject(Map.class);
            }
            Log.d(TAG,"需要匹配的事件序列是"+iGetEventPaths.getPathList());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    /**
     * 当前点击事件匹配事先设置的事件序列
     */
    // todo 待优化，以下加点需要优化：1.记录用户本地操作事件的方式 2.本地事件清理时机的处理 3. 本地事件和预设事件的匹配算法
    private void matchEvent(){
       if(null == paths || null == localPaths)return;
        Set set=paths.entrySet();
        Iterator iterator= set.iterator();
        while (iterator.hasNext()){
            Map.Entry<String,List> it= (Map.Entry<String, List>) iterator.next();
            List eventList= it.getValue();
            if(localPaths.containsAll(eventList)){
                localPaths.removeAll(eventList);
                if(null!=Constant.getApplication()){
                    MobclickAgent.onEvent(Constant.getApplication(),"test_event");
                    Log.d(TAG,"满足事件触发条件,本地事件集合长度为"+localPaths.size());
                }
            }
        }
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
                String desc = methodAnno.pathId();
                Log.d(TAG,"代码执行到此处，当前id是"+desc);
                localPaths.add(desc);
                initEventList();
                matchEvent();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }



}
