package com.azhi.oofpan.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Brightness6
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.azhi.oofpan.ui.components.MiuixCard
import com.azhi.oofpan.ui.components.MiuixTopBar
import com.azhi.oofpan.ui.theme.LocalThemeState
import com.azhi.oofpan.ui.theme.MiuixColor
import com.azhi.oofpan.ui.theme.MiuixShape
import com.azhi.oofpan.ui.theme.MiuixTypography

@Composable
fun SettingsScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val themeState = LocalThemeState.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MiuixColor.Background)
    ) {
        MiuixTopBar(
            title = "设置",
            onBackClick = onBackClick
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // 显示设置
            Text(
                text = "显示",
                style = MiuixTypography.titleMedium,
                color = MiuixColor.TextPrimary
            )

            MiuixCard(
                shape = MiuixShape.medium,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    SettingsSwitchItem(
                        icon = if (themeState.isDarkMode) Icons.Default.DarkMode else Icons.Default.LightMode,
                        label = "深色模式",
                        description = if (themeState.isDarkMode) "已开启深色模式" else "已开启浅色模式",
                        checked = themeState.isDarkMode,
                        onCheckedChange = { themeState.isDarkMode = it }
                    )
                }
            }

            // 通用设置
            Text(
                text = "通用",
                style = MiuixTypography.titleMedium,
                color = MiuixColor.TextPrimary
            )

            MiuixCard(
                shape = MiuixShape.medium,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    SettingsClickItem(
                        icon = Icons.Default.Storage,
                        label = "缓存管理",
                        onClick = { /* TODO */ }
                    )
                    SettingsDivider()
                    SettingsClickItem(
                        icon = Icons.Default.Wifi,
                        label = "网络设置",
                        onClick = { /* TODO */ }
                    )
                }
            }
        }
    }
}

@Composable
private fun SettingsSwitchItem(
    icon: ImageVector,
    label: String,
    description: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = MiuixColor.Primary,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                style = MiuixTypography.bodyLarge,
                color = MiuixColor.TextPrimary
            )
            Text(
                text = description,
                style = MiuixTypography.bodySmall,
                color = MiuixColor.TextSecondary
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = MiuixColor.Primary,
                checkedTrackColor = MiuixColor.Primary.copy(alpha = 0.3f),
                uncheckedThumbColor = MiuixColor.TextSecondary,
                uncheckedTrackColor = MiuixColor.SurfaceVariant
            )
        )
    }
}

@Composable
private fun SettingsClickItem(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = MiuixColor.TextSecondary,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = label,
            style = MiuixTypography.bodyLarge,
            color = MiuixColor.TextPrimary,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            tint = MiuixColor.TextTertiary,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
private fun SettingsDivider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 60.dp)
            .height(0.5.dp)
            .background(MiuixColor.Divider)
    )
}