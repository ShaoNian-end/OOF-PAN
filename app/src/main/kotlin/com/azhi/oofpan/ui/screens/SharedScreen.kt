package com.azhi.oofpan.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.azhi.oofpan.data.model.SharedFile
import com.azhi.oofpan.ui.components.MiuixCard
import com.azhi.oofpan.ui.components.MiuixFileTypeIcon
import com.azhi.oofpan.ui.components.MiuixTopBar
import com.azhi.oofpan.ui.components.formatTime
import com.azhi.oofpan.ui.theme.LocalMiuixColorScheme
import com.azhi.oofpan.ui.theme.MiuixShape

@Composable
fun SharedScreen(
    sharedList: List<SharedFile>,
    modifier: Modifier = Modifier
) {
    val colors = LocalMiuixColorScheme.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.background)
    ) {
        MiuixTopBar(title = "我的分享")

        if (sharedList.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "暂无分享",
                    style = MaterialTheme.typography.bodyLarge,
                    color = colors.onSurfaceSecondary
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(sharedList, key = { it.id }) { sharedFile ->
                    SharedFileCard(sharedFile = sharedFile)
                }
            }
        }
    }
}

@Composable
private fun SharedFileCard(
    sharedFile: SharedFile
) {
    val colors = LocalMiuixColorScheme.current
    val extension = sharedFile.fileName.substringAfterLast('.', "")

    MiuixCard(
        shape = MiuixShape.medium,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // 文件图标 + 名称 + 分享链接
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                MiuixFileTypeIcon(
                    fileType = extension,
                    iconSize = 44
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = sharedFile.fileName,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium,
                        color = colors.onBackground,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = sharedFile.shareLink,
                        style = MaterialTheme.typography.bodySmall,
                        color = colors.onSurfaceSecondary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 分割线
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(0.5.dp)
                    .background(colors.dividerLine)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 浏览 / 下载统计
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatItem(label = "浏览", value = sharedFile.viewCount.toString(), colors = colors)
                StatItem(label = "下载", value = sharedFile.downloadCount.toString(), colors = colors)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 创建时间 / 过期时间
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TimeItem(label = "创建时间", time = formatTime(sharedFile.createTime), colors = colors)
                TimeItem(label = "过期时间", time = formatTime(sharedFile.expireTime), colors = colors)
            }
        }
    }
}

@Composable
private fun StatItem(
    label: String,
    value: String,
    colors: com.azhi.oofpan.ui.theme.MiuixColorScheme
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = colors.primary
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = colors.onSurfaceSecondary
        )
    }
}

@Composable
private fun TimeItem(
    label: String,
    time: String,
    colors: com.azhi.oofpan.ui.theme.MiuixColorScheme
) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = colors.onSurfaceContainerVariant
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = time,
            style = MaterialTheme.typography.bodySmall,
            color = colors.onSurfaceSecondary
        )
    }
}