package com.nithiann.thecircle.domain.repository

interface StreamRepository {
    suspend fun postStream(): Void
    suspend fun endStream(): Void
    suspend fun checkStream(): Void
}