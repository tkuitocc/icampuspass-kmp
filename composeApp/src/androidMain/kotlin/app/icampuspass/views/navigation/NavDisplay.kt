package app.icampuspass.views.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.dropUnlessResumed
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import app.icampuspass.views.navigation.navkeys.AboutScreenNavKey
import app.icampuspass.views.navigation.navkeys.MainScreenNavKey
import app.icampuspass.views.navigation.navkeys.SettingsScreenNavKey
import app.icampuspass.views.navigation.navkeys.UserPickerNavKey
import app.icampuspass.views.screens.AboutScreen
import app.icampuspass.views.screens.MainScreen
import app.icampuspass.views.screens.SettingsScreen
import app.icampuspass.views.screens.UserPickerScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun NavDisplay(
    modifier: Modifier = Modifier,
    backStack: NavBackStack<NavKey> = rememberNavBackStack(MainScreenNavKey),
    scope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
) {
    NavDisplay(
        backStack = backStack,
        modifier = modifier
            .fillMaxSize()
            .safeDrawingPadding(),
        entryProvider = entryProvider {
            entry<MainScreenNavKey> {
                MainScreen(
                    onMenuOpen = {
                        scope.launch {
                            drawerState.open()
                        }
                    },
                    onNavigateUserPicker = dropUnlessResumed {
                        scope.launch {
                            drawerState.close()
                        }

                        backStack.add(UserPickerNavKey)
                    }
                )
            }

            entry<AboutScreenNavKey> {
                AboutScreen(
                    onNavigateBack = dropUnlessResumed {
                        while (backStack.lastOrNull() != MainScreenNavKey) {
                            backStack.removeLastOrNull()
                        }
                    }
                )
            }

            entry<SettingsScreenNavKey> {
                SettingsScreen(
                    onMenuOpen = {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                )
            }

            entry<UserPickerNavKey>(
                metadata = NavDisplay.transitionSpec {
                    slideInHorizontally(
                        animationSpec = tween(durationMillis = 400),
                        initialOffsetX = { it }
                    ) togetherWith ExitTransition.KeepUntilTransitionsFinished
                } + NavDisplay.popTransitionSpec {
                    EnterTransition.None togetherWith slideOutHorizontally(
                        animationSpec = tween(durationMillis = 400),
                        targetOffsetX = { it }
                    )
                } + NavDisplay.predictivePopTransitionSpec {
                    EnterTransition.None togetherWith slideOutHorizontally(
                        animationSpec = tween(durationMillis = 400),
                        targetOffsetX = { it }
                    )
                }
            ) {
                UserPickerScreen(
                    onNavigateBack = dropUnlessResumed {
                        scope.launch {
                            while (backStack.lastOrNull() != MainScreenNavKey) {
                                backStack.removeLastOrNull()
                            }
                        }
                    }
                )
            }
        }
    )
}
