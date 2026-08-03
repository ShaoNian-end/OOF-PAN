package com.azhi.oofpan.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = MiuixColor.Primary,
    onPrimary = MiuixColor.TextOnPrimary,
    primaryContainer = MiuixColor.PrimaryLight,
    background = MiuixColor.Background,
    surface = MiuixColor.Surface,
    surfaceVariant = MiuixColor.SurfaceVariant,
    onBackground = MiuixColor.TextPrimary,
    onSurface = MiuixColor.TextPrimary,
    onSurfaceVariant = MiuixColor.TextSecondary,
    outline = MiuixColor.Divider,
    error = MiuixColor.Error,
)

private val DarkColorScheme = darkColorScheme(
    primary = MiuixColor.PrimaryLight,
    onPrimary = MiuixColor.TextOnPrimary,
    primaryContainer = MiuixColor.PrimaryDark,
    background = MiuixColor.DarkBackground,
    surface = MiuixColor.DarkSurface,
    surfaceVariant = MiuixColor.DarkSurfaceVariant,
    onBackground = MiuixColor.DarkTextPrimary,
    onSurface = MiuixColor.DarkTextPrimary,
    onSurfaceVariant = MiuixColor.DarkTextSecondary,
    outline = MiuixColor.DividerAlpha,
    error = MiuixColor.Error,
)

@Composable
fun MiuixTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = MiuixTypography,
        shapes = androidx.compose.material3.Shapes(
            extraSmall = MiuixShape.small,
            small = MiuixShape.medium,
            medium = MiuixShape.large,
            large = MiuixShape.xlarge,
            extraLarge = MiuixShape.round,
        ),
        content = content
    )
}