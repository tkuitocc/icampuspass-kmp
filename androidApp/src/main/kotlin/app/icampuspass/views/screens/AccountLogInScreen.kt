package app.icampuspass.views.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
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
import app.icampuspass.R
import app.icampuspass.viewmodels.UserPickerScreenViewModel
import app.icampuspass.views.components.AccountLogInWebView
import app.icampuspass.views.theme.Theme
import org.koin.androidx.compose.koinViewModel


@Composable
fun AccountLogInScreen(
    viewModel: UserPickerScreenViewModel = koinViewModel(),
    onNavigateBack: () -> Unit = {}
) {
    AccountLogInScreenContent(
        onNavigateBack = onNavigateBack,
        onLogInPageFinished = { value ->
            viewModel.saveCurrentUserId(value)
            onNavigateBack()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AccountLogInScreenContent(
    onNavigateBack: () -> Unit = {},
    onLogInPageFinished: (value: String) -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.log_in))
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
                                    text = stringResource(id = R.string.navigate_back),
                                    modifier = Modifier
                                        .padding(horizontal = 8.dp, vertical = 2.dp),
                                    style = MaterialTheme.typography.labelLarge
                                )
                            }
                        },
                        state = rememberTooltipState()
                    ) {
                        IconButton(onClick = onNavigateBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(id = R.string.navigate_back)
                            )
                        }
                    }
                }
            )
        },
        content = { innerPadding ->
            AccountLogInWebView(
                modifier = Modifier
                    .padding(paddingValues = innerPadding),
                onLogInPageFinished = onLogInPageFinished
            )
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
private fun AccountLogInScreenPreview() {
    Theme {
        AccountLogInScreenContent()
    }
}
