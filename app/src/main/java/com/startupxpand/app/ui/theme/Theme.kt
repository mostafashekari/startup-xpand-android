package com.startupxpand.app.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Cyan500,
    onPrimary = Navy950,
    primaryContainer = Cyan600,
    onPrimaryContainer = Color.White,
    secondary = Amber500,
    onSecondary = Navy950,
    secondaryContainer = Amber600,
    onSecondaryContainer = Color.White,
    tertiary = Emerald500,
    background = Navy950,
    onBackground = Slate100,
    surface = Navy900,
    onSurface = Slate100,
    surfaceVariant = CardDark,
    onSurfaceVariant = Slate300,
    outline = CardBorder,
    outlineVariant = Navy800,
    error = Rose500,
    onError = Color.White
)

@Composable
fun StartupXpandTheme(
    darkTheme: Boolean = true, // force modern dark by default
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Transparent.toArgb()
            window.navigationBarColor = Navy950.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
