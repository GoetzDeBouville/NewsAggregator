package com.example.feature.newslist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.models.ErrorType
import com.example.core.domain.models.Item
import com.example.core.domain.models.Resource
import com.example.core.domain.models.Resource.Error
import com.example.core.resources.R
import com.example.feature.newslist.domain.api.GetNewsRepository
import com.example.feature.newslist.presentation.models.Event
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

    private var fullItemList: List<Item> = emptyList()

    init {
        loadData()
    }

    internal fun accept(intent: Event) {
        when (intent) {
            is Event.ClearSearch -> clearQuery()
            is Event.ClearToast -> {
                _state.update {
                    it.copy(
                        toastMessage = null
                    )
                }
            }

            is Event.SearchTextChanged -> filterItemsByQuery(intent.query)
            is Event.Refresh -> {
                clearQuery()
                loadData()
            }
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            _state.update {
                _state.value.copy(isLoading = true)
            }
            runCatching {
                repository.getNewsItems()
            }.onFailure { e ->
                _state.update {
                    _state.value.copy(
                        isLoading = false,
                        errorType = ErrorScreenState.NOTHING_FOUND
                    )
                }
            }.onSuccess { response ->
                response.collect { resource ->
                    when (resource) {
                        is Resource.Error -> handleError(resource)
                        is Resource.Success -> _state.update {
                            fullItemList = resource.data.orEmpty()
                            it.copy(
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

    private fun filterItemsByQuery(query: String) {
        updateTextInput(query)
        val filtered = if (query.isBlank()) {
            fullItemList
        } else {
            fullItemList.filter { item ->
                item.categories.any { category ->
                    category.contains(query, ignoreCase = true)
                }
            }
        }
        _state.update { it.copy(itemList = filtered) }
    }

    private fun updateTextInput(text: String) {
        _state.update { it.copy(textInput = text) }
    }

    private fun clearQuery() {
        _state.update {
            it.copy(
                itemList = fullItemList,
                textInput = ""
            )
        }
    }

    private fun handleError(error: Error<List<Item>>) {
        val items = error.data.orEmpty()
        val errorType = error.error?.mapToErrorScreen()

        _state.update {
            it.copy(
                isLoading = false,
                itemList = items,
                errorType = errorType,
                toastMessage = if (items.isNotEmpty()) {
                    getToastTextForErrorType(error.error)
                } else {
                    null
                }
            )
        }
    }

    private fun getToastTextForErrorType(errorType: ErrorType?): Int? {
        return when (errorType) {
            ErrorType.NO_CONNECTION -> R.string.error_no_internet_connection
            ErrorType.SERVER_ERROR -> R.string.error_something_went_wrong
            ErrorType.BAD_REQUEST -> R.string.error_nothing_found
            ErrorType.UNKNOWN_ERROR -> R.string.error_nothing_found
            else -> null
        }
    }
}