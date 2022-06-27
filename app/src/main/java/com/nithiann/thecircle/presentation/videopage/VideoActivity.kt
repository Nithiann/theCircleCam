package com.nithiann.thecircle.presentation.videopage

import android.os.Bundle
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.gson.GsonBuilder
import com.nithiann.thecircle.R
import com.nithiann.thecircle.common.Constants
import com.nithiann.thecircle.common.PathUtils.updateGallery
import com.nithiann.thecircle.domain.repository.MessageRepository
import com.nithiann.thecircle.domain.use_case.getMessagesUseCase
import com.nithiann.thecircle.infrastructure.remote.Api
import com.nithiann.thecircle.infrastructure.remote.Encrypt
import com.nithiann.thecircle.infrastructure.repository.MessageRepositoryImpl
import com.nithiann.thecircle.presentation.videopage.VideoPageViewModel
import com.pedro.rtplibrary.rtmp.RtmpCamera1
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.IOException
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class VideoActivity: FragmentActivity(), SurfaceHolder.Callback, View.OnClickListener {
    var rtmpCamera: RtmpCamera1? = null
    var sButton: Button? = null
    var streamButton: Button? = null
    private lateinit var stream: VideoPageViewModel
    //@SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContent { MessageScreen() }
        setContentView(com.nithiann.thecircle.R.layout.texture_layout)
        val connectCheckerRtmp: ConnectCheckerRtmp = ConnectCheckerRtmp()
        val openGlView: SurfaceView = findViewById(com.nithiann.thecircle.R.id.surfaceView)
        sButton = findViewById(R.id.switch_camera)
        sButton!!.setOnClickListener(this)
        streamButton = findViewById(R.id.b_start_stop)
        streamButton!!.setOnClickListener(this)
        rtmpCamera = RtmpCamera1(openGlView, connectCheckerRtmp)
        openGlView.holder.addCallback(this);


        //this.getMessages()
        //openGlView.setOnTouchListener(this);
    }

    @Composable
    fun MessageScreen(viewModel: VideoPageViewModel = hiltViewModel()) {
        val state = viewModel.state.value

        println(state)
        Modifier.alpha(0f)
        state.messages?.forEach { message ->
         println("hello wolrd")
        //println(message)
        }
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
                rtmpCamera?.startStream("rtmp://88.198.76.192/live/" + Encrypt.getName())
                startStreaming()
                Toast.makeText(this, "Started streaming", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error preparing stream, This device cant do it",
                    Toast.LENGTH_SHORT).show();
            }
        } else {
            rtmpCamera?.stopStream();
            stopStreaming()
            Toast.makeText(this, "Stopped streaming", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startStreaming(){

        // Create JSON using JSONObject
        val jsonObject = JSONObject()
        jsonObject.put("streamName", Encrypt.getName())
        jsonObject.put("signature", Encrypt.encryption(Encrypt.hash(Encrypt.getName())))

        // Convert JSONObject to String
        val jsonObjectString = jsonObject.toString()

        GlobalScope.launch(Dispatchers.IO) {
            val url = URL(Constants.BASE_URL2 + "api/Stream")
            val httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.requestMethod = "POST"
            httpURLConnection.setRequestProperty("Content-Type", "application/json") // The format of the content we're sending to the server
            httpURLConnection.setRequestProperty("Accept", "application/json") // The format of response we want to get from the server
            httpURLConnection.doInput = true
            httpURLConnection.doOutput = true

            // Send the JSON we created
            val outputStreamWriter = OutputStreamWriter(httpURLConnection.outputStream)
            outputStreamWriter.write(jsonObjectString)
            outputStreamWriter.flush()

            // Check if the connection is successful
            val responseCode = httpURLConnection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val response = httpURLConnection.inputStream.bufferedReader()
                    .use { it.readText() }  // defaults to UTF-8
                withContext(Dispatchers.Main) {

                    // Convert raw JSON to pretty JSON using GSON library
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(response)
                    Log.d("Pretty Printed JSON :", prettyJson)

                }
            } else {
                Log.e("HTTPURLCONNECTION_ERROR", responseCode.toString())
            }
        }
    }

    private fun stopStreaming(){

        // Create JSON using JSONObject
        val jsonObject = JSONObject()
        jsonObject.put("streamName", Encrypt.getName())
        jsonObject.put("signature", Encrypt.encryption(Encrypt.hash(Encrypt.getName())))

        // Convert JSONObject to String
        val jsonObjectString = jsonObject.toString()

        GlobalScope.launch(Dispatchers.IO) {
            val url = URL(Constants.BASE_URL2 + "api/Stream/end")
            val httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.requestMethod = "PUT"
            httpURLConnection.setRequestProperty("Content-Type", "application/json") // The format of the content we're sending to the server
            httpURLConnection.setRequestProperty("Accept", "application/json") // The format of response we want to get from the server
            httpURLConnection.doInput = true
            httpURLConnection.doOutput = true

            // Send the JSON we created
            val outputStreamWriter = OutputStreamWriter(httpURLConnection.outputStream)
            outputStreamWriter.write(jsonObjectString)
            outputStreamWriter.flush()

            // Check if the connection is successful
            val responseCode = httpURLConnection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val response = httpURLConnection.inputStream.bufferedReader()
                    .use { it.readText() }  // defaults to UTF-8
                withContext(Dispatchers.Main) {

                    // Convert raw JSON to pretty JSON using GSON library
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(response)
                    Log.d("Pretty Printed JSON :", prettyJson)

                }
            } else {
                Log.e("HTTPURLCONNECTION_ERROR", responseCode.toString())
            }
        }
    }

}