package app.icampuspass.views.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun colorScheme(
    useDarkColorScheme: Boolean = isSystemInDarkTheme(),
    useDynamicColor: Boolean = true
): ColorScheme {
    val supportsDynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

    return when {
        useDynamicColor && supportsDynamicColor -> {
            val context = LocalContext.current

            when {
                useDarkColorScheme -> dynamicDarkColorScheme(context = context)
                else -> dynamicLightColorScheme(context = context)
            }
        }

        useDarkColorScheme -> darkColorScheme()
        else -> lightColorScheme()
    }
}
