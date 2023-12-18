package com.mx.ediel.exclusivenews.ui.screens.favorites.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mx.ediel.exclusivenews.ui.screens.favorites.FavoritesScreen


const val favoritesNavigationRoute = "favorites_route"
fun NavController.navigateToFavorites(navOptions: NavOptions? = null) {
    this.navigate(favoritesNavigationRoute, navOptions)
}

fun NavGraphBuilder.favoritesScreen(
    onLeftIconClick: () -> Unit
) {
    composable(
        route = favoritesNavigationRoute,
    ) {
        FavoritesScreen(onLeftIconClick)
    }
}