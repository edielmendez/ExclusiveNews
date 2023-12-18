package com.mx.ediel.exclusivenews.ui.screens.detail.navigation

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mx.ediel.exclusivenews.ui.screens.detail.NewDetailScreen
import com.mx.ediel.exclusivenews.ui.screens.favorites.FavoritesScreen


const val NEWS_IMAGE = "newsImage"


const val newDetailNavigationRoute = "new_detail_route"
fun NavController.navigateToDetail(id: Int, navOptions: NavOptions? = null) {
    //Log.v("NavController", "image -> $image - $newDetailNavigationRoute/$image")
    this.navigate("$newDetailNavigationRoute/$id", navOptions)
}

fun NavGraphBuilder.detailScreen(
    onRightIconClick: () -> Unit
) {
    composable(
        route = "$newDetailNavigationRoute/{$NEWS_IMAGE}",
        arguments = listOf(
            navArgument(NEWS_IMAGE) { type = NavType.StringType },
        ),
    ) { backStackEntry ->
        val newsImage = backStackEntry.arguments?.getString(NEWS_IMAGE) ?: ""
        Log.v("NavController", "backStackEntry -> $newsImage")
        NewDetailScreen(
            newsId = "",
            newsTitle = "",
            newsAuthor = "",
            newsImage = newsImage,
            onBack = onRightIconClick,

        )
    }
}