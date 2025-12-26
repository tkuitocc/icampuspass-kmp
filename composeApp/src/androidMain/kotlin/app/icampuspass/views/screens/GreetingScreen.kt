package app.icampuspass.views.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.tooling.preview.Wallpapers.BLUE_DOMINATED_EXAMPLE
import androidx.compose.ui.tooling.preview.Wallpapers.GREEN_DOMINATED_EXAMPLE
import androidx.compose.ui.tooling.preview.Wallpapers.RED_DOMINATED_EXAMPLE
import androidx.compose.ui.tooling.preview.Wallpapers.YELLOW_DOMINATED_EXAMPLE
import app.icampuspass.models.Greeting
import app.icampuspass.composeapp.generated.resources.Res
import app.icampuspass.composeapp.generated.resources.compose_multiplatform
import app.icampuspass.models.getPlatform
import app.icampuspass.viewmodels.GreetingScreenViewModel
import app.icampuspass.views.theme.Theme
import org.jetbrains.compose.resources.painterResource
import org.koin.androidx.compose.koinViewModel

@Composable
fun GreetingScreen(
    viewModel: GreetingScreenViewModel = koinViewModel()
) {
    GreetingScreenContent(
        greet = remember { viewModel.getGreeting().greet() }
    )
}

@Composable
private fun GreetingScreenContent(
    greet: String
) {
    var showContent by remember { mutableStateOf(value = false) }

    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.surface)
            .safeContentPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = { showContent = !showContent }) {
            Text(text = "Click me!")
        }

        AnimatedVisibility(visible = showContent) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(resource = Res.drawable.compose_multiplatform),
                    contentDescription = null
                )

                Text(text = "Compose: $greet")
            }
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
@PreviewScreenSizes
@Preview
@Composable
private fun GreetingScreenPreview() {
    Theme {
        GreetingScreenContent(
            greet = remember { Greeting(platform = getPlatform()).greet() }
        )
    }
}
