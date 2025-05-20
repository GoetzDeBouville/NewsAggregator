package com.example.core.data.network.api

import com.example.core.data.network.models.Response

interface NetworkClient {
    suspend fun doRequest(dto: Any): Response
}
