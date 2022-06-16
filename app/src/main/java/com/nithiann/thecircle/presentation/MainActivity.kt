package com.nithiann.thecircle.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.drawable.shapes.Shape
import android.os.Bundle
import android.service.controls.actions.FloatAction
import android.text.style.BackgroundColorSpan
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nithiann.thecircle.presentation.ui.theme.TheCircleTheme
import com.nithiann.thecircle.presentation.aboutpage.aboutScreen
import com.nithiann.thecircle.presentation.profilepage.profileScreen
import com.nithiann.thecircle.presentation.videopage.VideoScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import androidx.compose.material.Text as Text1


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val REQUEST_CODE_PERMISSIONS = 999
    private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        if (allPermissionsGranted()) {

        } else {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
        }
        super.onCreate(savedInstanceState)
        setContent {
            TheCircleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.primary
                ) {
                    val navController = rememberNavController()
                    val menuIcons =
                        listOf(Icons.Filled.Menu, Icons.Filled.Person)
                    val menu = listOf(Screen.AboutScreen, Screen.ProfileScreen)

                    Scaffold(
                        bottomBar = {
                            BottomAppBar(
                                modifier = Modifier
                                    .height(65.dp),
                                cutoutShape = CircleShape,
                                elevation = 22.dp
                            ) {
                                val navBackStackEntry by navController.currentBackStackEntryAsState()
                                val currentDestination = navBackStackEntry?.destination

                                menu.forEachIndexed { index, screen ->
                                    NavigationBarItem(
                                        icon = {
                                            Icon(
                                                menuIcons[index],
                                                contentDescription = null,
                                                tint = Color.White
                                            )
                                        },
                                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,

                                        onClick = {
                                            navController.navigate(screen.route)
                                        }
                                    )
                                }
                            }
                        },
                        floatingActionButtonPosition = FabPosition.Center,
                        isFloatingActionButtonDocked = true,
                        floatingActionButton = {
                            FloatingActionButton(
                                shape = CircleShape,
                                onClick = {
                                    Screen.LiveScreen.route?.let {
                                        navController.navigate(it) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                    Screen.LiveScreen.route?.let { navController.navigate(it) }
                                },
                                contentColor = Color.Black
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Videocam,
                                    contentDescription = "Add icon",
                                    tint = Color.Black
                                )
                            }
                        }
                    ) { innerPadding ->
                        NavHost(
                            navController,
                            startDestination = Screen.AboutScreen.route,
                            Modifier.padding(innerPadding)
                        ) {
                            composable(Screen.ProfileScreen.route) { profileScreen(navController) }
                            composable(Screen.AboutScreen.route) { aboutScreen(navController) }
                            composable(Screen.LiveScreen.route) { VideoScreen(navController) }
                        }

                    }
                }
            }
        }
    }



    private fun allPermissionsGranted(): Boolean {
        for (permission in REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(
                    this, permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true
    }
}

