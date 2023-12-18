package com.mx.ediel.exclusivenews.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mx.ediel.exclusivenews.ui.screens.detail.navigation.detailScreen
import com.mx.ediel.exclusivenews.ui.screens.detail.navigation.navigateToDetail
import com.mx.ediel.exclusivenews.ui.screens.favorites.navigation.favoritesNavigationRoute
import com.mx.ediel.exclusivenews.ui.screens.favorites.navigation.favoritesScreen
import com.mx.ediel.exclusivenews.ui.screens.favorites.navigation.navigateToFavorites
import com.mx.ediel.exclusivenews.ui.screens.home.navigation.homeNavigationRoute
import com.mx.ediel.exclusivenews.ui.screens.home.navigation.homeScreen
import com.mx.ediel.exclusivenews.ui.screens.home.navigation.navigateToHome
import com.mx.ediel.exclusivenews.ui.screens.signin.navigation.signInNavigationRoute
import com.mx.ediel.exclusivenews.ui.screens.signin.navigation.signInScreen

@Composable
fun ExclusiveNewsNavHost(
    startDestination: String = homeNavigationRoute
){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        signInScreen(
            onSignInButtonClick = navController::navigateToHome
        )
        homeScreen(
            onFavoritesButtonClick = navController::navigateToFavorites,
            onNewItemClick = {
                navController.navigateToDetail(it)
            }
        )
        favoritesScreen(
            onLeftIconClick = navController::popBackStack,
            onNewItemClick = {
                //navController.navigateToDetail(it)
            }
        )
        detailScreen(
            onRightIconClick = navController::popBackStack
        )
    }
}