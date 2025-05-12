package app.icampuspass.views.composables

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuOpen
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import app.icampuspass.R
import app.icampuspass.views.navigation.destinations.AboutScreenDestination
import app.icampuspass.views.navigation.destinations.MainScreenDestination
import app.icampuspass.views.navigation.destinations.SettingsScreenDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalDrawerSheet(
    navController: NavHostController = rememberNavController(),
    onMenuClose: () -> Unit = {},
    onNavigateMain: () -> Unit = {},
    onNavigateSettings: () -> Unit = {},
    onNavigateAbout: () -> Unit = {}
) {
    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentDestination = navBackStackEntry?.destination

    ModalDrawerSheet {
        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.app_name))
            },
            navigationIcon = {
                IconButton(onClick = onMenuClose) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.MenuOpen,
                        contentDescription = null
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent
            )
        )

        HorizontalDivider(
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Column(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 0.dp)
                .fillMaxWidth(),
        ) {
            NavigationDrawerItem(
                label = {
                    Text(text = stringResource(id = R.string.menu_item_main))
                },
                selected = currentDestination?.hierarchy?.any {
                    it.hasRoute(MainScreenDestination::class)
                } == true,
                onClick = onNavigateMain,
                icon = {
                    Icon(
                        imageVector = if (currentDestination?.hierarchy?.any {
                                it.hasRoute(MainScreenDestination::class)
                            } == true) {
                            Icons.Filled.Home
                        } else {
                            Icons.Outlined.Home
                        },
                        contentDescription = null
                    )
                }
            )

            NavigationDrawerItem(
                label = {
                    Text(text = stringResource(id = R.string.menu_item_settings))
                },
                selected = currentDestination?.hierarchy?.any {
                    it.hasRoute(SettingsScreenDestination::class)
                } == true,
                onClick = onNavigateSettings,
                icon = {
                    Icon(
                        imageVector = if (currentDestination?.hierarchy?.any {
                                it.hasRoute(SettingsScreenDestination::class)
                            } == true) {
                            Icons.Filled.Settings
                        } else {
                            Icons.Outlined.Settings
                        },
                        contentDescription = null
                    )
                }
            )

            NavigationDrawerItem(
                label = {
                    Text(text = stringResource(id = R.string.menu_item_about))
                },
                selected = currentDestination?.hierarchy?.any {
                    it.hasRoute(AboutScreenDestination::class)
                } == true,
                onClick = onNavigateAbout,
                icon = {
                    Icon(
                        imageVector = if (currentDestination?.hierarchy?.any {
                                it.hasRoute(AboutScreenDestination::class)
                            } == true) {
                            Icons.Filled.Info
                        } else {
                            Icons.Outlined.Info
                        },
                        contentDescription = null
                    )
                }
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Preview(
    backgroundColor = 0xFF000000,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun ModalDrawerSheetPreview() {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) {
            darkColorScheme()
        } else {
            lightColorScheme()
        }
    ) {
        ModalDrawerSheet()
    }
}
