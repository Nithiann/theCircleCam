package com.nithiann.thecircle.infrastructure.repository

import com.nithiann.thecircle.domain.repository.MessageRepository
import com.nithiann.thecircle.infrastructure.remote.Api
import com.nithiann.thecircle.infrastructure.remote.dto.MessageDTO
import javax.inject.Inject

class MessageRepositoryImpl @Inject constructor(
    private val api: Api
): MessageRepository {
    override suspend fun getMessages(streamerEmail: String, signature: String): List<MessageDTO> {
        return api.getMessages(streamerEmail, signature);
    }
}