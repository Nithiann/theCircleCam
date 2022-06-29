package com.nithiann.thecircle.infrastructure.remote

import com.nithiann.thecircle.common.Constants
import java.io.IOException
import java.math.BigInteger
import java.net.URLEncoder
import java.security.*
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.RSAPublicKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.*
import javax.crypto.Cipher



object Encrypt {

    private val privateKeyContent =
        Constants.privateKey.replace("\n", "").replace("-----BEGIN PRIVATE KEY-----", "")
            .replace("-----END PRIVATE KEY-----", "")
    private val privateKeyEncoded = PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyContent));
    private val kf: KeyFactory = KeyFactory.getInstance("RSA")
    private val privKey: PrivateKey = kf.generatePrivate(privateKeyEncoded)

    fun hash(email: String): ByteArray {
        val bytes = email.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest
    }

    fun getName(): String {
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
//                        println(
//                            cert.issuerDN.name.toString().substringAfter(",").substringAfter("=").substringBefore(",").substringBefore(" ")
//                        )
                        return cert.issuerDN.name.toString().substringAfter(",").substringAfter("=").substringBefore(",").substringBefore(" ")
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
//                        println(
//                            cert.subjectDN
//                                .toString()
//                                .substringAfter("=")
//                                .substringBefore(",")
//                        )
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

    fun sign (input: ByteArray): String {
        lateinit var cert: X509Certificate
        val keyStore = getKeyStore()
        val ks = getCAStore()
        val aliases: Enumeration<String> = ks.aliases()
        while (aliases.hasMoreElements()) {
            val alias = aliases.nextElement() as String
            cert = ks.getCertificate(alias) as X509Certificate
        }
        keyStore.setKeyEntry("key", privKey, null, arrayOf(cert))
        val signature = Signature.getInstance("NONEwithRSA")
        signature.initSign(privKey)
        signature.update(input)
        val ciphertext: ByteArray = signature.sign()
        val par = String(Base64.getEncoder().encode(ciphertext))
        return par
    }

    fun getPublicKeyFromString(key: String): X509EncodedKeySpec {
        val publicKeyContent = key.replace("\n", "").replace("-----BEGIN PUBLIC KEY-----", "")
            .replace("-----END PUBLIC KEY-----", "").replace(" ", "")

        println("PUBLIC KEY CONTENT = " + publicKeyContent)
        val array: ByteArray = Base64.getMimeDecoder().decode(publicKeyContent);
        //val array: ByteArray = Base64.getDecoder().decode(key)
        println("PUBLIC KEY = " + String(array))
        val spec: X509EncodedKeySpec = X509EncodedKeySpec(array)
        return spec
    }

    fun verify(signature: String, publicKey: X509EncodedKeySpec, content: String): Boolean {
        val decodedSignature: ByteArray = Base64.getDecoder().decode(signature)
        val cipher = Cipher.getInstance("RSA")
        val key: PublicKey = kf.generatePublic(publicKey)
        cipher.init(Cipher.DECRYPT_MODE, key)
        val decryptedSig = cipher.doFinal(decodedSignature)

        val bytes = content.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val hashedContent = md.digest(bytes)
//        println("DECODED SIGNATURE = " + decodedSignature)
//        println("DECRYPTED SIGNATURE = " + String(decryptedSig))
//        println("HASHED CONTENT = " + String(hashedContent))

        return decryptedSig.equals(hashedContent)
    }

    fun encodeHREF(input: String): String {
        return URLEncoder.encode(input, "ASCII")
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