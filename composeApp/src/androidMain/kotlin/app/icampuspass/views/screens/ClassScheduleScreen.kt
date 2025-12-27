package app.icampuspass.views.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import android.text.format.DateUtils
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.tooling.preview.Wallpapers.BLUE_DOMINATED_EXAMPLE
import androidx.compose.ui.tooling.preview.Wallpapers.GREEN_DOMINATED_EXAMPLE
import androidx.compose.ui.tooling.preview.Wallpapers.RED_DOMINATED_EXAMPLE
import androidx.compose.ui.tooling.preview.Wallpapers.YELLOW_DOMINATED_EXAMPLE
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.icampuspass.R
import app.icampuspass.viewmodels.ClassScheduleScreenViewModel
import app.icampuspass.views.theme.Theme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.format.TextStyle
import java.time.temporal.TemporalAdjusters
import java.util.Locale

@Composable
fun ClassScheduleScreen(
    viewModel: ClassScheduleScreenViewModel = koinViewModel(),
    onMenuOpen: () -> Unit = {},
    onNavigateUserPicker: () -> Unit = {}
) {
    val classSchedule by viewModel.currentUserClassSchedule.collectAsStateWithLifecycle()

    val isRefreshingClassSchedule by
    viewModel.isRefreshingClassSchedule.collectAsStateWithLifecycle()

    ClassScheduleScreenContent(
        classSchedule = classSchedule,
        isRefreshingClassSchedule = isRefreshingClassSchedule,
        onMenuOpen = onMenuOpen,
        onNavigateUserPicker = onNavigateUserPicker,
        onRefreshClassSchedule = { viewModel.refreshClassSchedule() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ClassScheduleScreenContent(
    classSchedule: Map<String, Any?>? = null,
    isRefreshingClassSchedule: Boolean = false,
    onMenuOpen: () -> Unit = {},
    onNavigateUserPicker: () -> Unit = {},
    onRefreshClassSchedule: () -> Unit = {}
) {
    val scope = rememberCoroutineScope()

    val tabWeeks = (1..5).toMutableList().map {
        DayOfWeek
            .of(it)
            .getDisplayName(TextStyle.SHORT_STANDALONE, Locale.getDefault())
    }

    val currentDayOfWeek = LocalDate
        .now()
        .dayOfWeek

    val currentDayOfWeekIndex = currentDayOfWeek.ordinal

    val currentSelectedDayOfWeekIndex = when (currentDayOfWeekIndex) {
        DayOfWeek.SATURDAY.ordinal, DayOfWeek.SUNDAY.ordinal -> DayOfWeek.MONDAY.ordinal
        else -> currentDayOfWeekIndex
    }

    val currentTemporalAdjuster = { it: Int ->
        when {
            it > currentDayOfWeekIndex ||
                    currentDayOfWeekIndex == DayOfWeek.SATURDAY.ordinal ||
                    currentDayOfWeekIndex == DayOfWeek.SUNDAY.ordinal ->
                TemporalAdjusters.nextOrSame(DayOfWeek.of(it))
            else ->
                TemporalAdjusters.previousOrSame(DayOfWeek.of(it))
        }
    }

    val tabDatesAbsoluteYearMonthDay = (1..5).toMutableList().mapIndexed { index, it ->
        DateUtils.formatDateTime(
            LocalContext.current,
            LocalDate
                .now()
                .with(currentTemporalAdjuster(it))
                .atStartOfDay()
                .toInstant(OffsetDateTime.now().offset)
                .toEpochMilli(),
            DateUtils.FORMAT_SHOW_YEAR
        )
    }

    val tabDatesDayNum = (1..5).toMutableList().map {
        SimpleDateFormat("d").format(
            LocalDate
                .now()
                .with(currentTemporalAdjuster(it))
                .atStartOfDay()
                .toInstant(OffsetDateTime.now().offset)
                .toEpochMilli()
        )
    }

    var useScrollableTabRow by remember { mutableStateOf(value = false) }

    val ScrollableTabRowScrollState = rememberScrollState()

    val allTabsVisible = ScrollableTabRowScrollState.maxValue == 0

    val pagerState = rememberPagerState(initialPage = currentSelectedDayOfWeekIndex) {
        tabWeeks.size
    }

    var lazyListScrollIndex by remember { mutableIntStateOf(value = 0) }

    var lazyListScrollOffset by remember { mutableIntStateOf(value = 0) }

    val lazyListStates = List(size = tabWeeks.size) {
        rememberLazyListState(
            initialFirstVisibleItemIndex = lazyListScrollIndex,
            initialFirstVisibleItemScrollOffset = lazyListScrollOffset
        )
    }

    LaunchedEffect(lazyListStates[pagerState.currentPage].isScrollInProgress) {
        lazyListScrollIndex =
            lazyListStates[pagerState.currentPage].firstVisibleItemIndex
        lazyListScrollOffset =
            lazyListStates[pagerState.currentPage].firstVisibleItemScrollOffset
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.class_schedule)
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
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    if (useScrollableTabRow) {
                        PrimaryScrollableTabRow(
                            selectedTabIndex = pagerState.currentPage,
                            scrollState = ScrollableTabRowScrollState,
                            edgePadding = 0.dp,
                            minTabWidth = 0.dp
                        ) {
                            tabWeeks.forEachIndexed { index, title ->
                                Tab(
                                    selected = pagerState.currentPage == index,
                                    onClick = {
                                        scope.launch {
                                            pagerState.animateScrollToPage(page = index)
                                        }
                                    },
                                    text = {
                                        Text(
                                            text = "${title}\n${tabDatesDayNum[index]}",
                                            softWrap = false,
                                            onTextLayout = {
                                                if (allTabsVisible) {
                                                    useScrollableTabRow = false
                                                }
                                            }
                                        )
                                    },
                                )
                            }
                        }
                    } else {
                        PrimaryTabRow(
                            selectedTabIndex = pagerState.currentPage
                        ) {
                            tabWeeks.forEachIndexed { index, title ->
                                Tab(
                                    selected = pagerState.currentPage == index,
                                    onClick = {
                                        scope.launch {
                                            pagerState.animateScrollToPage(page = index)
                                        }
                                    },
                                    text = {
                                        Text(
                                            text = "${title}\n${tabDatesDayNum[index]}",
                                            softWrap = false,
                                            onTextLayout = {
                                                if (it.hasVisualOverflow) {
                                                    useScrollableTabRow = true
                                                }
                                            }
                                        )
                                    },
                                )
                            }
                        }
                    }

                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier
                            .fillMaxWidth(),
                        key = { it + 1 }
                    ) { index ->
                        val dayOfWeekIndex = index + 1

                        LaunchedEffect(pagerState.isScrollInProgress) {
                            if (index != pagerState.currentPage) {
                                lazyListStates[index].scrollToItem(
                                    index = lazyListScrollIndex,
                                    scrollOffset = lazyListScrollOffset
                                )
                            }
                        }

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize(),
                            state = lazyListStates[index]
                        ) {
                            item {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp, vertical = 4.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .padding(vertical = 6.dp)
                                            .align(alignment = Alignment.Start),
                                        text = "${tabDatesAbsoluteYearMonthDay[index]}",
                                        style = MaterialTheme.typography.bodyLarge,
                                    )

                                    Spacer(
                                        modifier = Modifier
                                            .padding(vertical = 2.dp)
                                    )
                                }
                            }

                            if (classSchedule != null && classSchedule.get(key = "cells") != null) {
                                val sessionItems =
                                    classSchedule.get(key = "cells") as List<Map<String, String>>

                                items(items = sessionItems) { obj ->
                                    if (
                                        obj.get(key = "weekno") != null &&
                                        obj.get(key = "weekno").toString()
                                            .removeSurrounding("\"").trim().isNotBlank() &&
                                        obj.get(key = "weekno").toString()
                                            .removeSurrounding("\"")
                                            .trim() == dayOfWeekIndex.toString() &&
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
                                                    text = "第"
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

                            item {
                                Column(
                                    modifier = Modifier
                                        .fillParentMaxWidth()
                                        .padding(horizontal = 16.dp, vertical = 4.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
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
private fun ClassScheduleScreenPreview() {
    Theme {
        ClassScheduleScreenContent()
    }
}
