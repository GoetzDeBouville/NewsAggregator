package com.example.feature.newslist.presentation.models

internal sealed interface Intent {
    data class SearchTextChanged(val query: String) : Intent
    data object ClearSearch : Intent
}