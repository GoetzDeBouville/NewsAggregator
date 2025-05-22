package com.example.core.domain.models

data class Item(
    val title: String,
    val link: String,
    val description: String,
    val categories: List<String>,
    val pubDate: String,
    val guid: String,
    val imageUrl: String?
)