package com.nithiann.thecircle.infrastructure.remote

import com.nithiann.thecircle.domain.models.StreamCheck
import com.nithiann.thecircle.domain.models.StreamStart
import com.nithiann.thecircle.infrastructure.remote.dto.ContributorDTO
import com.nithiann.thecircle.infrastructure.remote.dto.MessageDTO
import com.nithiann.thecircle.infrastructure.remote.dto.StreamCheckDTO
import com.nithiann.thecircle.infrastructure.remote.dto.StreamStartDTO
import retrofit2.http.*

interface Api {
    @GET("/api/Messages/streamer")
    suspend fun getMessages(@Query("streamerEmail") streamerEmail: String, @Query("signature") signature: String): List<MessageDTO>

    @GET("/api/Contributors")
    suspend fun getContributors(): List<ContributorDTO>

    @POST("/api/Stream")
    suspend fun postStream(@Body stream: StreamCheck) : StreamCheckDTO

    @PUT("/api/Stream/end")
    suspend fun checkStream(@Body streamCheck: StreamCheck): StreamCheckDTO
}

