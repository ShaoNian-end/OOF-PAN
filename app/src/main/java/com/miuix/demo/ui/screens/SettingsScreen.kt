package com.miuix.demo.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.*
import top.yukonga.miuix.kmp.extra.*

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

            SuperSwitch(
                title = "Wi-Fi",
                subtitle = "已连接: MyWiFi_5G",
                checked = wifiEnabled,
                onCheckedChange = { wifiEnabled = it }
            )

            SuperSwitch(
                title = "蓝牙",
                subtitle = "未连接设备",
                checked = bluetoothEnabled,
                onCheckedChange = { bluetoothEnabled = it }
            )

            SuperSwitch(
                title = "移动数据",
                subtitle = "当月已用 2.5 GB",
                checked = mobileData,
                onCheckedChange = { mobileData = it }
            )

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            // 显示设置
            SmallTitle(text = "显示与亮度")

            var brightnessValue by remember { mutableFloatStateOf(0.6f) }
            var darkMode by remember { mutableStateOf(false) }

            Slider(
                modifier = Modifier.fillMaxWidth(),
                value = brightnessValue,
                onValueChange = { brightnessValue = it }
            )

            SuperSwitch(
                title = "深色模式",
                subtitle = "降低屏幕亮度，减轻视觉疲劳",
                checked = darkMode,
                onCheckedChange = { darkMode = it }
            )

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            // 通知设置
            SmallTitle(text = "通知与提醒")

            var notificationEnabled by remember { mutableStateOf(true) }
            var soundEnabled by remember { mutableStateOf(true) }
            var vibrateEnabled by remember { mutableStateOf(true) }

            SuperSwitch(
                title = "允许通知",
                checked = notificationEnabled,
                onCheckedChange = { notificationEnabled = it }
            )

            SuperSwitch(
                title = "声音",
                subtitle = "收到通知时播放声音",
                checked = soundEnabled,
                onCheckedChange = { soundEnabled = it }
            )

            SuperSwitch(
                title = "振动",
                subtitle = "收到通知时振动",
                checked = vibrateEnabled,
                onCheckedChange = { vibrateEnabled = it }
            )

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            // 选项设置
            SmallTitle(text = "其他设置")

            var option1 by remember { mutableStateOf(true) }
            var option2 by remember { mutableStateOf(false) }

            SuperCheckbox(
                title = "自动更新",
                subtitle = "通过 Wi-Fi 自动下载更新",
                checked = option1,
                onCheckedChange = { option1 = it }
            )

            SuperCheckbox(
                title = "发送使用数据",
                subtitle = "帮助改进产品体验",
                checked = option2,
                onCheckedChange = { option2 = it }
            )

            SuperArrow(
                title = "关于",
                subtitle = "版本 1.0.0",
                onClick = { }
            )

            SuperArrow(
                title = "隐私政策",
                onClick = { }
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}