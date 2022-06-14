package com.nithiann.thecircle.presentation.aboutpage


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nithiann.thecircle.domain.models.Contributor
import dagger.hilt.android.lifecycle.HiltViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun aboutScreen(navController: NavController, viewModel: AboutPageViewModel = hiltViewModel()) {
    val state = viewModel.state.value;

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        state.contributors?.forEach { contributor ->
            Text("hello," + contributor.firstName + " " + contributor.lastName)
        }
    }
}