package com.nithiann.thecircle.presentation.videopage

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun videoScreen(navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Live page")
    }
}