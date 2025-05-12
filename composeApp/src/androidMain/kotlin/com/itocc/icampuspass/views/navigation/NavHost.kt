package com.itocc.icampuspass.views.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.itocc.icampuspass.views.navigation.destinations.AboutScreenDestination
import com.itocc.icampuspass.views.navigation.destinations.MainScreenDestination
import com.itocc.icampuspass.views.navigation.destinations.SettingsScreenDestination
import com.itocc.icampuspass.views.navigation.destinations.UserPickerDestination
import com.itocc.icampuspass.views.screens.AboutScreen
import com.itocc.icampuspass.views.screens.MainScreen
import com.itocc.icampuspass.views.screens.SettingsScreen
import com.itocc.icampuspass.views.screens.UserPickerScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun NavHost(
    scope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
) {
    NavHost(
        navController = navController,
        startDestination = MainScreenDestination,
        modifier = Modifier.fillMaxSize().safeDrawingPadding()
    ) {
        composable<MainScreenDestination> {
            MainScreen(
                onMenuOpen = {
                    scope.launch {
                        drawerState.open()
                    }
                },
                onNavigateUserPicker = {
                    scope.launch {
                        drawerState.close()
                    }

                    scope.launch {
                        navController.navigate(route = UserPickerDestination) {
                            popUpTo(route = MainScreenDestination)

                            launchSingleTop = true
                        }
                    }
                }
            )
        }

        composable<AboutScreenDestination> {
            AboutScreen(
                onNavigateBack = {
                    scope.launch {
                        navController.navigate(route = MainScreenDestination) {
                            popUpTo(route = MainScreenDestination)

                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }

        composable<SettingsScreenDestination> {
            SettingsScreen(
                onMenuOpen = {
                    scope.launch {
                        drawerState.open()
                    }
                }
            )
        }

        composable<UserPickerDestination>(
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(durationMillis = 400)) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(durationMillis = 400)) },
            popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(durationMillis = 400)) },
            popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(durationMillis = 400)) }
        ) {
            UserPickerScreen(
                onNavigateBack = {
                    scope.launch {
                        navController.navigate(route = MainScreenDestination) {
                            popUpTo(route = MainScreenDestination)

                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}
