package com.nithiann.thecircle.infrastructure.remote

import com.nithiann.thecircle.infrastructure.remote.dto.ContributorDTO
import retrofit2.http.GET

interface Api {
    @GET("/api/Contributors")
    suspend fun getContributors(): List<ContributorDTO>
}

