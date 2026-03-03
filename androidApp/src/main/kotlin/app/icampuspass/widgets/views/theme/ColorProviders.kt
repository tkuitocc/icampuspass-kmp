package app.icampuspass.widgets.views.theme

import android.os.Build
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.glance.LocalContext
import androidx.glance.color.ColorProviders
import androidx.glance.material3.ColorProviders

@Composable
fun colorProviders(
    colorSchemeType: ColorSchemeType = ColorSchemeType.SYSTEM,
    useDynamicColor: Boolean = true
): ColorProviders {
    val context = LocalContext.current

    return remember(
        context,
        colorSchemeType,
        useDynamicColor
    ) {
        val supportsDynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

        val (lightColorScheme, darkColorScheme) = when {
            useDynamicColor && supportsDynamicColor ->
                dynamicLightColorScheme(context = context) to
                    dynamicDarkColorScheme(context = context)
            else -> lightColorScheme() to darkColorScheme()
        }

        when (colorSchemeType) {
            ColorSchemeType.LIGHT -> ColorProviders(scheme = lightColorScheme)
            ColorSchemeType.DARK -> ColorProviders(scheme = darkColorScheme)
            else -> ColorProviders(
                light = lightColorScheme,
                dark = darkColorScheme
            )
        }
    }
}
