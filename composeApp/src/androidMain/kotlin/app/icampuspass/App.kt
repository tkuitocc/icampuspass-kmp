package app.icampuspass

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.safeContentPadding
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
import androidx.navigation.compose.rememberNavController
import app.icampuspass.views.composables.ModalDrawerSheet
import app.icampuspass.views.navigation.destinations.AboutScreenDestination
import app.icampuspass.views.navigation.destinations.MainScreenDestination
import app.icampuspass.views.navigation.destinations.SettingsScreenDestination
import app.icampuspass.views.navigation.NavHost
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun App() {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) {
            darkColorScheme()
        } else {
            lightColorScheme()
        }
    ) {
        Surface(color = Color.Black) {
            Surface(modifier = Modifier.safeContentPadding()) {
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
                                    navController.navigate(route = SettingsScreenDestination) {
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
                                    navController.navigate(route = AboutScreenDestination) {
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
                            scope = scope,
                            navController = navController,
                            drawerState = drawerState
                        )
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
