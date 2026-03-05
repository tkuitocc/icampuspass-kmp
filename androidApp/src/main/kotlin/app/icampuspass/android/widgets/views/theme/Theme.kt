package app.icampuspass.android.widgets.views.theme

import androidx.compose.runtime.Composable
import androidx.glance.GlanceTheme

@Composable
fun Theme(
    colorSchemeType: ColorSchemeType = ColorSchemeType.SYSTEM,
    useDynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    GlanceTheme(
        colors = colorProviders(
            colorSchemeType = colorSchemeType,
            useDynamicColor = useDynamicColor
        ),
        content = content
    )
}
