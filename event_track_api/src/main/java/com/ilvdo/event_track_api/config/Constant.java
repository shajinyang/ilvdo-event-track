package com.ilvdo.event_track_api.config;

import android.app.Application;

/**
 * Created by sjy on 2019/1/2
 * Describe
 */
public class Constant {
    private static Application application;

    public static Application getApplication() {
        return application;
    }

    public static void setApplication(Application application) {
        Constant.application = application;
    }
}
