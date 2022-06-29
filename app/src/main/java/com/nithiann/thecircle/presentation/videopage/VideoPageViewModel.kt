package com.nithiann.thecircle.presentation.videopage

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nithiann.thecircle.common.Constants
import com.nithiann.thecircle.common.Resource
import com.nithiann.thecircle.domain.models.MessageList
import com.nithiann.thecircle.domain.use_case.getMessagesUseCase
import com.nithiann.thecircle.infrastructure.remote.Encrypt
import com.nithiann.thecircle.infrastructure.remote.dto.MessageListDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class VideoPageViewModel @Inject constructor(
    private val getMessagesUseCase: getMessagesUseCase,
): ViewModel() {
    private val _state = mutableStateOf(VideoState())
    val state: State<VideoState> = _state

    init {
        getMessages()
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
}