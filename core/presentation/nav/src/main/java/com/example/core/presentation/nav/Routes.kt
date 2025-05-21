package com.example.core.presentation.nav

import android.net.Uri

sealed class Routes(val route: String) {
    data object NewsListScreen : Routes("newslist")
    data object ArticleScreen : Routes("article/{guide}") {
        fun createRoute(guide: String): String = "article/${Uri.encode(guide)}"
    }
}