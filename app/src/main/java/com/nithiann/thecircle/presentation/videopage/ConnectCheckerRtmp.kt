package com.nithiann.thecircle.presentation.videopage

import android.util.Log
import com.pedro.rtmp.utils.ConnectCheckerRtmp

class ConnectCheckerRtmp: ConnectCheckerRtmp {
    lateinit var streamingUrl: String

    override fun onConnectionSuccessRtmp() {
        Log.i("Stream", "Connection success")
    }

    override fun onDisconnectRtmp() {
        Log.i("Stream", "Disconnected")
    }

    override fun onNewBitrateRtmp(bitrate: Long) {
        TODO("Not yet implemented")
    }

    override fun onAuthErrorRtmp() {
        Log.e("Stream", "Authentication failed")
    }

    override fun onAuthSuccessRtmp() {
        Log.i("Stream", "Authentication succeeded")
    }

    override fun onConnectionFailedRtmp(reason: String) {
        Log.i("Stream", reason)
    }

    override fun onConnectionStartedRtmp(rtmpUrl: String) {
        Log.i("Stream", "Connetion has started on " + rtmpUrl)
    }
}