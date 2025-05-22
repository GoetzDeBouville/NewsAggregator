package com.example.core.data.db.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_items")
data class ItemEntity(
    @PrimaryKey
    @ColumnInfo(name = "guid")
    val guid: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "link")
    val link: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "categories")
    val categories: String,
    @ColumnInfo(name = "pubDate")
    val pubDate: String,
    @ColumnInfo(name = "pubDateTimestamp")
    val pubDateTimestamp: Long,
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String?
)