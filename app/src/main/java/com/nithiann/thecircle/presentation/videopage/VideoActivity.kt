package com.nithiann.thecircle.presentation.videopage

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.graphics.PathUtils
import com.nithiann.thecircle.R
import com.nithiann.thecircle.common.PathUtils.recordPath
import com.nithiann.thecircle.common.PathUtils.updateGallery
import com.pedro.rtplibrary.rtmp.RtmpCamera1
import java.util.*

class VideoActivity: ComponentActivity(), SurfaceHolder.Callback {
    var rtmpCamera: RtmpCamera1? = null
    val folder = null;
    //@SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.texture_layout)
        val folder = com.nithiann.thecircle.common.PathUtils.recordPath
        val connectCheckerRtmp: ConnectCheckerRtmp = ConnectCheckerRtmp()
        val openGlView: OpenGLView = findViewById(R.id.surfaceView)
        rtmpCamera = RtmpCamera1(openGlView, connectCheckerRtmp)
        openGlView.getHolder().addCallback(this);
        //openGlView.setOnTouchListener(this);
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        //do nothing
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        rtmpCamera?.startPreview()
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
}