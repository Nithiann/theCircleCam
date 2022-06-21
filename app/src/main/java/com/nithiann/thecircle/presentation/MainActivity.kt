package com.nithiann.thecircle.presentation

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nithiann.thecircle.presentation.aboutpage.aboutScreen
import com.nithiann.thecircle.presentation.profilepage.profileScreen
import com.nithiann.thecircle.presentation.ui.theme.TheCircleTheme
import com.nithiann.thecircle.presentation.videopage.VideoActivity
import com.nithiann.thecircle.presentation.videopage.VideoScreen
import dagger.hilt.android.AndroidEntryPoint
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
    private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE)

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        if (allPermissionsGranted()) {

        } else {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
        }
        getKey()
        super.onCreate(savedInstanceState)
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
                                icon = { Icon(Icons.Filled.Menu, contentDescription = null, tint = Color.White, modifier = Modifier.size(32.dp)) },
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
                                icon = { Icon(Icons.Filled.Person, contentDescription = null, tint = Color.White, modifier = Modifier.size(32.dp)) },
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
                ) {
                        innerPadding ->
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

    fun getKey() {
        val privateKey = "-----BEGIN PRIVATE KEY-----\n" +
                "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDEmwj9cJwubIDQ\n" +
                "OHYlfT5RkoEjvls9L9iDT4vcclh91AfXcS4cVj4eOOtGsOtYkFtdxPmpMQibd92V\n" +
                "XcI0cN8JVwr+B6+jTGtEu1AEBi0RhaFttemj4flsUXoaL2tQqeVIHmyqaaDU7Imh\n" +
                "+u8DlKt/lI6Ou0kJQwZymL4e17rJJJDLdRO1vcVE+yUDTBdqGxu//CFyHTjqcKB7\n" +
                "V69/m3TxdSs673DV6GWi99uxRnf3CRfmovxSGLRQxIwE7XTQU7QCUAQd5yrLxXMl\n" +
                "JTVdw/aWMuAfINA2PtNlfCTZRlAtvGznnS7nAg4kES1L33Q0f3jl30Ud4KxqOfnE\n" +
                "bMbBHMbhAgMBAAECggEABh0DWAzG284FlAlAFJAC6TrC0jo1OxSq86unCoPxtXlA\n" +
                "2dWhR0H1Qi+nPTZQyHBgruyU43UXXUI1En5tBKrCfKyyC6PF4IWGoQdUCHQACxdF\n" +
                "JZSuVFQOqJbAqGIeFk7aHCEkuYh5oZ/4nZ/1tlOf9jfH1loDfjVmf2y5c1sQ6ffa\n" +
                "OcmCB4GV2N3iHXiHSvHR6T6PzTFMyXb8UbeXOUg7Bdg2cZHES3DiKGE6crYm7Jh6\n" +
                "xYdOsMx0OqHWrwAdYs9HoLNUt/e6pDGurFw6X7iLlMXLOKT0PQVyqvy93Yk914kM\n" +
                "i0hKjK4gmgpJrrlQlOYUb2VwDM5AllhgCrf/wwjXWQKBgQDH1wpNsZYmDDj2o4kc\n" +
                "2alPhk6hJP5RglFfrd7EOhMOhbRJj9Ar/CG0c+RHIcvQD/m/I19t58yoDEByMkXX\n" +
                "Yzu2KnqbA/zPN1qyjM2f9X83MN/gdzGIQ4QazOTbiL/kUoXbjlPTzN2vrgrqPnZt\n" +
                "uYEWPhBOFns4zxjHTPYdIhje7QKBgQD7204MuDjJuvr3HjW+pSiDtiHdYJm+1sMi\n" +
                "pf73g9OxrlNH0+xjNF7J+4BZoS3yErnrHP25UsQuuDT1z186ZwBHCtIyRViOzU4F\n" +
                "qWscD7Fv22e6MaNV2oX/iifVuhPOGd8E4YER822CY6fJMi0rTZ+Pdtom9zHayyS/\n" +
                "7ZDGF3RVRQKBgGcM0chcsiOum9U7YWIaL7/Nb1CTpf1cKSAgpcYkeF09v0lLurpj\n" +
                "yvGl7Wps2A/TnSLeV8ByDsv9fWIl4HQAPPNkFlNHjB9C2SdHimVZEB/iuR+j90vg\n" +
                "HQhA7iby7pkLoPEmBL4sX4jPQ9ulGCbeyN0yZfAOkb4qtQlY+3Tsd0zFAoGALWyE\n" +
                "Sy7+rwOWN/Ou5c+L2xWCThcaI51AXINr1OBl0eoLAy1puQq8/djqcT/stXhDJ/B2\n" +
                "onIXCAYZJyxblID3P9jnyEFRk4/bvpGry8fYzL/ZmW9Sci2TdV9Jh/ajk8x+uLaj\n" +
                "PMWWvqmSnWr7UpARcyKQfe6fg0KYQjVqow+f37ECgYEAmdg3Fl7ti6q05zuuJHvo\n" +
                "Rqj8FTmOeShgWsh+CBbq6cXHaIZxhCobOtXZvf+kS03qASG7p0y9Iy1jlA4F4gZQ\n" +
                "l3EDSZs95n0ETWx1EMmzcZrDdVbiBE4kiWPSvBjtCJhVYRi0QZxbZvKBmkSpnYIQ\n" +
                "upy6f74TTHbF8pTv2O0e/JU=\n" +
                "-----END PRIVATE KEY-----"
        val privateKeyContent = privateKey.replace("\n", "").replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "");
        val privateKeyEncoded = PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyContent));
        val kf: KeyFactory = KeyFactory.getInstance("RSA")
        val ks = KeyStore.getInstance("AndroidKeyStore").apply {
            load(null)
        }

        val privKey: PrivateKey = kf.generatePrivate(privateKeyEncoded)
        val castore = KeyStore.getInstance("AndroidCAStore").apply {
            load(null)
        }
        val certificate = castore.getCertificate("user:45f5a707.0")
        ks.setKeyEntry("key", privKey, null, arrayOf(certificate))
        println(ks.getKey("key", null))
    }
}

