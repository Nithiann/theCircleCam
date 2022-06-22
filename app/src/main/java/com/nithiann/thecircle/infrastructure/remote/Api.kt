package com.nithiann.thecircle.infrastructure.remote

import com.nithiann.thecircle.infrastructure.remote.dto.ContributorDTO
import com.nithiann.thecircle.infrastructure.remote.dto.MessageDTO
import retrofit2.http.GET

interface Api {
    @GET("/api/Contributors")
    suspend fun getContributors(): List<ContributorDTO>

    @GET("/api/Messages/streamer/:streamerEmail/:signature")
    suspend fun getMessages(streamerEmail: String, signature: String): List<MessageDTO>


}

