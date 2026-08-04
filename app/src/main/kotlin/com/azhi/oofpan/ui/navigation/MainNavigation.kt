package com.azhi.oofpan.ui.navigation

import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.shape.RectangleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
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
import com.azhi.oofpan.data.model.FileItem
import com.azhi.oofpan.ui.components.BreadcrumbItem
import com.azhi.oofpan.ui.screens.FilesScreen
import com.azhi.oofpan.ui.screens.FolderContentScreen
import com.azhi.oofpan.ui.screens.HomeScreen
import com.azhi.oofpan.ui.screens.ProfileScreen
import com.azhi.oofpan.ui.screens.SettingsScreen
import com.azhi.oofpan.ui.screens.SharedScreen
import com.azhi.oofpan.ui.theme.LocalMiuixColorScheme
import top.yukonga.miuix.kmp.blur.BlurDefaults
import top.yukonga.miuix.kmp.blur.BlendColorEntry
import top.yukonga.miuix.kmp.blur.BlurBlendMode
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.blur.textureBlur

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val screen: @Composable () -> Unit
)

@Composable
fun MainNavigation(modifier: Modifier = Modifier) {
    val data = MockData.cloudData
    var showSettings by rememberSaveable { mutableStateOf(false) }
    val colors = LocalMiuixColorScheme.current

    // 文件夹导航栈：记录当前所在的文件夹路径
    var folderStack by rememberSaveable { mutableStateOf<List<FileItem>>(emptyList()) }

    val navItems = listOf(
        BottomNavItem("首页", Icons.Filled.Home) {
            HomeScreen(
                user = data.user,
                files = data.files,
                recentActivities = data.recentActivities,
                onFileClick = { file ->
                    if (file.type == "folder") {
                        folderStack = folderStack + file
                    }
                },
                onViewAllFiles = {}
            )
        },
        BottomNavItem("文件", Icons.Filled.Folder) {
            FilesScreen(
                files = data.files,
                onFileClick = { file ->
                    if (file.type == "folder") {
                        folderStack = folderStack + file
                    }
                }
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

    // ---- 系统返回键处理 ----
    val dispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    // 设置页面返回
    DisposableEffect(showSettings, dispatcher) {
        if (!showSettings || dispatcher == null) return@DisposableEffect onDispose {}

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showSettings = false
            }
        }
        dispatcher.addCallback(callback)
        onDispose { callback.remove() }
    }

    // 文件夹导航返回
    val isInFolder = folderStack.isNotEmpty()
    DisposableEffect(isInFolder, dispatcher) {
        if (!isInFolder || dispatcher == null) return@DisposableEffect onDispose {}

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                folderStack = folderStack.dropLast(1)
            }
        }
        dispatcher.addCallback(callback)
        onDispose { callback.remove() }
    }

    // ---- 页面路由 ----
    if (showSettings) {
        SettingsScreen(
            onBackClick = { showSettings = false }
        )
        return
    }

    // ---- Miuix 模糊背景捕获 ----
    // 捕获 Scaffold 背后的内容，用于底部导航栏的实时模糊
    val backdrop = rememberLayerBackdrop {
        drawRect(color = colors.background)
        drawContent()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .layerBackdrop(backdrop)
    ) {
        Scaffold(
            modifier = modifier.fillMaxSize(),
            containerColor = colors.background,
            // 仅消耗底部系统栏内边距，顶部不消耗实现状态栏沉浸
            contentWindowInsets = systemBars.only(WindowInsetsSides.Bottom),
            bottomBar = {
                NavigationBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .textureBlur(
                            backdrop = backdrop,
                            shape = RectangleShape,
                            blurRadius = 20f,
                            colors = BlurDefaults.blurColors(
                                blendColors = listOf(
                                    BlendColorEntry(
                                        color = colors.surface.copy(alpha = 0.75f),
                                        mode = BlurBlendMode.SrcOver
                                    )
                                )
                            )
                        ),
                    containerColor = Color.Transparent,
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
                                selectedIconColor = colors.primary,
                                selectedTextColor = colors.primary,
                                unselectedIconColor = colors.onSurfaceSecondary,
                                unselectedTextColor = colors.onSurfaceSecondary,
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
                if (isInFolder) {
                    // 构建面包屑路径
                    val breadcrumbItems = buildList {
                        add(BreadcrumbItem("全部文件") { folderStack = emptyList() })
                        folderStack.forEachIndexed { index, folder ->
                            add(
                                BreadcrumbItem(folder.name) {
                                    folderStack = folderStack.take(index + 1)
                                }
                            )
                        }
                    }

                    // 当前文件夹内的文件
                    val currentFolder = folderStack.last()
                    val currentFiles = currentFolder.children ?: emptyList()

                    FolderContentScreen(
                        breadcrumbItems = breadcrumbItems,
                        files = currentFiles,
                        onFolderClick = { folder ->
                            folderStack = folderStack + folder
                        },
                        onFileClick = { /* TODO: 文件操作 */ },
                        onBackClick = {
                            folderStack = folderStack.dropLast(1)
                        }
                    )
                } else {
                    when (selectedIndex) {
                        0 -> navItems[0].screen()
                        1 -> navItems[1].screen()
                        2 -> navItems[2].screen()
                        3 -> navItems[3].screen()
                    }
                }
            }
        }
    }
}