package com.example.feature.newslist.presentation.models

import com.example.core.domain.models.Category
import com.example.core.domain.models.Item
import com.newsapp.uikit.error.ErrorScreenState

internal data class State(
    val itemList: List<Item> = emptyList(),
    val isLoading: Boolean = false,
    val errorType: ErrorScreenState? = null,
    val categories: Set<Category> = emptySet()
)