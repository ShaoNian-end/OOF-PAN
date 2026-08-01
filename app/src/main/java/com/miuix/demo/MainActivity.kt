package com.miuix.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.runtime.*
import com.miuix.demo.ui.screens.ComponentsScreen
import com.miuix.demo.ui.screens.HomeScreen
import com.miuix.demo.ui.screens.LoginScreen
import com.miuix.demo.ui.screens.SettingsScreen
import com.miuix.demo.ui.theme.MiuixDemoTheme
import top.yukonga.miuix.kmp.basic.NavigationBar
import top.yukonga.miuix.kmp.basic.NavigationBarItem
import top.yukonga.miuix.kmp.basic.Scaffold

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

@Composable
fun MiuixApp() {
    var isLoggedIn by remember { mutableStateOf(false) }

    if (!isLoggedIn) {
        LoginScreen(
            onLoginSuccess = { isLoggedIn = true }
        )
    } else {
        var selectedTab by remember { mutableIntStateOf(0) }

        Scaffold(
            bottomBar = {
                NavigationBar {
                    NavigationBarItem(
                        selected = selectedTab == 0,
                        onClick = { selectedTab = 0 },
                        icon = Icons.Filled.Home,
                        label = "首页"
                    )
                    NavigationBarItem(
                        selected = selectedTab == 1,
                        onClick = { selectedTab = 1 },
                        icon = Icons.Filled.Widgets,
                        label = "组件"
                    )
                    NavigationBarItem(
                        selected = selectedTab == 2,
                        onClick = { selectedTab = 2 },
                        icon = Icons.Filled.Settings,
                        label = "设置"
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
}