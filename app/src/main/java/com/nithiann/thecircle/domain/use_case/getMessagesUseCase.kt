package com.nithiann.thecircle.domain.use_case

import com.nithiann.thecircle.common.Constants
import com.nithiann.thecircle.common.Resource
import com.nithiann.thecircle.domain.models.Message
import com.nithiann.thecircle.domain.repository.MessageRepository
import com.nithiann.thecircle.infrastructure.remote.Encrypt
import com.nithiann.thecircle.infrastructure.remote.dto.toMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class getMessagesUseCase @Inject constructor(
    private val repository: MessageRepository,
) {
    operator fun invoke() : Flow<Resource<out Any>> = flow {
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
        return Encrypt.getEmail()
    }

    private fun getSignature(): String {
        val hashed = Encrypt.hash(Constants.tmpStreamerEmail)
        println("Hashed: " + hashed)
        val encrypted = Encrypt.encryption(hashed)
        //val urlencoded = Encrypt.encodeHREF(encrypted)
        println("Encrypted: " + encrypted)
        val encryptedURL = Encrypt.encodeHREF(encrypted)
        println("EncryptedURL: " + encryptedURL)
        return encrypted
    }
}
