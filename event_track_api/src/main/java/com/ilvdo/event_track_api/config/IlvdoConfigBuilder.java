package com.ilvdo.event_track_api.config;

import android.app.Application;
import com.ilvdo.event_track_api.IlvdoEventTrack;

/**
 * Created by sjy on 2018/12/27
 * Describe
 */
public class IlvdoConfigBuilder {
    private Application application;
    private String appkey;
    private String channel;

    public  IlvdoConfigBuilder withApplicatiuon(Application application){
        this. application=application;
        return this;
    }
    public  IlvdoConfigBuilder withAppkey(String appkey){
        this. appkey=appkey;
        return this;
    }
    public  IlvdoConfigBuilder withChannel(String channel){
        this. channel=channel;
        return this;
    }
    public IlvdoEventTrack config(){
        return new IlvdoEventTrack(application, appkey, channel);
    }

}
