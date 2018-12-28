package com.ilvdo.event_track_lib.config;

import android.app.Application;
import com.ilvdo.event_track_lib.IlvdoEventTrack;

/**
 * Created by sjy on 2018/12/27
 * Describe
 */
public class IlvdoConfigBuilder {
    private Application application;

    public  IlvdoConfigBuilder  init(Application application){
        this. application=application;
        return this;
    }

    public IlvdoEventTrack config(){
        return new IlvdoEventTrack(application);
    }

}
