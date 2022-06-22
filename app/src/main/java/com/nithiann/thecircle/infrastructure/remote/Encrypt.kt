package com.nithiann.thecircle.infrastructure.remote

import com.nithiann.thecircle.common.Constants
import java.io.IOException
import java.net.URLEncoder
import java.security.*
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.security.spec.PKCS8EncodedKeySpec
import java.util.*
import javax.crypto.Cipher



object Encrypt {

    val privateKeyContent =
        Constants.privateKey.replace("\n", "").replace("-----BEGIN PRIVATE KEY-----", "")
            .replace("-----END PRIVATE KEY-----", "");
    val privateKeyEncoded = PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyContent));
    val kf: KeyFactory = KeyFactory.getInstance("RSA")
    val privKey: PrivateKey = kf.generatePrivate(privateKeyEncoded)


    fun hash(email: String): ByteArray {
        val bytes = email.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest
    /*.fold("", { str, it -> str + "%02x".format(it) })*/
    }

    fun getEmail(): String {
        val ks = getCAStore();
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
                         return cert.subjectDN
                             .toString()
                             .substringAfter("=")
                             .substringBefore(",")
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
        return "";
    }

    fun encryption (input: ByteArray): String {
        val keyStore = getKeyStore()
        val ks = getCAStore()
        val certificate = ks.getCertificate("user:6685520c.0")
        keyStore.setKeyEntry("key", privKey, null, arrayOf(certificate))
        val cipher = Cipher.getInstance("RSA")
        cipher.init(Cipher.ENCRYPT_MODE, privKey)
        val plaintext: ByteArray = input
        val ciphertext: ByteArray = cipher.doFinal(plaintext)
        Base64.getMimeEncoder().encode(ciphertext)
        val par = String(Base64.getMimeEncoder().encode(ciphertext))
        return(par)
    }

    fun encodeHREF(input: String): String {
        return URLEncoder.encode(input, "utf-8")
    }

    private fun getCAStore ():KeyStore {
        return KeyStore.getInstance("AndroidCAStore").apply {
            load(null)
        }
    }

    private fun getKeyStore():KeyStore {
        return KeyStore.getInstance("AndroidKeyStore").apply {
            load(null)
        }
    }
}