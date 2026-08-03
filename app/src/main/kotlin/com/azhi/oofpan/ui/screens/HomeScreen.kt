package com.azhi.oofpan.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.azhi.oofpan.data.model.Activity
import com.azhi.oofpan.data.model.FileItem
import com.azhi.oofpan.data.model.User
import com.azhi.oofpan.ui.components.MiuixAvatar
import com.azhi.oofpan.ui.components.MiuixCard
import com.azhi.oofpan.ui.components.MiuixSpaceBar
import com.azhi.oofpan.ui.components.MiuixTopBar
import com.azhi.oofpan.ui.components.MiuixVipBadge
import com.azhi.oofpan.ui.theme.MiuixColor
import com.azhi.oofpan.ui.theme.MiuixShape
import com.azhi.oofpan.ui.theme.MiuixTypography

@Composable
fun HomeScreen(
    user: User,
    files: List<FileItem>,
    recentActivities: List<Activity>,
    onFileClick: (FileItem) -> Unit,
    onViewAllFiles: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MiuixColor.Background)
    ) {
        MiuixTopBar(title = "云盘")

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // 用户信息卡片
            UserGreetingCard(user = user)

            Spacer(modifier = Modifier.height(16.dp))

            // 快捷操作按钮
            QuickActionsRow()

            Spacer(modifier = Modifier.height(20.dp))

            // 最近文件
            RecentFilesSection(
                files = files,
                onFileClick = onFileClick,
                onViewAll = onViewAllFiles
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 最近活动
            RecentActivitiesSection(activities = recentActivities)
        }
    }
}

@Composable
private fun UserGreetingCard(user: User) {
    MiuixCard(
        shape = MiuixShape.medium,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MiuixAvatar(
                avatarUrl = user.avatar,
                modifier = Modifier.size(52.dp)
            )
            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "你好, ${user.name}",
                        style = MiuixTypography.titleMedium,
                        color = MiuixColor.TextPrimary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    MiuixVipBadge(level = user.vipLevel)
                }
                Spacer(modifier = Modifier.height(10.dp))
                MiuixSpaceBar(
                    used = user.usedSpace,
                    total = user.totalSpace,
                    percent = user.spacePercent
                )
            }
        }
    }
}

@Composable
private fun QuickActionsRow() {
    MiuixCard(
        shape = MiuixShape.medium,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            QuickActionButton(
                icon = Icons.Default.CloudUpload,
                label = "上传"
            )
            QuickActionButton(
                icon = Icons.Default.CloudDownload,
                label = "下载"
            )
            QuickActionButton(
                icon = Icons.Default.Share,
                label = "分享"
            )
            QuickActionButton(
                icon = Icons.Default.MoreHoriz,
                label = "更多"
            )
        }
    }
}

@Composable
private fun QuickActionButton(
    icon: ImageVector,
    label: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(MiuixShape.small)
            .clickable { /* TODO */ }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(MiuixShape.medium)
                .background(MiuixColor.SurfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = MiuixColor.Primary,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = label,
            style = MiuixTypography.labelLarge,
            color = MiuixColor.TextSecondary
        )
    }
}

@Composable
private fun RecentFilesSection(
    files: List<FileItem>,
    onFileClick: (FileItem) -> Unit,
    onViewAll: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "最近文件",
                style = MiuixTypography.titleMedium,
                color = MiuixColor.TextPrimary
            )
            Text(
                text = "查看全部",
                style = MiuixTypography.labelLarge,
                color = MiuixColor.Primary,
                modifier = Modifier
                    .clip(MiuixShape.small)
                    .clickable(onClick = onViewAll)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(files) { file ->
                RecentFileItem(
                    file = file,
                    onClick = { onFileClick(file) }
                )
            }
        }
    }
}

@Composable
private fun RecentFileItem(
    file: FileItem,
    onClick: () -> Unit
) {
    MiuixCard(
        shape = MiuixShape.small,
        modifier = Modifier
            .width(140.dp)
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(MiuixShape.medium)
                    .background(fileTypeColor(file.type)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Folder,
                    contentDescription = null,
                    tint = MiuixColor.TextOnPrimary,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = file.name,
                style = MiuixTypography.bodySmall,
                color = MiuixColor.TextPrimary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = file.sizeDisplay(),
                style = MiuixTypography.labelSmall,
                color = MiuixColor.TextTertiary
            )
        }
    }
}

@Composable
private fun RecentActivitiesSection(activities: List<Activity>) {
    Column {
        Text(
            text = "最近活动",
            style = MiuixTypography.titleMedium,
            color = MiuixColor.TextPrimary,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        MiuixCard(
            shape = MiuixShape.medium,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                activities.forEachIndexed { index, activity ->
                    ActivityItem(activity = activity)
                    if (index < activities.lastIndex) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 56.dp)
                                .height(0.5.dp)
                                .background(MiuixColor.Divider)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ActivityItem(activity: Activity) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(MiuixShape.round)
                .background(activityTypeColor(activity.type)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = activityIcon(activity.type),
                contentDescription = null,
                tint = MiuixColor.TextOnPrimary,
                modifier = Modifier.size(18.dp)
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = activity.description,
                style = MiuixTypography.bodyMedium,
                color = MiuixColor.TextPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = activity.time,
                style = MiuixTypography.bodySmall,
                color = MiuixColor.TextTertiary
            )
        }
    }
}

private fun fileTypeColor(type: String) = when (type.lowercase()) {
    "folder" -> MiuixColor.FolderColor
    "doc", "docx", "pptx", "ppt", "xlsx", "xls" -> MiuixColor.DocColor
    "jpg", "jpeg", "png", "gif", "webp", "bmp" -> MiuixColor.ImageColor
    "mp4", "mov", "avi", "mkv" -> MiuixColor.VideoColor
    "mp3", "wav", "flac", "aac" -> MiuixColor.AudioColor
    "zip", "rar", "7z", "tar", "gz" -> MiuixColor.ZipColor
    "pdf" -> MiuixColor.PdfColor
    else -> MiuixColor.OtherColor
}

private fun activityTypeColor(type: String) = when (type.lowercase()) {
    "upload" -> MiuixColor.Success
    "download" -> MiuixColor.Info
    "share" -> MiuixColor.Warning
    "delete" -> MiuixColor.Error
    else -> MiuixColor.OtherColor
}

private fun activityIcon(type: String): ImageVector = when (type.lowercase()) {
    "upload" -> Icons.Default.CloudUpload
    "download" -> Icons.Default.CloudDownload
    "share" -> Icons.Default.Share
    "delete" -> Icons.Default.CloudDownload
    else -> Icons.Default.Folder
}

private fun FileItem.sizeDisplay(): String {
    val units = arrayOf("B", "KB", "MB", "GB", "TB")
    var size = this.size.toDouble()
    var unitIndex = 0
    while (size >= 1024 && unitIndex < units.lastIndex) {
        size /= 1024
        unitIndex++
    }
    return "%.1f %s".format(size, units[unitIndex])
}