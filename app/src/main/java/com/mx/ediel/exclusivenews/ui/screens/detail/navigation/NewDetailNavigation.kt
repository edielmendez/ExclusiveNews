package com.mx.ediel.exclusivenews.ui.screens.detail.navigation

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.mx.ediel.exclusivenews.ui.model.News
import com.mx.ediel.exclusivenews.ui.screens.detail.NewDetailScreen
import com.mx.ediel.exclusivenews.ui.screens.favorites.FavoritesScreen
import java.util.Base64


const val NEWS_TITLE = "newsTitle"
const val NEWS_AUTHOR = "newsAuthor"
const val NEWS_DESCRIPTION = "newsDescription"
const val NEWS_CREATED_AT = "newsCreatedAt"


const val newDetailNavigationRoute = "new_detail_route"
fun NavController.navigateToDetail(news: News, navOptions: NavOptions? = null) {
    this.navigate("$newDetailNavigationRoute/${news.title}/${news.author}/${news.description}/${news.createdAt}", navOptions)
}

fun NavGraphBuilder.detailScreen(
    onRightIconClick: () -> Unit
) {
    composable(
        route = "$newDetailNavigationRoute/{$NEWS_TITLE}/{$NEWS_AUTHOR}/{$NEWS_DESCRIPTION}/{$NEWS_CREATED_AT}",
        arguments = listOf(
            navArgument(NEWS_TITLE) { type = NavType.StringType },
        ),
    ) { backStackEntry ->
        val title = backStackEntry.arguments?.getString(NEWS_TITLE) ?: ""
        val author = backStackEntry.arguments?.getString(NEWS_AUTHOR) ?: ""
        val description = backStackEntry.arguments?.getString(NEWS_DESCRIPTION) ?: ""
        val createdAt = backStackEntry.arguments?.getString(NEWS_CREATED_AT) ?: ""
        NewDetailScreen(
            title = title,
            author = author,
            description = description,
            createdAt = createdAt,
            onBack = onRightIconClick,
        )
    }
}