package com.nithiann.thecircle.domain.use_case

import com.nithiann.thecircle.common.Resource
import com.nithiann.thecircle.domain.models.StreamCheck
import com.nithiann.thecircle.domain.repository.StreamRepository
import com.nithiann.thecircle.infrastructure.remote.Encrypt
import com.nithiann.thecircle.infrastructure.remote.dto.toContributor
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class CheckStreamUseCase @Inject constructor(
    private val repository: StreamRepository
) {
    operator fun invoke() : Flow<Resource<out Any>> = flow {
        val streamer: StreamCheck = StreamCheck(
            Encrypt.getEmail(), Encrypt.encryption(
                Encrypt.hash(
                    Encrypt.getEmail()
                )
            )
        )
        try {
            // start loading
            emit(Resource.Loading())
            val streamer = repository.checkStream(streamer)
            emit(Resource.Success(streamer))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An expected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Couldn't reach server. Please try again later."))
        }
    }
}