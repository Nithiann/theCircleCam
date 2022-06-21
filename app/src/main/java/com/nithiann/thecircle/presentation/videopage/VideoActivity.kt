package com.nithiann.thecircle.presentation.videopage

import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.graphics.PathUtils
import com.nithiann.thecircle.R
import com.nithiann.thecircle.common.PathUtils.updateGallery
import com.pedro.rtplibrary.rtmp.RtmpCamera1
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class VideoActivity: ComponentActivity(), SurfaceHolder.Callback, View.OnClickListener {
    var rtmpCamera: RtmpCamera1? = null
    var sButton: Button? = null
    var streamButton: Button? = null
    //@SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(com.nithiann.thecircle.R.layout.texture_layout)
        val connectCheckerRtmp: ConnectCheckerRtmp = ConnectCheckerRtmp()
        val openGlView: SurfaceView = findViewById(com.nithiann.thecircle.R.id.surfaceView)
        sButton = findViewById(R.id.switch_camera)
        sButton!!.setOnClickListener(this)
        streamButton = findViewById(R.id.b_start_stop)
        streamButton!!.setOnClickListener(this)
        rtmpCamera = RtmpCamera1(openGlView, connectCheckerRtmp)
        openGlView.holder.addCallback(this);
        //openGlView.setOnTouchListener(this);
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        rtmpCamera?.startPreview()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        if (rtmpCamera?.isRecording()!!) {
            rtmpCamera?.stopRecord()

        }
        if (rtmpCamera?.isStreaming()!!) {
            rtmpCamera?.stopStream();
        }
        rtmpCamera?.stopPreview();
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.switch_camera -> rtmpCamera!!.switchCamera()
            R.id.b_start_stop -> startStopStream()
        }

    }

    private fun startStopStream() {
        if (!rtmpCamera!!.isStreaming()) {
            if (rtmpCamera!!.isRecording()
                || rtmpCamera!!.prepareAudio() && rtmpCamera!!.prepareVideo()) {
                rtmpCamera!!.startStream("rtmp://88.198.76.192:8000/live/developmentApp")
                Toast.makeText(this, "Started streaming", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error preparing stream, This device cant do it",
                    Toast.LENGTH_SHORT).show();
            }
        } else {
            rtmpCamera?.stopStream();
            Toast.makeText(this, "Stopped streaming", Toast.LENGTH_SHORT).show()
        }
    }

}