package com.nithiann.thecircle.presentation.videopage

import android.os.Message
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nithiann.thecircle.common.Resource
import com.nithiann.thecircle.domain.use_case.getMessagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class VideoPageViewModel @Inject constructor(
    private val getMessagesUseCase: getMessagesUseCase
): ViewModel() {

    private val _state = mutableStateOf(VideoState())
    val state: State<VideoState> = _state

    init {
        getMessages()
    }

    private fun getMessages() {
        println("getting messages")
        getMessagesUseCase().onEach { result ->
            println(result)
            when(result) {
                is Resource.Success -> {
                    println("result success: "+ result)
                    _state.value = VideoState(messages = (result.data ?: null) as List<com.nithiann.thecircle.domain.models.Message>?)
                }
                is Resource.Error -> {
                    println("result error: "+ result)
                    _state.value = VideoState(error = result.response ?: "An error has occured")
                }
                is Resource.Loading -> {
                    println("result loading: "+ result)
                    _state.value = VideoState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}