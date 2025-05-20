package com.example.feature.newslist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature.newslist.domain.api.GetNewsRepository
import com.example.feature.newslist.presentation.models.State
import com.newsapp.uikit.error.ErrorScreenState
import com.newsapp.uikit.error.mapToErrorScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: GetNewsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(State(isLoading = true))
    internal val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            runCatching {
                repository.getNewsItems()
            }.onFailure { items ->
                _state.update {
                    _state.value.copy(
                        isLoading = false,
                        errorType = ErrorScreenState.NOTHING_FOUND
                    )
                }
            }.onSuccess { response ->
                response.collect { resource ->
                    if (resource.error != null) {
                        _state.update {
                            _state.value.copy(
                                isLoading = false,
                                errorType = resource.error?.mapToErrorScreen(),
                                itemList = emptyList()
                            )
                        }
                    } else {
                        _state.update {
                            _state.value.copy(
                                isLoading = false,
                                errorType = null,
                                itemList = resource.data ?: emptyList()
                            )
                        }
                    }
                }
                _state.update {
                    _state.value.copy()
                }
            }
        }
    }
}