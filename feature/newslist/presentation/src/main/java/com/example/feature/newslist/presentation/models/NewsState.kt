package com.example.feature.newslist.presentation.models

sealed interface NewsState {
    data object Loading : NewsState
}