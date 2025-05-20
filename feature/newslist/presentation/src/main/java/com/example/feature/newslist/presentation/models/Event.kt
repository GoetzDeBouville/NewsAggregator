package com.example.feature.newslist.presentation.models

sealed interface Event {
    class SearchByTags(val query: String) : Event
}