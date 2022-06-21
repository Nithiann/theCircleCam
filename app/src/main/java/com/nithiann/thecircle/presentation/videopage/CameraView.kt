package com.nithiann.thecircle.presentation.videopage

import android.annotation.SuppressLint
import android.content.Context
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.util.Log
import android.view.Surface
import android.view.SurfaceView
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.nithiann.thecircle.common.PathUtils
import com.pedro.rtmp.utils.ConnectCheckerRtmp
import com.pedro.rtplibrary.rtmp.RtmpCamera1
import com.pedro.rtplibrary.rtmp.RtmpCamera2
import java.io.IOException
import java.util.*
import java.util.concurrent.Executors



@SuppressLint("StaticFieldLeak")
var _context: Context? = null
@Composable
fun CameraView(){
    val lifecycleOwner = LocalLifecycleOwner.current
    val context: Context = LocalContext.current
    _context = context;

    RtmpCamera(context)

    val cameraProviderFuture = remember {
        ProcessCameraProvider.getInstance(context!!)
    }

    val surfaceView = remember {
        SurfaceView(context!!).apply {
            id = com.nithiann.thecircle.R.id.surfaceView
        }
    }

    val previewView = remember {
        PreviewView(context!!).apply {
            id = com.nithiann.thecircle.R.id.surfaceView
        }
    }

    val cameraExecutor = remember {
        Executors.newSingleThreadExecutor()
    }

    AndroidView(factory = { surfaceView }, modifier = Modifier.fillMaxSize()) {
            SurfaceView(context!!).apply {
                id = com.nithiann.thecircle.R.id.surfaceView
            }
    }
}

private class FaceAnalyzer(): ImageAnalysis.Analyzer {
    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        //val image = imageProxy.image
        //image?.close()
    }
}

private fun RtmpCamera(context: Context) {
    val openGLView: OpenGLView = OpenGLView(context)
    val connectCheckerRtmp: ConnectCheckerRtmp = ConnectCheckerRtmp()
    val rtpmCamera: RtmpCamera1 = RtmpCamera1(openGLView, connectCheckerRtmp)
    val folder = PathUtils.recordPath
    rtpmCamera.setReTries(10);

    if (rtpmCamera.prepareAudio() && rtpmCamera.prepareVideo()) {
        rtpmCamera.startStream("rtpm://88.198.76.192/live/DaddyAndroid2")
        println(rtpmCamera.isRecording())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            if (!rtpmCamera.isRecording()) {
                try {
                    if (!folder.exists()) {
                        folder.mkdir()
                    }
                    val sdf = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
                    val currentDateAndTime = sdf.format(Date())
                    if (!rtpmCamera.isStreaming()) {
                        if (rtpmCamera.prepareAudio() && rtpmCamera.prepareVideo()) {
                            rtpmCamera.startRecord(
                                folder.getAbsolutePath()
                                    .toString() + "/" + currentDateAndTime + ".mp4"
                            )

                        } else {
                        }
                    } else {
                        rtpmCamera.startRecord(
                            folder.getAbsolutePath().toString() + "/" + currentDateAndTime + ".mp4"
                        )
                    }
                } catch (e: IOException) {
                    //rtpmCamera.stopRecord()
                }
            } else {
                //rtpmCamera.stopRecord()
            }
        } else {
        }
    } else {
        // do nothing
    }
}
