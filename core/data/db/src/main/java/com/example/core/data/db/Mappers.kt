package com.example.core.data.db

import android.util.Log
import com.example.core.data.db.entitiy.ItemEntity
import com.example.core.data.models.CategoryDto
import com.example.core.data.models.ItemDto
import com.example.core.domain.models.Item
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.text.SimpleDateFormat
import java.util.Locale

fun ItemDto.mapToEntity(): ItemEntity =
    ItemEntity(
        title = title ?: "",
        link = link ?: "",
        description = description ?: "",
        categories = categories.encodeToString(),
        pubDate = pubDate ?: "",
        pubDateTimestamp = parsePubDateToTimestamp(pubDate ?: ""),
        guid = guid ?: "",
        imageUrl = contents.getOrNull(1)?.url
    )

fun ItemEntity.toDomain(): Item =
    Item(
        title = title,
        link = link,
        description = description,
        categories = categories.decodeToListString(),
        pubDate = pubDate,
        guid = guid,
        imageUrl = imageUrl
    )

private fun String.decodeToListString(): List<String> {
    return try {
        Json.decodeFromString(this)
    } catch (e: SerializationException) {
        Log.e("decodeToListString", e.message.toString())
        emptyList()
    } catch (e: IllegalArgumentException) {
        Log.e("decodeToListString", e.message.toString())
        emptyList()
    }
}

private fun List<CategoryDto>.encodeToString(): String {
    return try {
        Json.encodeToString(
            this.mapNotNull { it.value }
        )
    } catch (e: SerializationException) {
        Log.e("decodeToListString", e.message.toString())
        ""
    }
}

private fun parsePubDateToTimestamp(pubDate: String): Long {
    return try {
        val formatter = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH)
        formatter.parse(pubDate)?.time ?: 0L
    } catch (e: Exception) {
        0L
    }
}