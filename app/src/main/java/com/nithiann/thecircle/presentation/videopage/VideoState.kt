package com.nithiann.thecircle.presentation.videopage

import com.nithiann.thecircle.domain.models.Contributor
import com.nithiann.thecircle.domain.models.Message

data class VideoState (
    val isLoading: Boolean = false,
    val messages: List<Message>? = null,
    val error: String = ""
)