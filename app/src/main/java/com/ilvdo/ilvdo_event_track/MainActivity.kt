package com.ilvdo.ilvdo_event_track

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ilvdo.event_track_annotation.EventAspect
import com.ilvdo.event_track_annotation.Events
import com.ilvdo.event_track_annotation.Path
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    @EventAspect(pathId = "19")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_skip.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }
}
