package com.azhi.oofpan.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.azhi.oofpan.data.model.FileItem
import com.azhi.oofpan.ui.components.BreadcrumbItem
import com.azhi.oofpan.ui.components.MiuixBreadcrumbBar
import com.azhi.oofpan.ui.components.MiuixFileItem
import com.azhi.oofpan.ui.theme.LocalMiuixColorScheme

/**
 * 文件夹内容浏览页面
 * 顶部显示面包屑导航栏，下方显示文件夹内的文件列表
 */
@Composable
fun FolderContentScreen(
    breadcrumbItems: List<BreadcrumbItem>,
    files: List<FileItem>,
    onFolderClick: (FileItem) -> Unit,
    onFileClick: (FileItem) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = LocalMiuixColorScheme.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.background)
    ) {
        // 顶部：返回按钮 + 面包屑
        // statusBarsPadding 使背景色延伸到状态栏区域，实现沉浸效果
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .background(colors.surface),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "返回",
                    tint = colors.onBackground
                )
            }
            MiuixBreadcrumbBar(
                items = breadcrumbItems,
                modifier = Modifier.weight(1f)
            )
        }

        // 文件列表
        if (files.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "此文件夹为空",
                    color = colors.onSurfaceSecondary,
                    fontSize = 14.sp
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                ),
                verticalArrangement = Arrangement.spacedBy(0.dp)
            ) {
                items(
                    items = files,
                    key = { it.id }
                ) { file ->
                    if (file.type == "folder") {
                        MiuixFileItem(
                            file = file,
                            onClick = { onFolderClick(file) }
                        )
                    } else {
                        MiuixFileItem(
                            file = file,
                            onClick = { onFileClick(file) }
                        )
                    }
                }
            }
        }
    }
}