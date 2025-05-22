package com.example.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.db.dao.NewsItemDao
import com.example.core.data.db.entitiy.ItemEntity

@Database(
    entities = [
        ItemEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsItemDao(): NewsItemDao
}