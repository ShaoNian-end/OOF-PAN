package com.miuix.demo.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import top.yukonga.miuix.kmp.theme.ColorSchemeMode
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.theme.ThemeController

/**
 * Miuix Demo 应用主题
 *
 * 使用 MiuixTheme 包裹整个应用，支持以下模式：
 * - System: 跟随系统
 * - Light: 浅色模式
 * - Dark: 深色模式
 * - MonetSystem: 动态取色-跟随系统
 * - MonetLight: 动态取色-浅色
 * - MonetDark: 动态取色-深色
 */
@Composable
fun MiuixDemoTheme(
    mode: ColorSchemeMode = ColorSchemeMode.System,
    content: @Composable () -> Unit
) {
    val controller = remember { ThemeController(mode) }

    MiuixTheme(
        controller = controller,
        content = content
    )
}