package app.icampuspass

import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(scrim = Color.Black.toArgb()),
            navigationBarStyle = SystemBarStyle.dark(scrim = Color.Black.toArgb())
        )

        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
}
