package app.icampuspass.views.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.QrCodeScanner
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import app.icampuspass.R
import app.icampuspass.views.theme.Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SandboxThirdScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding(),
        ) {
            val navigationSuiteType = NavigationSuiteScaffoldDefaults
                .calculateFromAdaptiveInfo(adaptiveInfo = currentWindowAdaptiveInfo())

            NavigationSuiteScaffold(
                navigationSuiteItems = {
                    item(
                        label = {
                            Text(text = stringResource(id = R.string.menu_item_main))
                        },
                        selected = true,
                        onClick = {},
                        modifier = Modifier.height(height = 48.dp),
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Home,
                                contentDescription = null
                            )
                        }
                    )

                    item(
                        label = {
                            Text(text = stringResource(id = R.string.menu_item_settings))
                        },
                        selected = false,
                        onClick = {},
                        modifier = Modifier.height(height = 48.dp),
                        icon = {
                            Icon(
                                imageVector = Icons.Outlined.Settings,
                                contentDescription = null
                            )
                        }
                    )

                    item(
                        label = {
                            Text(text = stringResource(id = R.string.menu_item_about))
                        },
                        selected = false,
                        onClick = {},
                        modifier = Modifier.height(height = 48.dp),
                        icon = {
                            Icon(
                                imageVector = Icons.Outlined.Info,
                                contentDescription = null
                            )
                        }
                    )
                },
                layoutType = when (navigationSuiteType) {
                    NavigationSuiteType.NavigationBar -> NavigationSuiteType.None
                    NavigationSuiteType.NavigationRail -> NavigationSuiteType.WideNavigationRailExpanded
                    else -> navigationSuiteType
                }
            ) {
                Scaffold(
                    topBar = {
                        Column {
                            TopAppBar(
                                title = {
                                    Text(
                                        text = stringResource(
                                            id = R.string.top_app_bar_title,
                                            stringResource(id = R.string.top_app_bar_title_unit_tku)
                                        )
                                    )
                                },
                                navigationIcon = {
                                    IconButton(onClick = {}) {
                                        Icon(
                                            imageVector = Icons.Filled.Menu,
                                            contentDescription = null
                                        )
                                    }
                                },
                                actions = {
                                    IconButton(onClick = {}) {
                                        Icon(
                                            imageVector = Icons.Outlined.QrCodeScanner,
                                            contentDescription = null,
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(all = 6.dp)
                                        )
                                    }

                                    IconButton(onClick = {}) {
                                        Icon(
                                            imageVector = Icons.Outlined.AccountCircle,
                                            contentDescription = null,
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(all = 4.dp)
                                        )
                                    }
                                }
                            )

                            HorizontalDivider(
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                        }
                    },
                    content = { innerPadding ->
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                                .consumeWindowInsets(paddingValues = innerPadding)
                        ) {
                        }
                    }
                )
            }
        }
    }
}

@Preview(
    showSystemUi = true,
    wallpaper = Wallpapers.BLUE_DOMINATED_EXAMPLE
)
@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    wallpaper = Wallpapers.BLUE_DOMINATED_EXAMPLE
)
@PreviewScreenSizes
@Composable
fun SandboxThirdScreenPreview() {
    Theme {
        SandboxThirdScreen()
    }
}
