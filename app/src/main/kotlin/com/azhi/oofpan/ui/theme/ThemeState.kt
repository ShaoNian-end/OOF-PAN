package com.azhi.oofpan.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * 主题状态，管理深色模式切换
 */
class ThemeState {
    var isDarkMode by mutableStateOf(false)
}

val LocalThemeState = staticCompositionLocalOf { ThemeState() }

/**
 * 提供 ThemeState 的 CompositionLocal
 */
@Composable
fun ProvideThemeState(
    themeState: ThemeState,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalThemeState provides themeState) {
        content()
    }
}