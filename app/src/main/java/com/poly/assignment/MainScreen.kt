package com.poly.assignment

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseUser

@Composable
fun MainScreen(currentUser: FirebaseUser?) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = if (currentUser != null) "home" else "welcome",
        enterTransition = {
            fadeIn(animationSpec = tween(1000)) + slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Up,
                tween(1000)
            )
        },
        popEnterTransition = {
            fadeIn(animationSpec = tween(1000)) + slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Down,
                tween(1000)
            )
        },
        exitTransition = {
            fadeOut(animationSpec = tween(1000)) + slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Down,
                tween(1000)
            )
        },
        popExitTransition = {
            fadeOut(animationSpec = tween(1000)) + slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Up,
                tween(1000)
            )
        }
    ) {
        composable("welcome") { WelcomeScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("signup") { SignupScreen(navController) }
        composable("home") { HomeScreen(navController) }
    }
}
