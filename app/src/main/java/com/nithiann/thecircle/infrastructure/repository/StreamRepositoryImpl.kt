package com.nithiann.thecircle.infrastructure.repository

import com.nithiann.thecircle.domain.models.StreamCheck
import com.nithiann.thecircle.domain.repository.StreamRepository
import com.nithiann.thecircle.infrastructure.remote.Api
import javax.inject.Inject

class StreamRepositoryImpl @Inject constructor(
    private val api: Api
): StreamRepository {
    override suspend fun postStream(streamCheck: StreamCheck) {
       api.postStream(streamCheck)
    }

    override suspend fun endStream(streamCheck: StreamCheck) {
        api.checkStream(streamCheck)
    }

    override suspend fun checkStream(streamCheck: StreamCheck) {
        api.checkStream(streamCheck)
    }
}