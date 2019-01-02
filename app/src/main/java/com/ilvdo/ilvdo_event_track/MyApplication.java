package com.ilvdo.ilvdo_event_track;

import android.app.Application;
import com.ilvdo.event_track_api.IlvdoEventTrack;

/**
 * Created by sjy on 2018/12/28
 * Describe
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        IlvdoEventTrack
                .init()
                .withApplicatiuon(this)
                .withAppkey("54f6667efd98c53466000db0")
                .withChannel("UMENG_CHANNEL_VALUE")
                .config();
    }
}
