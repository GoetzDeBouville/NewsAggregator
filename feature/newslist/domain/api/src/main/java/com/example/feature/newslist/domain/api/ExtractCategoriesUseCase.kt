package com.example.feature.newslist.domain.api

import com.example.core.domain.models.Category
import com.example.core.domain.models.Item
import com.example.core.domain.models.Resource
import kotlinx.coroutines.flow.Flow

interface ExtractCategoriesUseCase {
    operator fun invoke(newsFlow: Flow<Resource<List<Item>>>): Flow<Set<Category>>
}