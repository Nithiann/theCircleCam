package com.nithiann.thecircle.domain.use_case

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
import javax.inject.Inject

class getMessagesUseCase @Inject constructor(
    private val repository: MessageRepository,
    private val streamerEmail: String,
    private val signature: String
) {
    operator fun invoke() : Flow<Resource<List<Message>>> = flow {
        try {
            // start loading
            emit(Resource.Loading())
            val messages = repository.getMessages(streamerEmail, signature).map { it.toMessage() }
            emit(Resource.Success(messages))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An expected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Couldn't reach server. Please try again later."))
        }
    }
}