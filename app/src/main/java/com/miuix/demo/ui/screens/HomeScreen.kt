package com.miuix.demo.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.*
import top.yukonga.miuix.kmp.theme.MiuixTheme

/**
 * 首页 - 展示 Miuix 基础组件
 */
@Composable
fun HomeScreen(
    onNavigateToComponents: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    val colors = MiuixTheme.colorScheme

    Scaffold(
        topBar = {
            TopAppBar(
                title = "Miuix Demo",
                actions = {
                    IconButton(onClick = { }) {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = "搜索")
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 欢迎卡片
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "欢迎使用 Miuix",
                        color = colors.onPrimaryContainer
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Miuix 是一个基于 Compose Multiplatform 的 UI 组件库，严格遵循 Xiaomi HyperOS 设计规范。",
                        color = colors.onSurface
                    )
                }
            }

            // 功能入口卡片
            Card(
                modifier = Modifier.fillMaxWidth(),
                onClick = onNavigateToComponents
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "组件库",
                            color = colors.onSurface
                        )
                        Text(
                            text = "浏览所有 Miuix 组件",
                            color = colors.onSurface
                        )
                    }
                    Icon(
                        imageVector = Icons.Filled.Widgets,
                        contentDescription = null,
                        tint = colors.primary
                    )
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                onClick = onNavigateToSettings
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "设置",
                            color = colors.onSurface
                        )
                        Text(
                            text = "Preference 组件展示",
                            color = colors.onSurface
                        )
                    }
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = null,
                        tint = colors.primary
                    )
                }
            }

            // 快速展示区域
            SmallTitle(text = "组件预览")

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { }
            ) {
                Text("主要按钮")
            }

            var textValue by remember { mutableStateOf("") }
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = textValue,
                onValueChange = { textValue = it },
                label = "输入文本..."
            )

            var switchValue by remember { mutableStateOf(true) }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("启用通知")
                Switch(
                    checked = switchValue,
                    onCheckedChange = { switchValue = it }
                )
            }

            var sliderValue by remember { mutableFloatStateOf(0.7f) }
            Slider(
                modifier = Modifier.fillMaxWidth(),
                value = sliderValue,
                onValueChange = { sliderValue = it }
            )
        }
    }
}