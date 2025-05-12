package app.icampuspass

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.tooling.preview.Wallpapers.BLUE_DOMINATED_EXAMPLE
import androidx.compose.ui.tooling.preview.Wallpapers.GREEN_DOMINATED_EXAMPLE
import androidx.compose.ui.tooling.preview.Wallpapers.RED_DOMINATED_EXAMPLE
import androidx.compose.ui.tooling.preview.Wallpapers.YELLOW_DOMINATED_EXAMPLE
import androidx.lifecycle.compose.dropUnlessResumed
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.icampuspass.views.composables.ModalDrawerSheet
import app.icampuspass.views.navigation.NavHost
import app.icampuspass.views.navigation.destinations.AboutScreenDestination
import app.icampuspass.views.navigation.destinations.MainScreenDestination
import app.icampuspass.views.navigation.destinations.SettingsScreenDestination
import app.icampuspass.views.theme.Theme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun App() {
    Theme {
        Surface(color = Color.Black) {
            Surface(modifier = Modifier.safeDrawingPadding()) {
                val scope: CoroutineScope = rememberCoroutineScope()

                val navController: NavHostController = rememberNavController()

                val drawerState: DrawerState =
                    rememberDrawerState(initialValue = DrawerValue.Closed)

                ModalNavigationDrawer(
                    drawerContent = {
                        ModalDrawerSheet(
                            navController = navController,
                            onMenuClose = {
                                scope.launch {
                                    drawerState.close()
                                }
                            },
                            onNavigateMain = dropUnlessResumed {
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
                            onNavigateSettings = dropUnlessResumed {
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
                            onNavigateAbout = dropUnlessResumed {
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

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL)
@PreviewDynamicColors
@Preview(
    name = "Dark red",
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
    wallpaper = RED_DOMINATED_EXAMPLE
)
@Preview(
    name = "Dark blue",
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
    wallpaper = BLUE_DOMINATED_EXAMPLE
)
@Preview(
    name = "Dark green",
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
    wallpaper = GREEN_DOMINATED_EXAMPLE
)
@Preview(
    name = "Dark yellow",
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
    wallpaper = YELLOW_DOMINATED_EXAMPLE
)
@PreviewScreenSizes
@Composable
fun AppPreview() {
    App()
}
