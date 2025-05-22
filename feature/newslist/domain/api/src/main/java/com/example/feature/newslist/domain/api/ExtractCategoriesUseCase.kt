package com.example.feature.newslist.domain.api

import com.example.core.domain.models.Item
import com.example.core.domain.models.Resource
import kotlinx.coroutines.flow.Flow

typealias category = String

interface ExtractCategoriesUseCase {
    operator fun invoke(newsFlow: Flow<Resource<List<Item>>>): Flow<Set<category>>
}