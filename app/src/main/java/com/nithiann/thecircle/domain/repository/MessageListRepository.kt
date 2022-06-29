package com.nithiann.thecircle.domain.repository

import com.nithiann.thecircle.domain.models.MessageList
import com.nithiann.thecircle.infrastructure.remote.dto.MessageListDTO

interface MessageListRepository {
    suspend fun getMessageList(senderEmail: String, streamerEmail: String, signature: String): MessageListDTO
}