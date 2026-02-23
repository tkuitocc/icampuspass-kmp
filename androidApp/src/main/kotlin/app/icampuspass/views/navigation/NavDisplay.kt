package app.icampuspass.views.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseInCirc
import androidx.compose.animation.core.EaseOutCirc
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import app.icampuspass.views.navigation.navkeys.AboutScreenNavKey
import app.icampuspass.views.navigation.navkeys.AccountLogInScreenNavKey
import app.icampuspass.views.navigation.navkeys.AccountLogOutScreenNavKey
import app.icampuspass.views.navigation.navkeys.ClassScheduleScreenNavKey
import app.icampuspass.views.navigation.navkeys.MainScreenNavKey
import app.icampuspass.views.navigation.navkeys.SettingsScreenNavKey
import app.icampuspass.views.navigation.navkeys.UserPickerNavKey
import app.icampuspass.views.screens.AboutScreen
import app.icampuspass.views.screens.AccountLogInScreen
import app.icampuspass.views.screens.ClassScheduleScreen
import app.icampuspass.views.screens.AccountLogOutScreen
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
        transitionSpec = {
            fadeIn(
                animationSpec = tween(durationMillis = 1000)
            ) togetherWith fadeOut(
                animationSpec = tween(durationMillis = 1000)
            )
        },
        popTransitionSpec = {
            fadeIn(
                animationSpec = tween(durationMillis = 1000)
            ) togetherWith fadeOut(
                animationSpec = tween(durationMillis = 1000)
            )
        },
        predictivePopTransitionSpec = {
            EnterTransition.None togetherWith ExitTransition.None
        },
        entryProvider = entryProvider {
            entry<AccountLogInScreenNavKey> {
                AccountLogInScreen(
                    onNavigateBack = {
                        scope.launch {
                            if (backStack.contains(element = AccountLogInScreenNavKey)) {
                                backStack.removeLastOrNull()
                            }
                        }
                    },
                )
            }

            entry<AccountLogOutScreenNavKey> {
                AccountLogOutScreen(
                    onNavigateBack = {
                        scope.launch {
                            if (backStack.contains(element = AccountLogOutScreenNavKey)) {
                                backStack.removeLastOrNull()
                            }
                        }
                    }
                )
            }

            entry<AboutScreenNavKey> {
                AboutScreen(
                    onNavigateBack = {
                        while (backStack.contains(element = AboutScreenNavKey)) {
                            backStack.removeLastOrNull()
                        }
                    }
                )
            }

            entry<ClassScheduleScreenNavKey> {
                ClassScheduleScreen(
                    onMenuOpen = {
                        scope.launch {
                            drawerState.open()
                        }
                    },
                    onNavigateUserPicker = {
                        scope.launch {
                            drawerState.close()
                        }

                        if (!backStack.contains(element = UserPickerNavKey)) {
                            backStack.add(element = UserPickerNavKey)
                        }
                    }
                )
            }

            entry<MainScreenNavKey> {
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

                        if (!backStack.contains(element = UserPickerNavKey)) {
                            backStack.add(element = UserPickerNavKey)
                        }
                    }
                )
            }

            entry<SettingsScreenNavKey> {
                SettingsScreen(
                    onNavigateBack = {
                        while (backStack.contains(element = SettingsScreenNavKey)) {
                            backStack.removeLastOrNull()
                        }
                    }
                )
            }

            entry<UserPickerNavKey>(
                metadata = NavDisplay.transitionSpec {
                    slideInHorizontally(
                        animationSpec = tween(easing = EaseOutCirc),
                        initialOffsetX = { it }
                    ) togetherWith ExitTransition.KeepUntilTransitionsFinished
                } + NavDisplay.popTransitionSpec {
                    EnterTransition.None togetherWith slideOutHorizontally(
                        animationSpec = tween(easing = EaseInCirc),
                        targetOffsetX = { it }
                    )
                }
            ) {
                UserPickerScreen(
                    onNavigateBack = {
                        scope.launch {
                            while (backStack.contains(element = UserPickerNavKey)) {
                                backStack.removeLastOrNull()
                            }
                        }
                    },
                    onNavigateLogIn = {
                        if (!backStack.contains(element = AccountLogInScreenNavKey)) {
                            backStack.add(element = AccountLogInScreenNavKey)
                        }
                    },
                    onNavigateLogOut = {
                        if (!backStack.contains(element = AccountLogOutScreenNavKey)) {
                            backStack.add(element = AccountLogOutScreenNavKey)
                        }
                    }
                )
            }
        }
    )
}
