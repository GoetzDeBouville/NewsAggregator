package com.example.newsaggregator.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.feature.newslist.presentation.NewsScreen
import com.example.presentation.ArticleScreen


@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.NewsListScreen.route) {
        composable(Routes.NewsListScreen.route) {
            NewsScreen(navController = navController)
        }

        composable(
            route = Routes.ArticleScreen.route,
            arguments = listOf(navArgument("guide") { type = NavType.StringType })
        ) { backStackEntry ->
            val guide = backStackEntry.arguments?.getString("guide") ?: return@composable
            ArticleScreen(navController = navController, link = guide)
        }
    }
}