package com.poly.assignment

import ProductScreen
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseUser
import java.lang.reflect.Modifier

@Composable
fun MainScreen(currentUser: FirebaseUser?) {
    val navController = rememberNavController()

    // Get the current route from the navController
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomNavItems = listOf(
        BottomNavItem(
            name = "Home",
            route = "home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        BottomNavItem(
            name = "Saved",
            route = "saved",
            selectedIcon = Icons.Filled.Bookmark,
            unselectedIcon = Icons.Outlined.BookmarkBorder
        ),
        BottomNavItem(
            name = "Notification",
            route = "noti",
            selectedIcon = Icons.Filled.Notifications,
            unselectedIcon = Icons.Outlined.Notifications
        ),
        BottomNavItem(
            name = "Profile",
            route = "profile",
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person
        )
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                bottomNavItems.forEach { bottomNavItem ->
                    NavigationBarItem(
                        selected = bottomNavItem.route == currentRoute,
                        onClick = {
                            navController.navigate(bottomNavItem.route) {
                                // Clear the back stack to avoid re-adding the same route
                                launchSingleTop = true
                                // Pop up to the start destination to clear the back stack
                                popUpTo = navController.graph.startDestinationId
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (bottomNavItem.route == currentRoute)
                                    bottomNavItem.selectedIcon else bottomNavItem.unselectedIcon,
                                contentDescription = bottomNavItem.name
                            )
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
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
            composable("saved") { SavedScreen(navController) }
            composable("noti") { NotificationsScreen(navController) }
            composable("profile") { ProfileScreen(navController) }
            composable("product/{productId}") { backStackEntry ->
                val productId = backStackEntry.arguments?.getString("productId")?.toInt() ?: 0
                val product = remember { mutableStateOf<Product?>(null) }
                LaunchedEffect(productId) {
                    product.value = ProductRepository.getProductById(productId)
                }
                product.value?.let {
                    ProductScreen(product = it)
                } ?: run {
                    // Handle loading or error state
                    Text("Loading...")
                }
            }
        }
    }
}

data class BottomNavItem(
    val name: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)
