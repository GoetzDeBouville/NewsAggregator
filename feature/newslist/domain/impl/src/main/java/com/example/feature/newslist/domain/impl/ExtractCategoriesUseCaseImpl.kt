package com.example.feature.newslist.domain.impl

import com.example.core.domain.models.Item
import com.example.core.domain.models.Resource
import com.example.feature.newslist.domain.api.ExtractCategoriesUseCase
import com.example.feature.newslist.domain.api.category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ExtractCategoriesUseCaseImpl @Inject constructor() : ExtractCategoriesUseCase {
    override operator fun invoke(newsFlow: Flow<Resource<List<Item>>>): Flow<Set<category>> {
        return newsFlow
            .filterIsInstance<Resource.Success<List<Item>>>()
            .map { items ->
                items.data?.flatMap {
                    it.categories
                }
                    ?.toSet() ?: emptySet()
            }
    }
}