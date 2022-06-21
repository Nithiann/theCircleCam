package com.nithiann.thecircle.presentation.profilepage

import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat.startActivity
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.navigation.NavController
import com.nithiann.thecircle.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun profileScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(state = rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        profile();
        Text(
            "About me",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(vertical = 16.dp),
        );
        Text(
            text = "Naam: Groen\n" +
                    "Leeftijd: 12\n" +
                    "Help: Plz send help\n",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 5.dp),
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun profile(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.padding(top = 15.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            contentScale = ContentScale.Fit,

            modifier = Modifier.padding(vertical = 16.dp)
        )
    }
}




