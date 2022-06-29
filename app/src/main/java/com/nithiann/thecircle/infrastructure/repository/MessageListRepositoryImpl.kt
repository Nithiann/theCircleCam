package com.nithiann.thecircle.infrastructure.repository

import com.nithiann.thecircle.domain.models.MessageList
import com.nithiann.thecircle.domain.repository.MessageListRepository
import com.nithiann.thecircle.infrastructure.remote.Api
import com.nithiann.thecircle.infrastructure.remote.dto.MessageListDTO
import javax.inject.Inject

class MessageListRepositoryImpl @Inject constructor(
    private val api: Api
): MessageListRepository {

    override suspend fun getMessageList(
        senderEmail: String,
        streamerEmail: String,
        signature: String
    ): MessageListDTO {
        return api.getMessageList(senderEmail, streamerEmail, signature);
    }
}