package com.nithiann.thecircle.presentation.profilepage

import android.app.Activity
import android.content.Intent
import androidx.core.app.ActivityCompat.startActivityForResult

class ProfilePageViewModel {
    fun openFile(activity: Activity) {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            setType("*/*")
        }
    }

}