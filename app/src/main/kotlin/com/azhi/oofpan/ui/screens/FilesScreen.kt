package com.azhi.oofpan.ui.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.azhi.oofpan.data.model.FileItem
import com.azhi.oofpan.ui.components.MiuixFileItem
import com.azhi.oofpan.ui.components.MiuixTopBar
import com.azhi.oofpan.ui.theme.LocalMiuixColorScheme
import com.azhi.oofpan.ui.theme.MiuixColor

private enum class FileCategory(
    val label: String,
    val extensions: Set<String>
) {
    ALL("全部", emptySet()),
    DOC("文档", setOf("pptx", "ppt", "docx", "doc", "pdf", "xlsx", "xls", "txt", "fig")),
    IMAGE("图片", setOf("jpg", "jpeg", "png", "gif", "bmp", "webp")),
    VIDEO("视频", setOf("mov", "mp4", "avi", "mkv", "wmv")),
    AUDIO("音频", setOf("mp3", "wav", "flac", "aac", "ogg")),
    ZIP("压缩包", setOf("zip", "rar", "7z", "tar", "gz"));

    companion object {
        fun fromExtension(extension: String): FileCategory {
            val ext = extension.lowercase()
            return entries.firstOrNull { category ->
                category != ALL && category.extensions.contains(ext)
            } ?: ALL
        }
    }
}

@Composable
fun FilesScreen(
    files: List<FileItem>,
    onFileClick: (FileItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = LocalMiuixColorScheme.current
    var selectedCategory by remember { mutableStateOf(FileCategory.ALL) }

    val filteredFiles = remember(files, selectedCategory) {
        if (selectedCategory == FileCategory.ALL) {
            files
        } else {
            files.filter { fileItem ->
                val category = FileCategory.fromExtension(fileItem.type)
                category == selectedCategory
            }
        }
    }

    Column(modifier = modifier.fillMaxSize()) {
        MiuixTopBar(
            title = "我的文件",
            actions = {
                IconButton(onClick = { /* TODO: 搜索 */ }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "搜索",
                        tint = colors.onBackground
                    )
                }
            }
        )

        CategoryFilterChips(
            selectedCategory = selectedCategory,
            onCategorySelected = { selectedCategory = it },
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        if (filteredFiles.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "暂无文件",
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
                    items = filteredFiles,
                    key = { it.id }
                ) { fileItem ->
                    MiuixFileItem(
                        file = fileItem,
                        onClick = { onFileClick(fileItem) }
                    )
                }
            }
        }
    }
}

@Composable
private fun CategoryFilterChips(
    selectedCategory: FileCategory,
    onCategorySelected: (FileCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = LocalMiuixColorScheme.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FileCategory.entries.forEach { category ->
            val isSelected = category == selectedCategory
            FilterChip(
                selected = isSelected,
                onClick = { onCategorySelected(category) },
                label = {
                    Text(
                        text = category.label,
                        fontSize = 13.sp
                    )
                },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = colors.surfaceVariant,
                    selectedContainerColor = colors.primary,
                    labelColor = colors.onSurfaceSecondary,
                    selectedLabelColor = colors.onPrimary
                ),
                shape = RoundedCornerShape(20.dp),
                border = null
            )
        }
    }
}