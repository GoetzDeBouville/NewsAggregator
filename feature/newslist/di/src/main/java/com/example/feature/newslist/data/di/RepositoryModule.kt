package com.example.feature.newslist.data.di

import com.example.feature.newslist.data.GetNewsRepositoryImpl
import com.example.feature.newslist.domain.api.GetNewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindGetNewsRepository(
        impl: GetNewsRepositoryImpl
    ): GetNewsRepository
}