package com.ilvdo.event_track_api;

import android.app.Application;
import com.ilvdo.event_track_api.config.Constant;
import com.ilvdo.event_track_api.config.IlvdoConfigBuilder;
import com.umeng.commonsdk.UMConfigure;


/**
 * Created by sjy on 2018/12/27
 * Describe
 */

public class IlvdoEventTrack {
    private final Application application;
    private final String appkey;//友盟appkey
    private final String channel;//友盟channel

    public static IlvdoConfigBuilder init(){
        return new IlvdoConfigBuilder();
    }

    public IlvdoEventTrack(Application application, String appkey, String channel) {
        this.application = application;
        this.appkey = appkey;
        this.channel = channel;
        Constant.setApplication(application);
        UMConfigure.init(application, appkey, channel, UMConfigure.DEVICE_TYPE_PHONE, "");
    }


}
