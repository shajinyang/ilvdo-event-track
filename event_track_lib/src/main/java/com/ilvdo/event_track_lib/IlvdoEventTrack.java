package com.ilvdo.event_track_lib;

import android.app.Application;
import com.ilvdo.event_track_lib.annotation.EventMethodAspect;
import com.ilvdo.event_track_lib.annotation.EventPath;
import com.ilvdo.event_track_lib.config.IlvdoConfigBuilder;


/**
 * Created by sjy on 2018/12/27
 * Describe
 */
@EventPath(path = {"heh","heh"})
public class IlvdoEventTrack {
    private final Application application;

    public static IlvdoConfigBuilder init(){
        return new IlvdoConfigBuilder();
    }

    public IlvdoEventTrack(Application application) {
        this.application = application;
    }

}
