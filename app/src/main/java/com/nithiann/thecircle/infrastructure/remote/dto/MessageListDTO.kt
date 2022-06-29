package com.nithiann.thecircle.infrastructure.remote.dto

import com.nithiann.thecircle.domain.models.Message
import com.nithiann.thecircle.domain.models.MessageList

data class MessageListDTO(
    val signature:String,
    val messages: List<Message>

)

fun MessageListDTO.toMessageList() : MessageList {
    return MessageList(
        messages = messages,
        signature = signature
    )
}
