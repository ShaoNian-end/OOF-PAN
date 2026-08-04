package com.azhi.oofpan.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.azhi.oofpan.data.MockData
import com.azhi.oofpan.ui.screens.FilesScreen
import com.azhi.oofpan.ui.screens.HomeScreen
import com.azhi.oofpan.ui.screens.ProfileScreen
import com.azhi.oofpan.ui.screens.SettingsScreen
import com.azhi.oofpan.ui.screens.SharedScreen
import com.azhi.oofpan.ui.theme.MiuixColor

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val screen: @Composable () -> Unit
)

@Composable
fun MainNavigation(modifier: Modifier = Modifier) {
    val data = MockData.cloudData
    var showSettings by rememberSaveable { mutableStateOf(false) }

    val navItems = listOf(
        BottomNavItem("首页", Icons.Filled.Home) {
            HomeScreen(
                user = data.user,
                files = data.files,
                recentActivities = data.recentActivities,
                onFileClick = {},
                onViewAllFiles = {}
            )
        },
        BottomNavItem("文件", Icons.Filled.Folder) {
            FilesScreen(
                files = data.files,
                onFileClick = {}
            )
        },
        BottomNavItem("分享", Icons.Filled.Share) {
            SharedScreen(
                sharedList = data.sharedList
            )
        },
        BottomNavItem("我的", Icons.Filled.Person) {
            ProfileScreen(
                user = data.user,
                onSettingClick = { showSettings = true },
                onAboutClick = {}
            )
        }
    )

    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }

    if (showSettings) {
        SettingsScreen(
            onBackClick = { showSettings = false }
        )
        return
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MiuixColor.Background,
        bottomBar = {
            NavigationBar(
                modifier = Modifier.fillMaxWidth(),
                containerColor = MiuixColor.Surface.copy(alpha = 0.85f),
                tonalElevation = 0.dp
            ) {
                for (index in navItems.indices) {
                    val item = navItems[index]
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label
                            )
                        },
                        label = { Text(text = item.label) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MiuixColor.Primary,
                            selectedTextColor = MiuixColor.Primary,
                            unselectedIconColor = MiuixColor.TextSecondary,
                            unselectedTextColor = MiuixColor.TextSecondary,
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (selectedIndex) {
                0 -> navItems[0].screen()
                1 -> navItems[1].screen()
                2 -> navItems[2].screen()
                3 -> navItems[3].screen()
            }
        }
    }
}