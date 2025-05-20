package com.example.core.domain.models

data class Channel(
    val title: String,
    val link: String,
    val description: String,
    val language: String,
    val copyright: String,
    val pubDate: String,
    val dcDate: String,
    val dcLanguage: String,
    val dcRights: String,
    val image: Image,
    val items: List<Item>
)