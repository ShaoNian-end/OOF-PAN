package com.miuix.demo.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.*
import top.yukonga.miuix.kmp.preference.*

/**
 * 设置页面 - 展示 Miuix Preference 组件
 */
@Composable
fun SettingsScreen(
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = "设置",
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "返回"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // 网络设置
            SmallTitle(text = "网络与连接")

            var wifiEnabled by remember { mutableStateOf(true) }
            var bluetoothEnabled by remember { mutableStateOf(false) }
            var mobileData by remember { mutableStateOf(true) }

            SwitchPreference(
                checked = wifiEnabled,
                onCheckedChange = { wifiEnabled = it },
                title = "Wi-Fi",
                summary = "已连接: MyWiFi_5G"
            )

            SwitchPreference(
                checked = bluetoothEnabled,
                onCheckedChange = { bluetoothEnabled = it },
                title = "蓝牙",
                summary = "未连接设备"
            )

            SwitchPreference(
                checked = mobileData,
                onCheckedChange = { mobileData = it },
                title = "移动数据",
                summary = "当月已用 2.5 GB"
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            // 显示设置
            SmallTitle(text = "显示与亮度")

            var brightnessValue by remember { mutableFloatStateOf(0.6f) }
            var darkMode by remember { mutableStateOf(false) }

            Slider(
                modifier = Modifier.fillMaxWidth(),
                value = brightnessValue,
                onValueChange = { brightnessValue = it }
            )

            SwitchPreference(
                checked = darkMode,
                onCheckedChange = { darkMode = it },
                title = "深色模式",
                summary = "降低屏幕亮度，减轻视觉疲劳"
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            // 通知设置
            SmallTitle(text = "通知与提醒")

            var notificationEnabled by remember { mutableStateOf(true) }
            var soundEnabled by remember { mutableStateOf(true) }
            var vibrateEnabled by remember { mutableStateOf(true) }

            SwitchPreference(
                checked = notificationEnabled,
                onCheckedChange = { notificationEnabled = it },
                title = "允许通知"
            )

            SwitchPreference(
                checked = soundEnabled,
                onCheckedChange = { soundEnabled = it },
                title = "声音",
                summary = "收到通知时播放声音"
            )

            SwitchPreference(
                checked = vibrateEnabled,
                onCheckedChange = { vibrateEnabled = it },
                title = "振动",
                summary = "收到通知时振动"
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            // 选项设置
            SmallTitle(text = "其他设置")

            var option1 by remember { mutableStateOf(true) }
            var option2 by remember { mutableStateOf(false) }

            CheckboxPreference(
                title = "自动更新",
                checked = option1,
                onCheckedChange = { option1 = it },
                summary = "通过 Wi-Fi 自动下载更新"
            )

            CheckboxPreference(
                title = "发送使用数据",
                checked = option2,
                onCheckedChange = { option2 = it },
                summary = "帮助改进产品体验"
            )

            ArrowPreference(
                title = "关于"
            )

            ArrowPreference(
                title = "隐私政策"
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}