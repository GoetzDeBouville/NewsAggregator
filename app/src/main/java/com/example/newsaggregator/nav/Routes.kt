package com.example.newsaggregator.nav

sealed class Routes(val route: String) {
    data object NewsListScreen : Routes("newslist")
    data object ArticleScreen : Routes("article/{guide}") {
        fun createRoute(guide: String): String = "article/$guide"
    }
}
