package app.icampuspass.android.app

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation3.runtime.rememberNavBackStack
import app.icampuspass.android.app.views.components.ModalDrawerSheet
import app.icampuspass.android.app.views.navigation.NavDisplay
import app.icampuspass.android.app.views.navigation.navkeys.AboutScreenNavKey
import app.icampuspass.android.app.views.navigation.navkeys.ClassScheduleScreenNavKey
import app.icampuspass.android.app.views.navigation.navkeys.MainScreenNavKey
import app.icampuspass.android.app.views.navigation.navkeys.SettingsScreenNavKey
import app.icampuspass.android.shared.views.theme.Theme
import kotlinx.coroutines.launch

@Composable
fun App() {
    Theme {
        Surface(color = Color.Black) {
            Surface(modifier = Modifier.safeDrawingPadding()) {
                val scope = rememberCoroutineScope()

                val backStack = rememberNavBackStack(MainScreenNavKey)

                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

                ModalNavigationDrawer(
                    drawerContent = {
                        ModalDrawerSheet(
                            backStack = backStack,
                            onMenuClose = {
                                scope.launch {
                                    drawerState.close()
                                }
                            },
                            onNavigateMain = {
                                scope.launch {
                                    drawerState.close()
                                }

                                while (backStack.lastOrNull() != MainScreenNavKey) {
                                    backStack.removeLastOrNull()
                                }
                            },
                            onNavigateClassSchedule = {
                                scope.launch {
                                    drawerState.close()
                                }

                                while (backStack.lastOrNull() != MainScreenNavKey) {
                                    backStack.removeLastOrNull()
                                }

                                if (!backStack.contains(element = ClassScheduleScreenNavKey)) {
                                    backStack.add(element = ClassScheduleScreenNavKey)
                                }
                            },
                            onNavigateSettings = {
                                scope.launch {
                                    drawerState.close()
                                }

                                while (backStack.lastOrNull() != MainScreenNavKey) {
                                    backStack.removeLastOrNull()
                                }

                                if (!backStack.contains(element = SettingsScreenNavKey)) {
                                    backStack.add(element = SettingsScreenNavKey)
                                }
                            },
                            onNavigateAbout = {
                                scope.launch {
                                    drawerState.close()
                                }

                                while (backStack.lastOrNull() != MainScreenNavKey) {
                                    backStack.removeLastOrNull()
                                }

                                if (!backStack.contains(element = AboutScreenNavKey)) {
                                    backStack.add(element = AboutScreenNavKey)
                                }
                            }
                        )
                    },
                    drawerState = drawerState,
                    gesturesEnabled = (
                            backStack.lastOrNull() == MainScreenNavKey ||
                            backStack.lastOrNull() == ClassScheduleScreenNavKey
                    ),
                    content = {
                        NavDisplay(
                            backStack = backStack,
                            scope = scope,
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
