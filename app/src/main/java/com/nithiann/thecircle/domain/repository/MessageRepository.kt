package com.nithiann.thecircle.domain.repository

import com.nithiann.thecircle.infrastructure.remote.dto.MessageDTO

interface MessageRepository {
    suspend fun getMessages(senderEmail: String, streamerEmail: String, signature: String): Boolean
}