package com.azhi.oofpan.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = LightMiuixColorScheme.primary,
    onPrimary = LightMiuixColorScheme.onPrimary,
    primaryContainer = LightMiuixColorScheme.primaryContainer,
    secondary = LightMiuixColorScheme.secondary,
    onSecondary = LightMiuixColorScheme.onSecondary,
    secondaryContainer = LightMiuixColorScheme.secondaryContainer,
    tertiary = LightMiuixColorScheme.onTertiaryContainer,
    background = LightMiuixColorScheme.background,
    onBackground = LightMiuixColorScheme.onBackground,
    surface = LightMiuixColorScheme.surface,
    onSurface = LightMiuixColorScheme.onSurface,
    surfaceVariant = LightMiuixColorScheme.surfaceVariant,
    onSurfaceVariant = LightMiuixColorScheme.onSurfaceSecondary,
    outline = LightMiuixColorScheme.outline,
    error = LightMiuixColorScheme.error,
    onError = LightMiuixColorScheme.onError,
    errorContainer = LightMiuixColorScheme.errorContainer,
    onErrorContainer = LightMiuixColorScheme.onErrorContainer,
)

private val DarkColorScheme = darkColorScheme(
    primary = DarkMiuixColorScheme.primary,
    onPrimary = DarkMiuixColorScheme.onPrimary,
    primaryContainer = DarkMiuixColorScheme.primaryContainer,
    secondary = DarkMiuixColorScheme.secondary,
    onSecondary = DarkMiuixColorScheme.onSecondary,
    secondaryContainer = DarkMiuixColorScheme.secondaryContainer,
    tertiary = DarkMiuixColorScheme.onTertiaryContainer,
    background = DarkMiuixColorScheme.background,
    onBackground = DarkMiuixColorScheme.onBackground,
    surface = DarkMiuixColorScheme.surface,
    onSurface = DarkMiuixColorScheme.onSurface,
    surfaceVariant = DarkMiuixColorScheme.surfaceVariant,
    onSurfaceVariant = DarkMiuixColorScheme.onSurfaceSecondary,
    outline = DarkMiuixColorScheme.outline,
    error = DarkMiuixColorScheme.error,
    onError = DarkMiuixColorScheme.onError,
    errorContainer = DarkMiuixColorScheme.errorContainer,
    onErrorContainer = DarkMiuixColorScheme.onErrorContainer,
)

@Composable
fun MiuixTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val miuixColorScheme = if (darkTheme) DarkMiuixColorScheme else LightMiuixColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window

            // 沉浸式状态栏：透明背景
            window.statusBarColor = Color.Transparent.toArgb()

            // 状态栏图标颜色：亮色模式用深色，暗色模式用浅色
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme

            // 导航栏图标颜色跟随主题
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = !darkTheme
        }
    }

    ProvideMiuixColorScheme(miuixColorScheme = miuixColorScheme) {
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
}