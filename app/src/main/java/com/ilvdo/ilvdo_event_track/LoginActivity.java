package com.ilvdo.ilvdo_event_track;

import android.app.Activity;
import android.os.Bundle;
import com.ilvdo.event_track_annotation.EventAspect;

/**
 * Created by sjy on 2018/12/27
 * Describe
 */
public class LoginActivity extends Activity {

    @Override
    @EventAspect(pathId ="12")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test();
    }

    @EventAspect(pathId = "123")
    public void  test(){

    }
}
