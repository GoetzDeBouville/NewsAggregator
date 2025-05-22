package com.example.core.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.core.data.db.entitiy.ItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsItemDao {
    @Upsert
    fun insertItems(items: List<ItemEntity>)

    @Query("SELECT * FROM news_items ORDER BY pubDateTimestamp DESC LIMIT 200")
    fun getAllNewsItem(): Flow<List<ItemEntity>>
}