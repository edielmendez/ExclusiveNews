package com.mx.ediel.exclusivenews.ui.screens.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mx.ediel.exclusivenews.ui.model.News
import com.mx.ediel.exclusivenews.ui.screens.home.HomeScreen
import com.mx.ediel.exclusivenews.ui.screens.signin.SignInScreen

const val homeNavigationRoute = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(homeNavigationRoute, navOptions)
}

fun NavGraphBuilder.homeScreen(
    onFavoritesButtonClick: () -> Unit,
    onNewItemClick: (News) -> Unit
) {
    composable(
        route = homeNavigationRoute,
    ) {
        HomeScreen(
            onFavoritesButtonClick = onFavoritesButtonClick,
            onNewItemClick = onNewItemClick
        )
    }
}