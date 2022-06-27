package com.nithiann.thecircle.domain.models


// TODO: create based on new message design
data class Message(
    val id: Int,
    val msg: String,
    val sentToStreamerId: Int,
    val sendByUserId: Int
)