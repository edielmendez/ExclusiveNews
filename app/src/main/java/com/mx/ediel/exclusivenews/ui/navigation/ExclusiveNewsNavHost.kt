package com.mx.ediel.exclusivenews.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mx.ediel.exclusivenews.ui.screens.favorites.navigation.favoritesScreen
import com.mx.ediel.exclusivenews.ui.screens.favorites.navigation.navigateToFavorites
import com.mx.ediel.exclusivenews.ui.screens.home.navigation.homeScreen
import com.mx.ediel.exclusivenews.ui.screens.home.navigation.navigateToHome
import com.mx.ediel.exclusivenews.ui.screens.signin.navigation.navigateToSignIn
import com.mx.ediel.exclusivenews.ui.screens.signin.navigation.signInNavigationRoute
import com.mx.ediel.exclusivenews.ui.screens.signin.navigation.signInScreen

@Composable
fun ExclusiveNewsNavHost(
    startDestination: String = signInNavigationRoute
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
            onNewItemClick = {}
        )
        favoritesScreen(
            onRightIconClick = {}
        )
    }
}