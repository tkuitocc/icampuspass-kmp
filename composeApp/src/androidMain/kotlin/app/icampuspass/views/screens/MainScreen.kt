package app.icampuspass.views.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.QrCodeScanner
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.tooling.preview.Wallpapers.BLUE_DOMINATED_EXAMPLE
import androidx.compose.ui.tooling.preview.Wallpapers.GREEN_DOMINATED_EXAMPLE
import androidx.compose.ui.tooling.preview.Wallpapers.RED_DOMINATED_EXAMPLE
import androidx.compose.ui.tooling.preview.Wallpapers.YELLOW_DOMINATED_EXAMPLE
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.icampuspass.R
import app.icampuspass.viewmodels.ClassScheduleScreenViewModel
import app.icampuspass.views.theme.Theme
import org.koin.androidx.compose.koinViewModel
import java.util.Locale

@Composable
fun MainScreen(
    viewModel: ClassScheduleScreenViewModel = koinViewModel(),
    onMenuOpen: () -> Unit = {},
    onNavigateUserPicker: () -> Unit = {}
) {
    val currentUserId by viewModel.currentUserId.collectAsStateWithLifecycle()

    val classSchedule by viewModel.currentUserClassSchedule.collectAsStateWithLifecycle()

    val isRefreshingClassSchedule by
        viewModel.isRefreshingClassSchedule.collectAsStateWithLifecycle()

    MainScreenContent(
        currentUserId = currentUserId,
        classSchedule = classSchedule,
        isRefreshingClassSchedule = isRefreshingClassSchedule,
        onMenuOpen = onMenuOpen,
        onNavigateUserPicker = onNavigateUserPicker,
        onRefreshClassSchedule = { viewModel.refreshClassSchedule() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainScreenContent(
    currentUserId: String? = null,
    classSchedule: Map<String, Any?>? = null,
    isRefreshingClassSchedule: Boolean = false,
    onMenuOpen: () -> Unit = {},
    onNavigateUserPicker: () -> Unit = {},
    onRefreshClassSchedule: () -> Unit = {}
) {
    Scaffold(
        topBar = {
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
                                    text = stringResource(id = R.string.menu_action_open),
                                    modifier = Modifier
                                        .padding(horizontal = 8.dp, vertical = 2.dp),
                                    style = MaterialTheme.typography.labelLarge
                                )
                            }
                        },
                        state = rememberTooltipState()
                    ) {
                        IconButton(onClick = onMenuOpen) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = stringResource(id = R.string.menu_action_open)
                            )
                        }
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

                    IconButton(onClick = onNavigateUserPicker) {
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
        },
        content = { innerPadding ->
            PullToRefreshBox(
                isRefreshing = isRefreshingClassSchedule,
                onRefresh = onRefreshClassSchedule,
                modifier = Modifier
                    .padding(paddingValues = innerPadding)
                    .consumeWindowInsets(paddingValues = innerPadding)
            ) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    item {
                        Text(
                            text = when {
                                currentUserId.isNullOrBlank() -> "Hello, guest!"
                                else -> "Hello, ${currentUserId}!"
                            },
                            modifier = Modifier
                                .fillParentMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 4.dp),
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 22.sp,
                                lineHeight = 28.sp,
                                letterSpacing = 0.sp
                            )
                        )
                    }

                    if (classSchedule != null && classSchedule.get(key = "cells") != null) {
                        val sessionItems =
                            classSchedule.get(key = "cells") as List<Map<String, String>>

                        items(items = sessionItems) { obj ->
                            if (
                                obj.get(key = "ch_cos_name") != null &&
                                obj.get(key = "ch_cos_name").toString()
                                    .removeSurrounding("\"").trim().isNotBlank()
                            ) {
                                ElevatedCard(
                                    modifier = Modifier
                                        .fillParentMaxWidth()
                                        .padding(horizontal = 16.dp, vertical = 4.dp),
                                    colors = CardDefaults.elevatedCardColors(
                                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                                    )
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .padding(all = 16.dp)
                                    ) {
                                        Text(
                                            text = "週"
                                                    + obj.get(key = "week").toString()
                                                .removeSurrounding("\"").trim()
                                                    + "　第"
                                                    + obj.get(key = "sessno").toString()
                                                .removeSurrounding("\"").trim()
                                                    + "節　"
                                                    + obj.get(key = "sesstime").toString()
                                                .removeSurrounding("\"").trim()
                                                    + "　"
                                                    + obj.get(key = "room").toString()
                                                .removeSurrounding("\"").trim()
                                                    + "　成績座號："
                                                    + obj.get(key = "seatno").toString()
                                                .removeSurrounding("\"").trim()
                                        )

                                        Spacer(modifier = Modifier.padding(vertical = 4.dp))

                                        Text(
                                            text = obj.get(key = "ch_cos_name").toString()
                                                .removeSurrounding("\"").trim(),
                                            fontWeight = FontWeight.SemiBold
                                        )

                                        Spacer(modifier = Modifier.padding(vertical = 2.dp))

                                        Text(
                                            text = obj.get(key = "en_cos_name").toString()
                                                .removeSurrounding(delimiter = "\"").trim()
                                                .lowercase()
                                                .split(" ")
                                                .joinToString(separator = " ") { word ->
                                                    word.replaceFirstChar {
                                                        if (word == "of") {
                                                            it.toString()
                                                        } else {
                                                            it.titlecase(Locale.getDefault())
                                                        }
                                                    }
                                                },
                                            fontWeight = FontWeight.SemiBold
                                        )

                                        Spacer(modifier = Modifier.padding(vertical = 4.dp))

                                        Text(
                                            text = obj.get(key = "teach_name").toString()
                                                .removeSurrounding(delimiter = "\"").trim()
                                                .replace(oldValue = "助　教", newValue = "助教")
                                        )

                                        val instructorNameEn =
                                            obj.get(key = "teach_name_en").toString()
                                                .removeSurrounding(delimiter = "\"").trim()
                                                .lowercase()
                                                .replace(oldValue = ",", newValue = "")
                                                .split(" ")
                                                .joinToString(separator = " ") { word ->
                                                    word.replaceFirstChar {
                                                        if (word == "of") {
                                                            it.toString()
                                                        } else {
                                                            it.titlecase(Locale.getDefault())
                                                        }
                                                    }
                                                }

                                        Text(
                                            text = when (instructorNameEn) {
                                                "Chen Hsin Liang" -> "Chen Hsin-liang"
                                                "Wei-tsong Lee" -> "Lee Wei-tsong"
                                                "Shu-chun Yang" -> "Yang Shu-chun"
                                                else -> instructorNameEn
                                            }
                                        )

                                        val course_note = obj.get(key = "note").toString()
                                            .removeSurrounding(delimiter = "\"").trim()

                                        if (course_note.isNotBlank()) {
                                            Spacer(modifier = Modifier.padding(vertical = 4.dp))

                                            Text(
                                                text = "備註：${course_note}"
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    )
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
@Preview
@Composable
private fun MainScreenPreview() {
    Theme {
        MainScreenContent()
    }
}
