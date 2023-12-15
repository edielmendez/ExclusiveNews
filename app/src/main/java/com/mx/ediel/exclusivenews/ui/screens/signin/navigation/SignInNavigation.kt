package com.mx.ediel.exclusivenews.ui.screens.signin.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mx.ediel.exclusivenews.ui.screens.signin.SignInScreen

const val signInNavigationRoute = "sign_in_route"

fun NavController.navigateSignIn(navOptions: NavOptions? = null) {
    this.navigate(signInNavigationRoute, navOptions)
}

fun NavGraphBuilder.signInScreen(onSignInButtonClick: (String) -> Unit) {
    composable(
        route = signInNavigationRoute,
    ) {
        SignInScreen()
    }
}