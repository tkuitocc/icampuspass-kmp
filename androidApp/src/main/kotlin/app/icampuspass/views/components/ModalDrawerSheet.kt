package app.icampuspass.views.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.EventNote
import androidx.compose.material.icons.automirrored.filled.MenuOpen
import androidx.compose.material.icons.automirrored.outlined.EventNote
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
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.Wallpapers.BLUE_DOMINATED_EXAMPLE
import androidx.compose.ui.tooling.preview.Wallpapers.GREEN_DOMINATED_EXAMPLE
import androidx.compose.ui.tooling.preview.Wallpapers.RED_DOMINATED_EXAMPLE
import androidx.compose.ui.tooling.preview.Wallpapers.YELLOW_DOMINATED_EXAMPLE
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import app.icampuspass.R
import app.icampuspass.views.navigation.navkeys.AboutScreenNavKey
import app.icampuspass.views.navigation.navkeys.ClassScheduleScreenNavKey
import app.icampuspass.views.navigation.navkeys.MainScreenNavKey
import app.icampuspass.views.navigation.navkeys.SettingsScreenNavKey
import app.icampuspass.views.theme.Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalDrawerSheet(
    backStack: NavBackStack<NavKey> = rememberNavBackStack(MainScreenNavKey),
    onMenuClose: () -> Unit = {},
    onNavigateMain: () -> Unit = {},
    onNavigateClassSchedule: () -> Unit = {},
    onNavigateSettings: () -> Unit = {},
    onNavigateAbout: () -> Unit = {}
) {
    ModalDrawerSheet(modifier = Modifier.width(width = 320.dp)) {
        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.app_name))
            },
            navigationIcon = {
                TooltipBox(
                    positionProvider = TooltipDefaults
                        .rememberTooltipPositionProvider(
                            positioning = TooltipAnchorPosition.Below,
                            spacingBetweenTooltipAndAnchor = 16.dp
                        ),
                    tooltip = {
                        PlainTooltip(
                            modifier = Modifier.padding(all = 8.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.menu_action_close),
                                modifier = Modifier
                                    .padding(horizontal = 8.dp, vertical = 2.dp),
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    },
                    state = rememberTooltipState()
                ) {
                    IconButton(onClick = onMenuClose) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.MenuOpen,
                            contentDescription = stringResource(id = R.string.menu_action_close)
                        )
                    }
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
        )

        HorizontalDivider(
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 0.dp)
                .fillMaxWidth(),
        ) {
            NavigationDrawerItem(
                label = {
                    Text(text = stringResource(id = R.string.menu_item_main))
                },
                selected = backStack.lastOrNull() == MainScreenNavKey,
                onClick = onNavigateMain,
                modifier = Modifier.height(height = 48.dp),
                icon = {
                    Icon(
                        imageVector = if (backStack.lastOrNull() == MainScreenNavKey) {
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
                    Text(text = stringResource(id = R.string.menu_item_class_schedule))
                },
                selected = backStack.lastOrNull() == ClassScheduleScreenNavKey,
                onClick = onNavigateClassSchedule,
                modifier = Modifier.height(height = 48.dp),
                icon = {
                    Icon(
                        imageVector = if (backStack.lastOrNull() == ClassScheduleScreenNavKey) {
                            Icons.AutoMirrored.Filled.EventNote
                        } else {
                            Icons.AutoMirrored.Outlined.EventNote
                        },
                        contentDescription = null
                    )
                }
            )

            NavigationDrawerItem(
                label = {
                    Text(text = stringResource(id = R.string.menu_item_settings))
                },
                selected = backStack.lastOrNull() == SettingsScreenNavKey,
                onClick = onNavigateSettings,
                modifier = Modifier.height(height = 48.dp),
                icon = {
                    Icon(
                        imageVector = if (backStack.lastOrNull() == SettingsScreenNavKey) {
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
                selected = backStack.lastOrNull() == AboutScreenNavKey,
                onClick = onNavigateAbout,
                modifier = Modifier.height(height = 48.dp),
                icon = {
                    Icon(
                        imageVector = if (backStack.lastOrNull() == AboutScreenNavKey) {
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
@Preview
@Composable
private fun ModalDrawerSheetPreview() {
    Theme {
        ModalDrawerSheet()
    }
}
