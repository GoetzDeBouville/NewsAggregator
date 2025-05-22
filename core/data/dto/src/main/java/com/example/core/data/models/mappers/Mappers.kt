package com.example.core.data.models.mappers

import com.example.core.data.models.CreditDto
import com.example.core.data.models.ImageDto
import com.example.core.data.models.ItemDto
import com.example.core.domain.models.Credit
import com.example.core.domain.models.Image
import com.example.core.domain.models.Item

fun CreditDto.toDomain() = Credit(
    scheme = scheme,
    value = value ?: ""
)

fun ImageDto.toDomain() = Image(
    title = title ?: "",
    url = url ?: "",
    link = link ?: ""
)

fun ItemDto.toDomain() = Item(
    title = title ?: "",
    link = link ?: "",
    description = description ?: "",
    categories = categories.mapNotNull {
        it.value
    },
    pubDate = pubDate ?: "",
    guid = guid ?: "",
    imageUrl = contents.getOrNull(1)?.url
)
