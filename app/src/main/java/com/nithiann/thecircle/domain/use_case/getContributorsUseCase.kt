package com.nithiann.thecircle.domain.use_case

import com.nithiann.thecircle.common.Resource
import com.nithiann.thecircle.domain.models.Contributor
import com.nithiann.thecircle.domain.repository.AboutRepository
import com.nithiann.thecircle.infrastructure.remote.dto.toContributor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class getContributorsUseCase @Inject constructor(
    private val repository: AboutRepository
) {
    operator fun invoke() : Flow<Resource<List<Contributor>>> = flow {
        try {
            // start loading
            emit(Resource.Loading())
            val contributors = repository.getContributors().map { it.toContributor() }
            emit(Resource.Success(contributors))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An expected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Couldn't reach server. Please try again later."))
        }
    }
}