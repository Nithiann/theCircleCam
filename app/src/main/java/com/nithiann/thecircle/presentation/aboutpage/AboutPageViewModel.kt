package com.nithiann.thecircle.presentation.aboutpage

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nithiann.thecircle.common.Resource
import com.nithiann.thecircle.domain.use_case.getContributorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AboutPageViewModel @Inject constructor(
    private val getContributorsUseCase: getContributorsUseCase
): ViewModel() {
    private val _state = mutableStateOf(AboutState())
    val state: State<AboutState> = _state

    init {
        getContirbutors()
    }

    private fun getContirbutors() {
        getContributorsUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = AboutState(contributors = result.data?: null)
                }
                is Resource.Error -> {
                    _state.value = AboutState(error = result.message ?: "An error has occured")
                }
                is Resource.Loading -> {
                    _state.value = AboutState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}