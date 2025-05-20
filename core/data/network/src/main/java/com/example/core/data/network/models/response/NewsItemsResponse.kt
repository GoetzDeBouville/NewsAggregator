package com.example.core.data.network.models.response

import com.example.core.data.models.ItemDto
import com.example.core.data.network.models.Response

data class NewsItemsResponse(
    val newsItems: List<ItemDto>
) : Response()