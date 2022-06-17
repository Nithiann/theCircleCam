package com.nithiann.thecircle.presentation.videopage

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.pedro.rtmp.utils.ConnectCheckerRtmp
import com.pedro.rtplibrary.rtmp.RtmpCamera1
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.IOException
import java.util.*

@Composable
fun VideoScreen(navController: NavController) {
    val context = LocalContext.current
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        CameraView()
    }
}

