package com.nithiann.thecircle.presentation.videopage

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

import androidx.lifecycle.LiveData

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nithiann.thecircle.common.Constants
import com.nithiann.thecircle.common.Resource
import com.nithiann.thecircle.domain.models.MessageList
import com.nithiann.thecircle.domain.use_case.getMessagesUseCase
import com.nithiann.thecircle.infrastructure.remote.dto.MessageListDTO
import com.nithiann.thecircle.infrastructure.remote.dto.toMessageList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class VideoPageViewModel @Inject constructor(
    private val getMessagesUseCase: getMessagesUseCase
): ViewModel() {


    // TODO: make sure getMessages looks over time and not only when init
    // TODO: update UI after getter is completed
    private val _state = mutableStateOf(VideoState())
    val state: State<VideoState> = _state

    init {
        getMessages()
        Log.i("VideoPageViewModel", "Has been created!")
    }

    private fun getMessages() {
            getMessagesUseCase().onEach { result ->
                println("RESULT: " + result.data.toString())
                when (result) {
                    is Resource.Success -> {
                        println("RESULT SUCCES")
                        _state.value = VideoState(messages = result.data)
                        if(Encrypt.verify(result.data!!.signature, Encrypt.getPublicKeyFromString(Constants.publicKeyServer), result.data.messages.toString())) {
                            println("SIGNATURE VERIFIED!!!!!!!")
                        } else {
                            println("SIGNATURE NOT VERIFIED!!!!!!!")
                        }
                    }
                    is Resource.Error -> {
                        println("result error: " + result)
                        _state.value = VideoState(error = result.response ?: "An error has occured")
                    }
                    is Resource.Loading -> {
                        println("result loading: " + result)
                        _state.value = VideoState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("VideoPageViewModel", "Destroyed!")
    }


//    private fun invoke() {
//        try {
//            // start loading
//            emit(Resource.Loading())
//            val messages = repository.getMessageList(getStreamerEmail(), getStreamerEmail(), getSignature()).let { it ->
//                it.toMessageList()
//            }
//            println("----------------------===------=== " + messages.signature)
//            emit(Resource.Success(messages))
//        } catch (e: HttpException) {
//            emit(Resource.Error(e.localizedMessage ?: "An expected error occured"))
//        } catch (e: IOException) {
//            emit(Resource.Error(e.localizedMessage ?: "Couldn't reach server. Please try again later."))
//        }
//    }
}