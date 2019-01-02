package com.ilvdo.ilvdo_event_track;

import com.ilvdo.event_track_annotation.Events;
import com.ilvdo.event_track_annotation.Path;

/**
 * Created by sjy on 2018/12/29
 * Describe
 */
@Events({
    @Path(path = {"01","02"} ,id="login_to_main"),
    @Path(path = {"03","06"} ,id="pay_vip")

        })
public class MyPath {

}
