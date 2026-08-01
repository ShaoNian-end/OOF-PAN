package com.miuix.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.miuix.demo.ui.screens.ComponentsScreen
import com.miuix.demo.ui.screens.HomeScreen
import com.miuix.demo.ui.screens.SettingsScreen
import com.miuix.demo.ui.theme.MiuixDemoTheme
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.NavigationBar
import top.yukonga.miuix.kmp.basic.NavigationBarItem
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*

/**
 * 主 Activity - 使用 Miuix 组件构建的完整应用
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiuixDemoTheme {
                MiuixApp()
            }
        }
    }
}

/**
 * 应用主入口 - 底部导航结构
 */
@Composable
fun MiuixApp() {
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "首页"
                        )
                    },
                    label = { Text("首页") }
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Widgets,
                            contentDescription = "组件"
                        )
                    },
                    label = { Text("组件") }
                )
                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "设置"
                        )
                    },
                    label = { Text("设置") }
                )
            }
        }
    ) {
        when (selectedTab) {
            0 -> HomeScreen(
                onNavigateToComponents = { selectedTab = 1 },
                onNavigateToSettings = { selectedTab = 2 }
            )
            1 -> ComponentsScreen(
                onBack = { selectedTab = 0 }
            )
            2 -> SettingsScreen(
                onBack = { selectedTab = 0 }
            )
        }
    }
}