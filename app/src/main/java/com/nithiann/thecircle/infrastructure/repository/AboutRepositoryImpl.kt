package com.nithiann.thecircle.infrastructure.repository

import com.nithiann.thecircle.domain.repository.AboutRepository
import com.nithiann.thecircle.infrastructure.remote.Api
import com.nithiann.thecircle.infrastructure.remote.dto.ContributorDTO
import javax.inject.Inject

class AboutRepositoryImpl @Inject constructor(
    private val api: Api
): AboutRepository {
    override suspend fun getContributors(): List<ContributorDTO> {
        return api.getContributors()
    }
}