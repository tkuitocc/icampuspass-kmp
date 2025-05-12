package com.itocc.icampuspass.composables

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuOpen
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.itocc.icampuspass.R
import com.itocc.icampuspass.destinations.MainDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalDrawerSheet(
    navController: NavHostController = rememberNavController(),
    onMenuClose: () -> Unit = {},
    onNavigateMain: () -> Unit = {}
) {
    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentDestination = navBackStackEntry?.destination

    ModalDrawerSheet(
        drawerShape = RectangleShape
    ) {
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
        )

        Column(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 0.dp)
                .fillMaxWidth(),
        ) {
            NavigationDrawerItem(
                label = {
                    Text(text = "Home")
                },
                selected = currentDestination?.hierarchy?.any {
                    it.hasRoute(MainDestination::class)
                } == true,
                onClick = onNavigateMain,
                icon = {
                    Icon(
                        imageVector = if (currentDestination?.hierarchy?.any {
                                it.hasRoute(MainDestination::class)
                            } == true) {
                            Icons.Filled.Home
                        } else {
                            Icons.Outlined.Home
                        },
                        contentDescription = null
                    )
                }
            )
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
