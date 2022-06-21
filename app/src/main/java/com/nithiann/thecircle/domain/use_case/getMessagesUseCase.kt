package com.nithiann.thecircle.domain.use_case

import com.nithiann.thecircle.common.Constants
import com.nithiann.thecircle.common.Resource
import com.nithiann.thecircle.domain.models.Contributor
import com.nithiann.thecircle.domain.models.Message
import com.nithiann.thecircle.domain.repository.AboutRepository
import com.nithiann.thecircle.domain.repository.MessageRepository
import com.nithiann.thecircle.infrastructure.remote.dto.toContributor
import com.nithiann.thecircle.infrastructure.remote.dto.toMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.security.KeyFactory
import java.security.KeyStore
import java.security.PrivateKey
import java.security.spec.PKCS8EncodedKeySpec
import java.util.*
import javax.crypto.Cipher
import javax.inject.Inject

class getMessagesUseCase @Inject constructor(
    private val repository: MessageRepository,
) {
    operator fun invoke() : Flow<Resource<List<Message>>> = flow {
        try {
            // start loading
            emit(Resource.Loading())
            val messages = repository.getMessages(getStreamerEmail(), getSignature()).map { it.toMessage() }
            emit(Resource.Success(messages))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An expected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Couldn't reach server. Please try again later."))
        }
    }

    private fun getStreamerEmail(): String {
        return Constants.tmpStreamerEmail
    }

    private fun getSignature(): String {
        val privateKeyContent =
            Constants.privateKey.replace("\n", "").replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "");
        val privateKeyEncoded = PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyContent));
        val kf: KeyFactory = KeyFactory.getInstance("RSA")
        val ks = KeyStore.getInstance("AndroidKeyStore").apply {
            load(null)
        }

        val privKey: PrivateKey = kf.generatePrivate(privateKeyEncoded)
        val castore = KeyStore.getInstance("AndroidCAStore").apply {
            load(null)
        }

        println(castore)

        val certificate = castore.getCertificate("user:45f5a707.0")
        ks.setKeyEntry("key", privKey, null, arrayOf(certificate))

        val cipher = Cipher.getInstance("RSA")
        cipher.init(Cipher.ENCRYPT_MODE, privKey)
        val text = "test"
        val plaintext: ByteArray = text.toByteArray()
        val ciphertext: ByteArray = cipher.doFinal(plaintext)
        return "hello"
    }
}
