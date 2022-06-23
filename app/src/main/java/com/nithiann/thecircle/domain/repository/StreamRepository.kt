package com.nithiann.thecircle.domain.repository

import com.nithiann.thecircle.domain.models.StreamCheck

interface StreamRepository {
    suspend fun postStream(streamCheck: StreamCheck)
    suspend fun endStream(streamCheck: StreamCheck)
    suspend fun checkStream(streamCheck: StreamCheck)
}