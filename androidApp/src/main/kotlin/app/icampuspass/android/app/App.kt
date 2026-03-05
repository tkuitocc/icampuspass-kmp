package app.icampuspass.android.app

import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import app.icampuspass.android.app.views.screens.GreetingScreen
import app.icampuspass.android.shared.views.theme.Theme

@Composable
fun App() {
    Theme {
        Surface(color = Color.Black) {
            Surface(modifier = Modifier.safeDrawingPadding()) {
                GreetingScreen()
            }
        }
    }
}
