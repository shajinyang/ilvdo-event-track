package com.ilvdo.event_track_api;

import android.app.Application;
import com.ilvdo.event_track_api.config.IlvdoConfigBuilder;


/**
 * Created by sjy on 2018/12/27
 * Describe
 */

public class IlvdoEventTrack {
    private final Application application;

    public static IlvdoConfigBuilder init(){
        return new IlvdoConfigBuilder();
    }

    public IlvdoEventTrack(Application application) {
        this.application = application;
    }



}
