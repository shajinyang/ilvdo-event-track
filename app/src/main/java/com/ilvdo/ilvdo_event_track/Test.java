package com.ilvdo.ilvdo_event_track;

import android.util.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;


/**
 * Created by sjy on 2018/12/26
 * Describe
 */
@Aspect
public class Test {
    //ydc start
    private static final String TAG = "test_trace";
    @Before("execution(* android.app.Activity.on**(..))")
    public void onActivityMethodBefore(JoinPoint joinPoint) throws Throwable {
        String key = joinPoint.getSignature().toString();
        Log.d(TAG, "onActivityMethodBefore:呵呵，你的行动我已了如指掌");
    }
}
