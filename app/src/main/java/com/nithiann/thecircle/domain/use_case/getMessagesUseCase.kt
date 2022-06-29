package com.nithiann.thecircle.domain.use_case

import android.util.Log
import com.nithiann.thecircle.common.Resource
import com.nithiann.thecircle.domain.models.MessageList
import com.nithiann.thecircle.domain.repository.MessageListRepository
import com.nithiann.thecircle.infrastructure.remote.Encrypt
import com.nithiann.thecircle.infrastructure.remote.dto.MessageListDTO
import com.nithiann.thecircle.infrastructure.remote.dto.toMessageList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class getMessagesUseCase @Inject constructor(
    private val repository: MessageListRepository,
) {
    operator fun invoke() : Flow<Resource<MessageList>> = flow {
        try {
            // start loading

            val messages = repository.getMessageList(getStreamerEmail(), getStreamerEmail(), getSignature()).let { it ->
                it.toMessageList()
            }

            Log.i("getMessagesUseCase - invoke", messages.toString())
            emit(Resource.Success(messages!!))
        } catch (e: HttpException) {
            emit(Resource.Loading())
            emit(Resource.Error(e.localizedMessage ?: "An expected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Couldn't reach server. Please try again later."))
        }
    }


    private fun getStreamerEmail(): String {
        return Encrypt.getEmail()
    }

    private fun getSignature(): String {
        val hashed = Encrypt.hash(Encrypt.getEmail()+Encrypt.getEmail())
        //println("Hashed: " + hashed)
        val encrypted = Encrypt.sign(hashed)
        println("Encrypted: " + encrypted)
        val encryptedURL = Encrypt.encodeHREF(encrypted)
        println("EncryptedURL: " + encryptedURL)
        return encrypted
    }
}
