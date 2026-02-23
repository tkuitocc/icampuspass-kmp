package app.icampuspass.views.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
import app.icampuspass.viewmodels.UserPickerScreenViewModel
import app.icampuspass.views.theme.Theme
import org.koin.androidx.compose.koinViewModel

@Composable
fun UserPickerScreen(
    viewModel: UserPickerScreenViewModel = koinViewModel(),
    onNavigateBack: () -> Unit = {},
    onNavigateLogIn: () -> Unit = {},
    onNavigateLogOut: () -> Unit = {}
) {
    val currentUserId by viewModel.currentUserId.collectAsStateWithLifecycle()

    UserPickerScreenContent(
        currentUserId = currentUserId,
        onNavigateBack = onNavigateBack,
        onNavigateLogIn = onNavigateLogIn,
        onNavigateLogOut = onNavigateLogOut
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UserPickerScreenContent(
    currentUserId: String? = null,
    onNavigateBack: () -> Unit = {},
    onNavigateLogIn: () -> Unit = {},
    onNavigateLogOut: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Tamkang University")
                },
                actions = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults
                    .topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
                        scrolledContainerColor = MaterialTheme.colorScheme.surfaceContainerLow
                    )
            )
        },
        content = { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .consumeWindowInsets(paddingValues = innerPadding)
                    .background(color = MaterialTheme.colorScheme.surfaceContainerLow),
                contentPadding = innerPadding
            ) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (currentUserId.isNullOrBlank()) {
                            Text(text = "Guest")

                            OutlinedButton(
                                onClick = onNavigateLogIn,
                                modifier = Modifier
                                    .padding(top = 8.dp),
                            ) {
                                Text(text = stringResource(id = R.string.user_picker_log_in_button))
                            }
                        } else {
                            Text(text = currentUserId)

                            OutlinedButton(
                                onClick = onNavigateLogOut,
                                modifier = Modifier
                                    .padding(top = 8.dp),
                            ) {
                                Text(text = stringResource(id = R.string.user_picker_log_out_button))
                            }
                        }
                    }
                }

                item {
                    Surface(
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        shape = MaterialTheme.shapes.large,
                        tonalElevation = AlertDialogDefaults.TonalElevation
                    ) {
                        Column {
                            Text(
                                modifier = Modifier.padding(16.dp),
                                text = "This area typically contains the supportive text " +
                                        "which presents the details regarding the Dialog's purpose."
                            )
                        }
                    }
                }

                item {
                    Surface(
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        shape = MaterialTheme.shapes.large,
                        tonalElevation = AlertDialogDefaults.TonalElevation
                    ) {
                        Column {
                            Text(
                                modifier = Modifier.padding(16.dp),
                                text = "This area typically contains the supportive text " +
                                        "which presents the details regarding the Dialog's purpose."
                            )
                        }
                    }
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(id = R.string.user_dialog_footer_privacy_policy),
                            style = MaterialTheme.typography.labelSmall
                        )
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
private fun UserPickerScreenPreview() {
    Theme {
        UserPickerScreenContent()
    }
}
