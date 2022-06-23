package com.nithiann.thecircle.infrastructure.remote.dto

import com.nithiann.thecircle.domain.models.StreamStart

data class StreamStartDTO(
    val originalMessage: String,
    val originalMessageSignature: String,
    val streamerEmail: String,
    val streamerEmailSignature: String
)

fun StreamStartDTO.toStreamStart() : StreamStart {
    return StreamStart(
        originalMessage = originalMessage,
        originalMessageSignature = originalMessageSignature,
        streamerEmail = streamerEmail,
        streamerEmailSignature = streamerEmailSignature
    )
}
