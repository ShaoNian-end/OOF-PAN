package com.miuix.demo.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.*
import top.yukonga.miuix.kmp.extra.*
import top.yukonga.miuix.kmp.theme.MiuixTheme

/**
 * 组件展示页面 - 展示 Miuix 的各种组件
 */
@Composable
fun ComponentsScreen(
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = "组件库",
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // ===== 基础组件 =====
            SmallTitle(text = "基础组件")

            // Card
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Card 卡片组件")
                    Text(text = "卡片用于展示分组信息", color = MiuixTheme.colorScheme.onSurfaceVariant)
                }
            }

            // Button 示例
            SmallTitle(text = "Button 按钮")
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(onClick = { }) {
                    Text("主要按钮")
                }
                Button(onClick = { }) {
                    Text("次要按钮")
                }
            }

            // IconButton 示例
            SmallTitle(text = "IconButton 图标按钮")
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Filled.Favorite, contentDescription = "收藏")
                }
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Filled.Share, contentDescription = "分享")
                }
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = "删除")
                }
            }

            // TextField 示例
            SmallTitle(text = "TextField 输入框")
            var textValue by remember { mutableStateOf("") }
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = textValue,
                onValueChange = { textValue = it },
                placeholder = "请输入内容..."
            )

            // Switch 示例
            SmallTitle(text = "Switch 开关")
            var switch1 by remember { mutableStateOf(true) }
            var switch2 by remember { mutableStateOf(false) }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Wi-Fi")
                Switch(checked = switch1, onCheckedChange = { switch1 = it })
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("蓝牙")
                Switch(checked = switch2, onCheckedChange = { switch2 = it })
            }

            // Checkbox 示例
            SmallTitle(text = "Checkbox 复选框")
            var check1 by remember { mutableStateOf(true) }
            var check2 by remember { mutableStateOf(false) }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("选项 A")
                Checkbox(checked = check1, onCheckedChange = { check1 = it })
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("选项 B")
                Checkbox(checked = check2, onCheckedChange = { check2 = it })
            }

            // Slider 示例
            SmallTitle(text = "Slider 滑动条")
            var sliderValue by remember { mutableFloatStateOf(0.5f) }
            Slider(
                modifier = Modifier.fillMaxWidth(),
                value = sliderValue,
                onValueChange = { sliderValue = it }
            )

            // ProgressIndicator 示例
            SmallTitle(text = "ProgressIndicator 进度条")
            ProgressIndicator(
                modifier = Modifier.fillMaxWidth(),
                progress = 0.7f
            )

            // ===== 扩展组件 =====
            SmallTitle(text = "扩展组件")

            // SuperArrow 示例
            SuperArrow(
                title = "SuperArrow 带箭头组件",
                onClick = { }
            )
            SuperArrow(
                title = "可点击跳转",
                subtitle = "这是一个带副标题的 Arrow 组件",
                onClick = { }
            )

            // SuperSwitch 示例
            var superSwitch1 by remember { mutableStateOf(true) }
            var superSwitch2 by remember { mutableStateOf(false) }
            SuperSwitch(
                title = "SuperSwitch 开关",
                checked = superSwitch1,
                onCheckedChange = { superSwitch1 = it }
            )
            SuperSwitch(
                title = "飞行模式",
                subtitle = "关闭所有无线连接",
                checked = superSwitch2,
                onCheckedChange = { superSwitch2 = it }
            )

            // SuperCheckbox 示例
            var superCheck1 by remember { mutableStateOf(true) }
            SuperCheckbox(
                title = "SuperCheckbox 复选框",
                checked = superCheck1,
                onCheckedChange = { superCheck1 = it }
            )

            // Divider
            Divider(modifier = Modifier.padding(vertical = 8.dp))

            // FloatingActionButton
            FloatingActionButton(
                onClick = { }
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "添加")
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}