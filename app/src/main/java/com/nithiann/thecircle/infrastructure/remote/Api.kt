package com.nithiann.thecircle.infrastructure.remote

import com.nithiann.thecircle.domain.models.StreamCheck
import com.nithiann.thecircle.domain.models.StreamStart
import com.nithiann.thecircle.infrastructure.remote.dto.*
import retrofit2.http.*

interface Api {
    @GET("/api/Messages/streamer")
    suspend fun getMessageList(@Query("senderEmail") senderEmail: String, @Query("streamerEmail") streamerEmail: String, @Query("signature") signature: String): MessageListDTO

    @GET("/api/Contributors")
    suspend fun getContributors(): List<ContributorDTO>

    @POST("/api/Stream")
    suspend fun postStream(@Body stream: StreamCheck) : StreamCheckDTO

    @PUT("/api/Stream/end")
    suspend fun checkStream(@Body streamCheck: StreamCheck): StreamCheckDTO
}

