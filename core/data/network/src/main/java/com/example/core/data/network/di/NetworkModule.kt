package com.example.core.data.network.di

import com.example.core.data.network.api.NewsApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nl.adaptivity.xmlutil.XmlDeclMode
import nl.adaptivity.xmlutil.core.XmlVersion
import nl.adaptivity.xmlutil.serialization.XML
import okhttp3.MediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val xmlContentType = MediaType.get("application/xml; charset=UTF8")

    @Provides
    @Singleton
    fun provideXmlSerializer(): XML = XML {
        xmlVersion = XmlVersion.XML10
        xmlDeclMode = XmlDeclMode.Auto
        indentString = "  "
    }

    @Provides
    @Singleton
    fun provideRetrofit(xml: XML): Retrofit = Retrofit.Builder()
        .baseUrl("https://www.theguardian.com/")
        .addConverterFactory(xml.asConverterFactory(xmlContentType))
        .build()

    @Provides
    @Singleton
    fun provideNewsApi(retrofit: Retrofit): NewsApi = retrofit.create(NewsApi::class.java)
}