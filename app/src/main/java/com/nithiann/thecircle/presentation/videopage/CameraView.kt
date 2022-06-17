package com.nithiann.thecircle.presentation.videopage

import android.R
import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.Log
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
import androidx.core.graphics.PathUtils
import com.nithiann.thecircle.R
import com.pedro.rtmp.utils.ConnectCheckerRtmp
import com.pedro.rtplibrary.rtmp.RtmpCamera1
import java.io.IOException
import java.util.concurrent.Executors


@SuppressLint("StaticFieldLeak")
private var context: Context? = null;
@Composable
fun CameraView(){
    context = LocalContext.current;
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    val cameraProviderFuture = remember {
        ProcessCameraProvider.getInstance(context)
    }

    val previewView = remember {
        PreviewView(context).apply {
            id = R.id.preview_view
        }
    }

    val cameraExecutor = remember {
        Executors.newSingleThreadExecutor()
    }

    AndroidView(factory = { previewView }, modifier = Modifier.fillMaxSize()) {
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }

            val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

            val faceAnalysis = ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()
                .also {
                    it.setAnalyzer(cameraExecutor, FaceAnalyzer())
                }


            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(lifecycleOwner, cameraSelector, preview, faceAnalysis)
            } catch (e: Exception) {
                Log.e("Exception", "CameraX not working!")
            }
        }, ContextCompat.getMainExecutor(context))
    }

}

private class FaceAnalyzer(): ImageAnalysis.Analyzer {
    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        val image = imageProxy.image
        RtmpCamera()
        image?.close()
    }
}

private fun RtmpCamera() {
    val openGLView: OpenGLView = OpenGLView(context)
    val connectCheckerRtmp: ConnectCheckerRtmp = com.nithiann.thecircle.presentation.videopage.ConnectCheckerRtmp();
    val rtpmCamera: RtmpCamera1 = RtmpCamera1(openGLView, connectCheckerRtmp);
    val folder = PathUtils
    rtpmCamera.setReTries(10);

    if (rtpmCamera.prepareAudio() && rtpmCamera.prepareVideo()) {
        rtpmCamera.startStream("rtmp://88.198.76.192/live/DaddyAndroid")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            if (!rtpmCamera.isRecording()) {
                try {
                    if (!folder.exists()) {
                        folder.mkdir()
                    }
                    val sdf = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
                    currentDateAndTime = sdf.format(Date())
                    if (!rtmpCamera1.isStreaming()) {
                        if (rtmpCamera1.prepareAudio() && rtmpCamera1.prepareVideo()) {
                            rtmpCamera1.startRecord(
                                folder.getAbsolutePath()
                                    .toString() + "/" + currentDateAndTime + ".mp4"
                            )
                            bRecord.setText(R.string.stop_record)
                            Toast.makeText(this, "Recording... ", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(
                                this, "Error preparing stream, This device cant do it",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        rtpmCamera.startRecord(
                            folder.getAbsolutePath().toString() + "/" + currentDateAndTime + ".mp4"
                        )
                        bRecord.setText(R.string.stop_record)
                        Toast.makeText(this, "Recording... ", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: IOException) {
                    rtpmCamera.stopRecord()
                    PathUtils.updateGallery(
                        context,
                        folder.getAbsolutePath().toString() + "/" + currentDateAndTime + ".mp4"
                    )
                    bRecord.setText(R.string.start_record)
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show()
                }
            } else {
                rtmpCamera.stopRecord()
                PathUtils.updateGallery(
                    this,
                    folder.getAbsolutePath().toString() + "/" + currentDateAndTime + ".mp4"
                )
                bRecord.setText(R.string.start_record)
            }
        } else {
        }
    } else {
        // do nothing
    }
}

private fun setRecording() {

}