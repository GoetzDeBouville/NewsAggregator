package com.example.core.data.di

import android.content.Context
import androidx.room.Room
import com.example.core.data.db.AppDatabase
import com.example.core.data.db.dao.NewsItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val DATABASE_NAME = "app_database"

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideNewsItemDao(db: AppDatabase): NewsItemDao {
        return db.newsItemDao()
    }
}
