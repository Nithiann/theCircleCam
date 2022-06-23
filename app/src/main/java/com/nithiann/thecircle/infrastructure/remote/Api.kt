package com.nithiann.thecircle.infrastructure.remote

import com.nithiann.thecircle.domain.models.StreamStart
import com.nithiann.thecircle.infrastructure.remote.dto.ContributorDTO
import com.nithiann.thecircle.infrastructure.remote.dto.MessageDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {
    @GET("/api/Messages/streamer/{streamerEmail}/{signature}")
    suspend fun getMessages(@Path("streamerEmail") streamerEmail: String, @Path("signature") signature: String): List<MessageDTO>

    @GET("/api/Contributors")
    suspend fun getContributors(): List<ContributorDTO>

//    @POST("/api/Stream")
//    suspend fun postStream(@Body stream: StreamStart)
}

