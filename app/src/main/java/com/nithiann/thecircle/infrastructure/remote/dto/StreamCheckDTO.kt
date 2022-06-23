package com.nithiann.thecircle.infrastructure.remote.dto

import com.nithiann.thecircle.domain.models.StreamCheck

data class StreamCheckDTO(
    val streamName: String,
    val signature: String
)

fun StreamCheckDTO.toStreamCheck() : StreamCheck {
    return StreamCheck(
        streamName = streamName,
        signature = signature
    )
}
