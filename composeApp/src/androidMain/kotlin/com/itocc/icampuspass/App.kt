package com.itocc.icampuspass

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.itocc.icampuspass.composables.ModalDrawerSheet
import com.itocc.icampuspass.destinations.AboutScreenDestination
import com.itocc.icampuspass.destinations.MainScreenDestination
import com.itocc.icampuspass.destinations.SettingsScreenDestination
import com.itocc.icampuspass.destinations.UserDialogDestination
import com.itocc.icampuspass.dialogs.UserDialog
import com.itocc.icampuspass.screens.AboutScreen
import com.itocc.icampuspass.screens.MainScreen
import com.itocc.icampuspass.screens.SettingsScreen

@Composable
fun App() {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) {
            darkColorScheme()
        } else {
            lightColorScheme()
        }
    ) {
        Surface(
            color = Color.Black
        ) {
            Surface(
                modifier = Modifier.safeDrawingPadding()
            ) {
                val scope: CoroutineScope = rememberCoroutineScope()

                val navController: NavHostController = rememberNavController()

                val drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

                ModalNavigationDrawer(
                    drawerContent = {
                        ModalDrawerSheet(
                            navController = navController,
                            onMenuClose = {
                                scope.launch {
                                    drawerState.close()
                                }
                            },
                            onNavigateMain = {
                                scope.launch {
                                    drawerState.close()
                                }

                                scope.launch {
                                    navController.navigate(route = MainScreenDestination) {
                                        popUpTo(route = MainScreenDestination)

                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                            onNavigateSettings = {
                                scope.launch {
                                    drawerState.close()
                                }

                                scope.launch {
                                    navController.navigate(SettingsScreenDestination) {
                                        popUpTo(route = MainScreenDestination)

                                        launchSingleTop = true
                                    }
                                }
                            },
                            onNavigateAbout = {
                                scope.launch {
                                    drawerState.close()
                                }

                                scope.launch {
                                    navController.navigate(AboutScreenDestination) {
                                        popUpTo(route = MainScreenDestination)

                                        launchSingleTop = true
                                    }
                                }
                            }
                        )
                    },
                    drawerState = drawerState,
                    gesturesEnabled = drawerState.isOpen,
                    content = {
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
                                    onOpenUserDialog = {
                                        scope.launch {
                                            drawerState.close()
                                        }

                                        scope.launch {
                                            navController.navigate(route = UserDialogDestination) {
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

                            dialog<UserDialogDestination> {
                                UserDialog(
                                    onCloseUserDialog = {
                                        scope.launch {
                                            navController.popBackStack()
                                        }
                                    }
                                )
                            }
                        }
                    }
                )

                BackHandler(enabled = drawerState.isOpen) {
                    scope.launch {
                        drawerState.close()
                    }
                }
            }
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Preview(
    showSystemUi = true,
    backgroundColor = 0xFF000000,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun AppPreview() {
    App()
}
