package com.azhi.oofpan.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// =============================================================================
// MiuixColorScheme — 动态颜色方案（随主题切换）
// =============================================================================

/**
 * Miuix 完整颜色方案（与 Miuix Colors API 保持一致）
 *
 * 参考: https://compose-miuix-ui.github.io/miuix/guide/colors
 */
data class MiuixColorScheme(
    // Primary
    val primary: Color,
    val onPrimary: Color,
    val primaryVariant: Color,
    val onPrimaryVariant: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,

    // Error
    val error: Color,
    val onError: Color,
    val errorContainer: Color,
    val onErrorContainer: Color,

    // Disabled Primary
    val disabledPrimary: Color,
    val disabledOnPrimary: Color,
    val disabledPrimaryButton: Color,
    val disabledOnPrimaryButton: Color,
    val disabledPrimarySlider: Color,

    // Secondary
    val secondary: Color,
    val onSecondary: Color,
    val secondaryVariant: Color,
    val onSecondaryVariant: Color,
    val disabledSecondary: Color,
    val disabledOnSecondary: Color,
    val disabledSecondaryVariant: Color,
    val disabledOnSecondaryVariant: Color,
    val secondaryContainer: Color,
    val onSecondaryContainer: Color,
    val secondaryContainerVariant: Color,
    val onSecondaryContainerVariant: Color,

    // Tertiary
    val tertiaryContainer: Color,
    val onTertiaryContainer: Color,
    val tertiaryContainerVariant: Color,

    // Background
    val background: Color,
    val onBackground: Color,
    val onBackgroundVariant: Color,

    // Surface
    val surface: Color,
    val onSurface: Color,
    val surfaceVariant: Color,
    val onSurfaceSecondary: Color,
    val onSurfaceVariantSummary: Color,
    val onSurfaceVariantActions: Color,
    val disabledOnSurface: Color,
    val surfaceContainer: Color,
    val onSurfaceContainer: Color,
    val onSurfaceContainerVariant: Color,
    val surfaceContainerHigh: Color,
    val onSurfaceContainerHigh: Color,
    val surfaceContainerHighest: Color,
    val onSurfaceContainerHighest: Color,

    // Outline & Divider
    val outline: Color,
    val dividerLine: Color,

    // Window
    val windowDimming: Color,

    // Slider
    val sliderKeyPoint: Color,
    val sliderKeyPointForeground: Color,
    val sliderBackground: Color,
)

/** Miuix 浅色默认色板 */
val LightMiuixColorScheme = MiuixColorScheme(
    // Primary
    primary = Color(0xFF1A73E8),
    onPrimary = Color(0xFFFFFFFF),
    primaryVariant = Color(0xFF3482FF),
    onPrimaryVariant = Color(0xFFAECDFF),
    primaryContainer = Color(0xFF5D9BFF),
    onPrimaryContainer = Color(0xFFFFFFFF),

    // Error
    error = Color(0xFFE94634),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFDF6F4),
    onErrorContainer = Color(0xFF410002),

    // Disabled Primary
    disabledPrimary = Color(0xFFC2D9FF),
    disabledOnPrimary = Color(0xFFF3F8FF),
    disabledPrimaryButton = Color(0xFFC2D9FF),
    disabledOnPrimaryButton = Color(0xFFFFFFFF),
    disabledPrimarySlider = Color(0xFFB8CFF5),

    // Secondary
    secondary = Color(0xFFE6E6E6),
    onSecondary = Color(0xFFFFFFFF),
    secondaryVariant = Color(0xFFF0F0F0),
    onSecondaryVariant = Color(0xFF303030),
    disabledSecondary = Color(0xFFF0F0F0),
    disabledOnSecondary = Color(0xFFFCFCFC),
    disabledSecondaryVariant = Color(0xFFF2F2F2),
    disabledOnSecondaryVariant = Color(0xFFB2B2B2),
    secondaryContainer = Color(0xFFF0F0F0),
    onSecondaryContainer = Color(0xFFA9A9A9),
    secondaryContainerVariant = Color(0xFFF0F0F0),
    onSecondaryContainerVariant = Color(0xFFA8A8A8),

    // Tertiary
    tertiaryContainer = Color(0xFFEAF2FF),
    onTertiaryContainer = Color(0xFF3482FF),
    tertiaryContainerVariant = Color(0xFFEAF2FF),

    // Background
    background = Color(0xFFF5F5F5),
    onBackground = Color(0xFF000000),
    onBackgroundVariant = Color(0xFF8C93B0),

    // Surface
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF000000),
    surfaceVariant = Color(0xFFF0F0F0),
    onSurfaceSecondary = Color(0xCC000000),
    onSurfaceVariantSummary = Color(0x99000000),
    onSurfaceVariantActions = Color(0x66000000),
    disabledOnSurface = Color(0xFFB2B2B2),
    surfaceContainer = Color(0xFFFFFFFF),
    onSurfaceContainer = Color(0xFF000000),
    onSurfaceContainerVariant = Color(0xFF959595),
    surfaceContainerHigh = Color(0xFFE8E8E8),
    onSurfaceContainerHigh = Color(0xFFA2A2A2),
    surfaceContainerHighest = Color(0xFFE8E8E8),
    onSurfaceContainerHighest = Color(0xFF000000),

    // Outline & Divider
    outline = Color(0xFFD9D9D9),
    dividerLine = Color(0xFFE0E0E0),

    // Window
    windowDimming = Color(0x4D000000),

    // Slider
    sliderKeyPoint = Color(0x4DA3B3CD),
    sliderKeyPointForeground = Color(0xFF6EB5FF),
    sliderBackground = Color(0x0F000000),
)

/** Miuix 深色默认色板 */
val DarkMiuixColorScheme = MiuixColorScheme(
    // Primary
    primary = Color(0xFF277AF7),
    onPrimary = Color(0xFFFFFFFF),
    primaryVariant = Color(0xFF0073DD),
    onPrimaryVariant = Color(0xFF99C7F1),
    primaryContainer = Color(0xFF338FE4),
    onPrimaryContainer = Color(0xFFFFFFFF),

    // Error
    error = Color(0xFFF12522),
    onError = Color(0xFF690005),
    errorContainer = Color(0xFF2E0603),
    onErrorContainer = Color(0xFFFFDAD6),

    // Disabled Primary
    disabledPrimary = Color(0xFF253E64),
    disabledOnPrimary = Color(0xFF677993),
    disabledPrimaryButton = Color(0xFF253E64),
    disabledOnPrimaryButton = Color(0xFF677893),
    disabledPrimarySlider = Color(0xFF44587C),

    // Secondary
    secondary = Color(0xFF505050),
    onSecondary = Color(0xFFFFFFFF),
    secondaryVariant = Color(0xFF434343),
    onSecondaryVariant = Color(0xFFD9D9D9),
    disabledSecondary = Color(0xFF3F3F3F),
    disabledOnSecondary = Color(0xFF797979),
    disabledSecondaryVariant = Color(0xFF404040),
    disabledOnSecondaryVariant = Color(0xFF707170),
    secondaryContainer = Color(0xFF434343),
    onSecondaryContainer = Color(0xFF7C7C7C),
    secondaryContainerVariant = Color(0xFF4F4F4F),
    onSecondaryContainerVariant = Color(0xFF959595),

    // Tertiary
    tertiaryContainer = Color(0xFF2B3B54),
    onTertiaryContainer = Color(0xFF4788FF),
    tertiaryContainerVariant = Color(0xFF505050),

    // Background
    background = Color(0xFF121212),
    onBackground = Color(0xE6FFFFFF),
    onBackgroundVariant = Color(0xFF787E96),

    // Surface
    surface = Color(0xFF1E1E1E),
    onSurface = Color(0xFFF2F2F2),
    surfaceVariant = Color(0xFF242424),
    onSurfaceSecondary = Color(0xCCFFFFFF),
    onSurfaceVariantSummary = Color(0x80FFFFFF),
    onSurfaceVariantActions = Color(0x66FFFFFF),
    disabledOnSurface = Color(0xFF666666),
    surfaceContainer = Color(0xFF242424),
    onSurfaceContainer = Color(0xE6FFFFFF),
    onSurfaceContainerVariant = Color(0xFF737373),
    surfaceContainerHigh = Color(0xFF242424),
    onSurfaceContainerHigh = Color(0xFF666666),
    surfaceContainerHighest = Color(0xFF2D2D2D),
    onSurfaceContainerHighest = Color(0xFFE9E9E9),

    // Outline & Divider
    outline = Color(0xFF404040),
    dividerLine = Color(0xFF393939),

    // Window
    windowDimming = Color(0x99000000),

    // Slider
    sliderKeyPoint = Color(0x4D7A8AA6),
    sliderKeyPointForeground = Color(0xFF5DAAFF),
    sliderBackground = Color(0x26FFFFFF),
)

/**
 * CompositionLocal 持有当前的 MiuixColorScheme
 */
val LocalMiuixColorScheme = staticCompositionLocalOf { LightMiuixColorScheme }

/**
 * 提供 MiuixColorScheme 的 CompositionLocal
 */
@Composable
fun ProvideMiuixColorScheme(
    miuixColorScheme: MiuixColorScheme,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalMiuixColorScheme provides miuixColorScheme) {
        content()
    }
}

// =============================================================================
// MiuixColor — 静态颜色对象（向后兼容，方便快速引用）
// 主题色映射到 LightMiuixColorScheme 的值
// 如需动态切换主题色，请使用 LocalMiuixColorScheme.current
// =============================================================================

object MiuixColor {
    // ---- 主题色（静态映射到 LightMiuixColorScheme） ----
    val Primary = LightMiuixColorScheme.primary
    val PrimaryLight = LightMiuixColorScheme.primaryVariant
    val PrimaryDark = Color(0xFF1557B0)
    val TextOnPrimary = LightMiuixColorScheme.onPrimary
    val Background = LightMiuixColorScheme.background
    val Surface = LightMiuixColorScheme.surface
    val SurfaceVariant = LightMiuixColorScheme.surfaceVariant
    val CardBackground = LightMiuixColorScheme.surfaceContainer
    val TextPrimary = LightMiuixColorScheme.onBackground
    val TextSecondary = LightMiuixColorScheme.onSurfaceSecondary
    val TextTertiary = LightMiuixColorScheme.onSurfaceContainerVariant
    val Divider = LightMiuixColorScheme.dividerLine
    val DividerAlpha = LightMiuixColorScheme.outline

    // ---- 暗色主题色 ----
    val DarkBackground = DarkMiuixColorScheme.background
    val DarkSurface = DarkMiuixColorScheme.surface
    val DarkSurfaceVariant = DarkMiuixColorScheme.surfaceVariant
    val DarkTextPrimary = DarkMiuixColorScheme.onBackground
    val DarkTextSecondary = DarkMiuixColorScheme.onSurfaceSecondary

    // ---- 语义色 ----
    val Error = LightMiuixColorScheme.error
    val Success = Color(0xFF34A853)
    val Warning = Color(0xFFFFB300)
    val Info = Color(0xFF4285F4)

    // ---- 文件类型色 ----
    val FolderColor = Color(0xFFFFB74D)
    val DocColor = Color(0xFF4A90D9)
    val ImageColor = Color(0xFF66BB6A)
    val VideoColor = Color(0xFFEF5350)
    val AudioColor = Color(0xFFAB47BC)
    val ZipColor = Color(0xFF78909C)
    val PdfColor = Color(0xFFEF5350)
    val OtherColor = Color(0xFF90A4AE)

    // ---- VIP 等级色 ----
    val VipGold = Color(0xFFFFB300)
    val VipSilver = Color(0xFFBDBDBD)
    val VipPlatinum = Color(0xFFE0E0E0)
}