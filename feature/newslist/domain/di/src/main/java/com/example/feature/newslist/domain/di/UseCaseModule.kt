package com.example.feature.newslist.domain.di

import com.example.feature.newslist.domain.api.ExtractCategoriesUseCase
import com.example.feature.newslist.domain.impl.ExtractCategoriesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    abstract fun bindGetNewsRepository(
        impl: ExtractCategoriesUseCaseImpl
    ): ExtractCategoriesUseCase
}