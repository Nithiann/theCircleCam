package com.nithiann.thecircle.presentation.videopage

import com.nithiann.thecircle.domain.models.Contributor
import com.nithiann.thecircle.domain.models.Message
import com.nithiann.thecircle.domain.models.MessageList
import com.nithiann.thecircle.infrastructure.remote.dto.MessageListDTO

data class VideoState(
    val isLoading: Boolean = false,
    val messages: MessageList? = null,
    val error: String = ""
)