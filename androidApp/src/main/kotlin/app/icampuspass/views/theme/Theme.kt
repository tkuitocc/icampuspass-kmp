package app.icampuspass.views.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun Theme(
    useDarkColorScheme: Boolean = isSystemInDarkTheme(),
    useDynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = colorScheme(
            useDarkColorScheme = useDarkColorScheme,
            useDynamicColor = useDynamicColor
        ),
        content = content
    )
}
