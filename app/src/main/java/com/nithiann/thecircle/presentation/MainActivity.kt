package com.nithiann.thecircle.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nithiann.thecircle.presentation.ui.theme.TheCircleTheme
import com.nithiann.thecircle.presentation.aboutpage.aboutScreen
import com.nithiann.thecircle.presentation.videopage.VideoScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheCircleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.primary
                ) {
                    val navController = rememberNavController()
                    val menuItems = listOf("About me", "Live", "About us")
                    val menuIcons = listOf(Icons.Filled.Person, Icons.Filled.Videocam, Icons.Filled.Group)
                    val menu = listOf(Screen.AboutScreen, Screen.LiveScreen, Screen.AboutScreen)

                    Scaffold(
                        bottomBar = {
                            NavigationBar(modifier = Modifier
                                .fillMaxWidth(),
                                containerColor = androidx.compose.material3.MaterialTheme.colorScheme.primaryContainer,
                                contentColor = androidx.compose.material3.MaterialTheme.colorScheme.contentColorFor(androidx.compose.material3.MaterialTheme.colorScheme.primaryContainer)
                                ) {
                                val navBackStackEntry by navController.currentBackStackEntryAsState()
                                val currentDestination = navBackStackEntry?.destination

                                menu.forEachIndexed { index, screen ->
                                    NavigationBarItem(
                                        icon = { Icon(menuIcons[index], contentDescription = null)},
                                        label = {Text(text = menuItems[index],style = TextStyle(color = androidx.compose.material3.MaterialTheme.colorScheme.contentColorFor(
                                            androidx.compose.material3.MaterialTheme.colorScheme.primaryContainer)) )},
                                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                        onClick = {
                                            navController.navigate(screen.route)
                                        }
                                    )
                                }
                            }
                        }
                    ) { innerPadding ->
                        NavHost(navController, startDestination = Screen.AboutScreen.route, Modifier.padding(innerPadding)) {
                            composable(Screen.AboutScreen.route) { aboutScreen(navController) }
                            composable(Screen.LiveScreen.route) { VideoScreen(navController) }
                        }

                    }
                }
            }
        }
    }
}
