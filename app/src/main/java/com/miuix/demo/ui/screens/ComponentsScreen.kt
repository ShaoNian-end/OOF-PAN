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
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.*
import top.yukonga.miuix.kmp.theme.MiuixTheme

/**
 * 组件展示页面 - 展示 Miuix 的各种组件
 */
@Composable
fun ComponentsScreen(
    onBack: () -> Unit
) {
    val colors = MiuixTheme.colorScheme

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
            SmallTitle(text = "基础组件")

            // Card
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Card 卡片组件")
                    Text(text = "卡片用于展示分组信息", color = colors.onSurface)
                }
            }

            // Button
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

            // IconButton
            SmallTitle(text = "IconButton 图标按钮")
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
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

            // TextField
            SmallTitle(text = "TextField 输入框")
            var textValue by remember { mutableStateOf("") }
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = textValue,
                onValueChange = { textValue = it },
                label = "请输入内容..."
            )

            // Switch
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

            // Checkbox
            SmallTitle(text = "Checkbox 复选框")
            var checkState1 by remember { mutableStateOf(ToggleableState.On) }
            var checkState2 by remember { mutableStateOf(ToggleableState.Off) }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("选项 A")
                Checkbox(
                    state = checkState1,
                    onClick = {
                        checkState1 = if (checkState1 == ToggleableState.On) ToggleableState.Off else ToggleableState.On
                    }
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("选项 B")
                Checkbox(
                    state = checkState2,
                    onClick = {
                        checkState2 = if (checkState2 == ToggleableState.On) ToggleableState.Off else ToggleableState.On
                    }
                )
            }

            // Slider
            SmallTitle(text = "Slider 滑动条")
            var sliderValue by remember { mutableFloatStateOf(0.5f) }
            Slider(
                modifier = Modifier.fillMaxWidth(),
                value = sliderValue,
                onValueChange = { sliderValue = it }
            )

            // HorizontalDivider
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            // FloatingActionButton
            FloatingActionButton(onClick = { }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "添加")
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}