package com.example.feature.newslist.presentation.models

internal sealed interface Event {
    data class SearchTextChanged(val query: String) : Event
    data object ClearSearch : Event
    data object ClearToast : Event
    data object Refresh : Event
}