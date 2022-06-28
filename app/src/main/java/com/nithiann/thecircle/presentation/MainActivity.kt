package com.nithiann.thecircle.presentation

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.NetworkOnMainThreadException
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.SemanticsProperties.Error
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nithiann.thecircle.common.Constants
import com.nithiann.thecircle.infrastructure.remote.Encrypt
import com.nithiann.thecircle.presentation.aboutpage.aboutScreen
import com.nithiann.thecircle.presentation.profilepage.profileScreen
import com.nithiann.thecircle.presentation.ui.theme.TheCircleTheme
import com.nithiann.thecircle.presentation.videopage.VideoActivity
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException
import java.net.URL
import java.security.*
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import androidx.compose.material3.Text as Text1
import java.security.KeyFactory
import java.security.KeyStore
import java.security.PrivateKey
import java.security.spec.PKCS8EncodedKeySpec
import java.util.*
import javax.crypto.Cipher


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val REQUEST_CODE_PERMISSIONS = 999
    private val REQUIRED_PERMISSIONS = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {


        if (allPermissionsGranted()) {

        } else {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
        }
        super.onCreate(savedInstanceState)

        PrintInstalledCertificates()

        Encrypt.getName()
        val users = Constants.users
        for (user in users) {
            println("Hardcoded users: " + user.name)
        }



        setContent {
            Column() {
//                OptionMenu()
                BottomBar()
            }
        }
    }

    @Composable
    private fun BottomBar() {
        TheCircleTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.primary
            ) {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                Scaffold(
                    bottomBar = {
                        BottomAppBar(
                            modifier = Modifier
                                .height(65.dp),
                            cutoutShape = CircleShape,
                            elevation = 22.dp
                        ) {

                            NavigationBarItem(
                                icon = {
                                    Icon(
                                        Icons.Filled.Menu,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(32.dp)
                                    )
                                },
                                onClick = {
                                    Screen.AboutScreen.route?.let {
                                        navController.navigate(it) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }

                                    }
                                    Screen.AboutScreen.route?.let { navController.navigate(it) }
                                },
                                selected = false
                            )
                            NavigationBarItem(
                                icon = {
                                    Icon(
                                        Icons.Filled.Person,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(32.dp)
                                    )
                                },
                                onClick = {
                                    Screen.ProfileScreen.route?.let {
                                        navController.navigate(it) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }

                                    }
                                    Screen.ProfileScreen.route?.let { navController.navigate(it) }
                                },
                                selected = false
                            )
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
                        composable(Screen.LiveScreen.route) {
                            val intent = Intent(this@MainActivity, VideoActivity::class.java)
                            startActivity(intent)
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

    private fun PrintInstalledCertificates() {
        try {
            val ks: KeyStore = KeyStore.getInstance("AndroidCAStore")
            if (ks != null) {
                ks.load(null, null)
                val aliases: Enumeration<String> = ks.aliases()
                while (aliases.hasMoreElements()) {
                    val alias = aliases.nextElement() as String
                    val cert = ks.getCertificate(alias) as X509Certificate
                    //To print User Certs only
                    if (cert.issuerDN.name.contains("user")) {
                        println(cert)
                    }
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: KeyStoreException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: CertificateException) {
            e.printStackTrace()
        }
    }
}

