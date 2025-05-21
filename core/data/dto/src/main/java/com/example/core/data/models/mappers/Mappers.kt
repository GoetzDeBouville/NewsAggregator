package com.example.core.data.models.mappers

import com.example.core.data.models.CategoryDto
import com.example.core.data.models.ChannelDto
import com.example.core.data.models.ContentDto
import com.example.core.data.models.CreditDto
import com.example.core.data.models.ImageDto
import com.example.core.data.models.ItemDto
import com.example.core.data.models.RssDto
import com.example.core.domain.models.Category
import com.example.core.domain.models.Channel
import com.example.core.domain.models.Content
import com.example.core.domain.models.Credit
import com.example.core.domain.models.Image
import com.example.core.domain.models.Item
import com.example.core.domain.models.Rss

fun CategoryDto.toDomain() = Category(
    domain = domain ?: "",
    value = value ?: ""
)

fun CreditDto.toDomain() = Credit(
    scheme = scheme,
    value = value ?: ""
)

fun ContentDto.toDomain() = Content(
    type = type,
    width = width,
    url = url,
    credit = credit?.toDomain()
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
    categories = categories.map { it.toDomain() },
    pubDate = pubDate ?: "",
    guid = guid ?: "",
    contents = contents.map { it.toDomain() }
)

fun ChannelDto.toDomain() = Channel(
    title = title ?: "",
    link = link ?: "",
    description = description ?: "",
    copyright = copyright ?: "",
    pubDate = pubDate ?: "",
    image = image?.toDomain() ?: Image("", "", ""),
    items = items.map { it.toDomain() }
)

fun RssDto.toDomain() = Rss(
    version = version ?: "",
    channel = channel.toDomain()
)