package app.icampuspass.views.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import app.icampuspass.R
import app.icampuspass.viewmodels.UserPickerScreenViewModel
import app.icampuspass.views.components.AccountLogOutWebView
import app.icampuspass.views.theme.Theme
import org.koin.androidx.compose.koinViewModel


@Composable
fun AccountLogOutScreen(
    viewModel: UserPickerScreenViewModel = koinViewModel(),
    onNavigateBack: () -> Unit = {}
) = AccountLogOutScreenContent(
    onLogOutPageFinished = {
        viewModel.removeCurrentUserId()
        onNavigateBack()
    }
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AccountLogOutScreenContent(
    onLogOutPageFinished: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.log_out))
                }
            )
        },
        content = { innerPadding ->
            AccountLogOutWebView(
                modifier = Modifier
                    .padding(paddingValues = innerPadding),
                onLogOutPageFinished = onLogOutPageFinished
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
private fun AccountLogOutScreenPreview() {
    Theme {
        AccountLogOutScreenContent()
    }
}
