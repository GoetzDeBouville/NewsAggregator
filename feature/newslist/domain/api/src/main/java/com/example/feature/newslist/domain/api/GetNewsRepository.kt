package com.example.feature.newslist.domain.api

import com.example.core.domain.models.Item
import com.example.core.domain.models.Resource
import kotlinx.coroutines.flow.Flow

interface GetNewsRepository {
    fun getNewsItems(): Flow<Resource<List<Item>>>
}