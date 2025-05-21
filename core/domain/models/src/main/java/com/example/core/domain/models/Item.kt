package com.example.core.domain.models

data class Item(
    val title: String,
    val link: String,
    val description: String,
    val categories: List<Category>,
    val pubDate: String,
    val guid: String,
    val contents: List<Content>
)