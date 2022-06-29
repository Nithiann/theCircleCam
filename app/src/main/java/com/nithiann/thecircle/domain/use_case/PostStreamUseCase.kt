package com.nithiann.thecircle.domain.use_case

import com.nithiann.thecircle.common.Resource
import com.nithiann.thecircle.domain.models.StreamCheck
import com.nithiann.thecircle.domain.repository.StreamRepository
import com.nithiann.thecircle.infrastructure.remote.Encrypt
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PostStreamUseCase @Inject constructor(
    private val repository: StreamRepository
) {
    operator fun invoke(): Flow<Resource<out Any>> = flow {
       val streamer: StreamCheck = StreamCheck(Encrypt.getEmail(), Encrypt.sign(Encrypt.hash(Encrypt.getEmail())))
        try {
            // start loading
            emit(Resource.Loading())
            val stream = repository.postStream(streamer)
            emit(Resource.Success(stream))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An expected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Couldn't reach server. Please try again later."))
        }
    }
}