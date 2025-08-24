package app.icampuspass

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.icampuspass.components.ModalDrawerSheetComponent
import app.icampuspass.components.TopAppBarComponent
import app.icampuspass.destinations.ClassScheduleDestination
import app.icampuspass.destinations.MainDestination
import app.icampuspass.screens.ClassScheduleScreen
import app.icampuspass.screens.MainScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Preview
@Composable
fun App() {
    val drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val scope: CoroutineScope = rememberCoroutineScope()

    val navController: NavHostController = rememberNavController()

    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) {
            darkColorScheme()
        } else {
            lightColorScheme()
        }
    ) {
        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheetComponent(
                    navController = navController,
                    scope = scope,
                    drawerState = drawerState
                )
            },
            modifier = Modifier.fillMaxSize().safeContentPadding(),
            drawerState = drawerState,
            gesturesEnabled = drawerState.isOpen,
            content = {
                Scaffold(
                    topBar = {
                        TopAppBarComponent(scope = scope, drawerState = drawerState)
                    },
                    content = { innerPadding ->
                        NavHost(navController = navController, startDestination = MainDestination) {
                            composable<MainDestination> {
                                MainScreen(
                                    modifier = Modifier
                                        .padding(innerPadding)
                                        .fillMaxSize()
                                )
                            }
                            composable<ClassScheduleDestination> {
                                ClassScheduleScreen(
                                    modifier = Modifier
                                        .padding(innerPadding)
                                        .fillMaxSize()
                                )
                            }
                        }

                        BackHandler(enabled = drawerState.isOpen) {
                            scope.launch {
                                drawerState.close()
                            }
                        }
                    }
                )
            }
        )
    }
}
