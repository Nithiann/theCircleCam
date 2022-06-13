package com.nithiann.thecircle.domain.repository

import com.nithiann.thecircle.infrastructure.remote.dto.ContributorDTO

interface AboutRepository {
    suspend fun getContributors(): ContributorDTO
}