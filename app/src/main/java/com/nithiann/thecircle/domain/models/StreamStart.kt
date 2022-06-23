package com.nithiann.thecircle.domain.models

import java.security.Signature

data class StreamStart(
    val originalMessage: String,
    val originalMessageSignature: String,
    val streamerEmail: String,
    val streamerEmailSignature: String
)