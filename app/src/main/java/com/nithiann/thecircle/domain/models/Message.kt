package com.nithiann.thecircle.domain.models

data class Message(
    val id: Int,
    val msg: String,
    val msgSignature: String,
    val sentToStreamerId: Int,
    val sendByUserId: Int
)