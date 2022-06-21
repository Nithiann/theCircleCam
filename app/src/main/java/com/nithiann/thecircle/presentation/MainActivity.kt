package com.nithiann.thecircle.presentation

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.SemanticsProperties.Error
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nithiann.thecircle.common.Constants
import com.nithiann.thecircle.presentation.ui.theme.TheCircleTheme
import com.nithiann.thecircle.presentation.aboutpage.aboutScreen
import com.nithiann.thecircle.presentation.profilepage.profileScreen
import com.nithiann.thecircle.presentation.videopage.VideoActivity
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException
import java.security.*
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
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
        super.onCreate(savedInstanceState)
        getSignature()
        setContent {
            TheCircleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.primary
                ) {
                    val navController = rememberNavController()
                    val menuItems = listOf("About me", "Live", "About us")
                    val menuIcons =
                        listOf(Icons.Filled.Person, Icons.Filled.Videocam, Icons.Filled.Group)
                    val menu = listOf(Screen.ProfileScreen, Screen.LiveScreen, Screen.AboutScreen)

                    Scaffold(
                        bottomBar = {
                            NavigationBar(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                containerColor = androidx.compose.material3.MaterialTheme.colorScheme.primaryContainer,
                                contentColor = androidx.compose.material3.MaterialTheme.colorScheme.contentColorFor(
                                    androidx.compose.material3.MaterialTheme.colorScheme.primaryContainer
                                )
                            ) {
                                val navBackStackEntry by navController.currentBackStackEntryAsState()
                                val currentDestination = navBackStackEntry?.destination

                                menu.forEachIndexed { index, screen ->
                                    NavigationBarItem(
                                        icon = {
                                            Icon(
                                                menuIcons[index],
                                                contentDescription = null
                                            )
                                        },
                                        label = {
                                            Text(
                                                text = menuItems[index], style = TextStyle(
                                                    color = androidx.compose.material3.MaterialTheme.colorScheme.contentColorFor(
                                                        androidx.compose.material3.MaterialTheme.colorScheme.primaryContainer
                                                    )
                                                )
                                            )
                                        },
                                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                        onClick = {
                                            navController.navigate(screen.route)
                                        }
                                    )
                                }
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
                    //To print System Certs only
                    if (cert.issuerDN.name.contains("system")) {
                        println(cert.issuerDN.name)
                    }

                    //To print User Certs only
                    if (cert.issuerDN.name.contains("user")) {
                        println(cert)
                    }

                    //To print all certs
                    println(cert.issuerDN.name)
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

    private fun getSignature() {
        val privateKeyContent =
            Constants.privateKey.replace("\n", "").replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "");
        val privateKeyEncoded = PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyContent));
        val kf: KeyFactory = KeyFactory.getInstance("RSA")
        val keyStore: KeyStore = KeyStore.getInstance("AndroidKeyStore").apply {
            load(null)
        }

        val privKey: PrivateKey = kf.generatePrivate(privateKeyEncoded)
        val ks: KeyStore = KeyStore.getInstance("AndroidCAStore")
        try {
            if (ks != null) {
                ks.load(null, null)
                val aliases: Enumeration<String> = ks.aliases()
                while (aliases.hasMoreElements()) {
                    val alias = aliases.nextElement() as String
                    val cert = ks.getCertificate(alias) as X509Certificate
                    //To print User Certs only
                    if (alias.contains("user")) {
                        println(alias)
                        println(
                            cert.subjectDN
                                .toString()
                                .substringAfter("=")
                                .substringBefore(",")
                        )
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

        val certificate = ks.getCertificate("user:6685520c.0")
        keyStore.setKeyEntry("key", privKey, null, arrayOf(certificate))

        val cipher = Cipher.getInstance("RSA")
        cipher.init(Cipher.ENCRYPT_MODE, privKey)
        val text = "test"
        val plaintext: ByteArray = text.toByteArray()
        val ciphertext: ByteArray = cipher.doFinal(plaintext)
        Base64.getMimeEncoder().encode(ciphertext)
    }
}

