package com.example.core.data.network.api

import com.example.core.data.models.RssDto
import retrofit2.http.GET

interface NewsApi {
    @GET("international/rss")
    suspend fun fetchRss(): RssDto
}