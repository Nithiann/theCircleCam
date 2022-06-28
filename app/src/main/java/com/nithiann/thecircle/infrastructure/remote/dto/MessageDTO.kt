package com.nithiann.thecircle.infrastructure.remote.dto

import com.nithiann.thecircle.domain.models.Message

data class MessageDTO(
    val id: Int,
    val msg: String,
    val msgSignature: String,
    val sentToStreamerId: Int,
    val sendByUserId: Int
)

fun MessageDTO.toMessage() : Message {
    return Message(
        id = id,
        msg = msg,
        msgSignature = msgSignature,
        sentToStreamerId = sentToStreamerId,
        sendByUserId = sendByUserId
    )
}
