package com.example.core.data.di

import com.example.core.data.network.api.NetworkClient
import com.example.core.data.network.impl.RetrofitNetworkClient
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkBindingsModule {

    @Binds
    @Singleton
    abstract fun bindNetworkClient(
        impl: RetrofitNetworkClient
    ): NetworkClient
}
