package com.azhi.oofpan.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.azhi.oofpan.data.model.Activity
import com.azhi.oofpan.data.model.FileItem
import com.azhi.oofpan.data.model.SharedFile
import com.azhi.oofpan.data.model.User
import com.azhi.oofpan.ui.theme.LocalMiuixColorScheme
import com.azhi.oofpan.ui.theme.MiuixColor
import com.azhi.oofpan.ui.theme.MiuixShape

/**
 * 格式化文件大小，返回 KB/MB/GB 字符串
 */
fun formatFileSize(bytes: Long): String {
    return when {
        bytes < 1024 -> "$bytes B"
        bytes < 1024 * 1024 -> "${bytes / 1024} KB"
        bytes < 1024 * 1024 * 1024 -> {
            val mb = bytes.toDouble() / (1024 * 1024)
            String.format("%.1f MB", mb)
        }
        else -> {
            val gb = bytes.toDouble() / (1024 * 1024 * 1024)
            String.format("%.1f GB", gb)
        }
    }
}

/**
 * 格式化 ISO 时间字符串，返回月/日 时:分
 */
fun formatTime(isoTime: String): String {
    return try {
        // 处理 ISO 格式如 "2026-08-01T16:20:00Z"
        val datePart = isoTime.substringBefore("T")
        val timePart = isoTime.substringAfter("T").substringBefore(":")
        val minutePart = isoTime.substringAfter(":").substringBefore(":")

        val month = datePart.substringAfter("-").substringBefore("-")
        val day = datePart.substringAfterLast("-")

        "${month}/${day} ${timePart}:${minutePart}"
    } catch (_: Exception) {
        isoTime
    }
}

/**
 * MIUI X 风格顶部栏
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiuixTopBar(
    title: String,
    onBackClick: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    modifier: Modifier = Modifier
) {
    val colors = LocalMiuixColorScheme.current

    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                color = colors.onBackground
            )
        },
        navigationIcon = {
            if (onBackClick != null) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "返回",
                        tint = colors.onBackground
                    )
                }
            }
        },
        actions = actions,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colors.surface,
            titleContentColor = colors.onBackground
        ),
        modifier = modifier
    )
}

/**
 * MIUI X 风格卡片，使用 16.dp 圆角和阴影
 */
@Composable
fun MiuixCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    shape: androidx.compose.ui.graphics.Shape = MiuixShape.large,
    content: @Composable () -> Unit
) {
    val colors = LocalMiuixColorScheme.current

    Card(
        modifier = modifier
            .then(
                if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier
            ),
        shape = shape,
        colors = CardDefaults.cardColors(containerColor = colors.surfaceContainer),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        content()
    }
}

/**
 * 根据文件类型返回对应的图标和颜色
 */
@Composable
fun MiuixFileTypeIcon(
    fileType: String,
    modifier: Modifier = Modifier,
    iconSize: Int = 40
) {
    val (icon: ImageVector, color: Color) = when (fileType.lowercase()) {
        "folder" -> Icons.Default.Folder to MiuixColor.FolderColor
        "docx", "doc" -> Icons.Default.Description to MiuixColor.DocColor
        "jpg", "jpeg", "png", "gif", "bmp", "webp" -> Icons.Default.Image to MiuixColor.ImageColor
        "mov", "mp4", "avi", "mkv", "wmv", "flv" -> Icons.Default.Videocam to MiuixColor.VideoColor
        "mp3", "wav", "flac", "aac", "ogg", "wma" -> Icons.Default.MusicNote to MiuixColor.AudioColor
        "zip", "rar", "7z", "tar", "gz" -> Icons.Default.FolderZip to MiuixColor.ZipColor
        "pdf" -> Icons.Default.PictureAsPdf to MiuixColor.PdfColor
        "pptx", "ppt" -> Icons.Default.Slideshow to Color(0xFFFFB74D)
        "fig" -> Icons.Default.Brush to MiuixColor.OtherColor
        else -> Icons.Default.InsertDriveFile to MiuixColor.OtherColor
    }

    Box(
        modifier = modifier
            .size(iconSize.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color.copy(alpha = 0.12f)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = fileType,
            tint = color,
            modifier = Modifier.size((iconSize * 0.55).dp)
        )
    }
}

/**
 * MIUI X 风格文件列表项，显示图标、名称、大小、日期和星标
 */
@Composable
fun MiuixFileItem(
    file: FileItem,
    modifier: Modifier = Modifier,
    onClick: ((FileItem) -> Unit)? = null,
    onStarClick: ((FileItem) -> Unit)? = null
) {
    val colors = LocalMiuixColorScheme.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(enabled = onClick != null) { onClick?.invoke(file) }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        MiuixFileTypeIcon(
            fileType = file.type,
            iconSize = 48
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = file.name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = colors.onBackground,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (file.type != "folder") {
                    Text(
                        text = formatFileSize(file.size),
                        style = MaterialTheme.typography.bodySmall,
                        color = colors.onSurfaceSecondary
                    )
                    Text(
                        text = " · ",
                        style = MaterialTheme.typography.bodySmall,
                        color = colors.onSurfaceSecondary
                    )
                }
                Text(
                    text = formatTime(file.updateTime),
                    style = MaterialTheme.typography.bodySmall,
                    color = colors.onSurfaceSecondary
                )
            }
        }

        if (onStarClick != null) {
            IconButton(
                onClick = { onStarClick(file) },
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    imageVector = if (file.isStarred) Icons.Default.Star else Icons.Default.StarBorder,
                    contentDescription = if (file.isStarred) "取消星标" else "标记星标",
                    tint = if (file.isStarred) MiuixColor.Warning else colors.onSurfaceContainerVariant,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

/**
 * MIUI X 风格存储空间使用条
 */
@Composable
fun MiuixSpaceBar(
    used: Long,
    total: Long,
    percent: Int,
    modifier: Modifier = Modifier
) {
    val colors = LocalMiuixColorScheme.current
    val percentFloat = if (total > 0) {
        (used.toFloat() / total.toFloat()).coerceIn(0f, 1f)
    } else {
        0f
    }

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "已使用 ${formatFileSize(used)}",
                style = MaterialTheme.typography.bodySmall,
                color = colors.onSurfaceSecondary
            )
            Text(
                text = "共 ${formatFileSize(total)}",
                style = MaterialTheme.typography.bodySmall,
                color = colors.onSurfaceSecondary
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(MiuixShape.round)
                .background(colors.surfaceVariant)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(percentFloat)
                    .clip(MiuixShape.round)
                    .background(colors.primary)
            )
        }
    }
}

/**
 * MIUI X 风格底部导航项（无椭圆背景）
 */
@Composable
fun MiuixBottomNavItem(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val colors = LocalMiuixColorScheme.current
    val iconTint = if (isSelected) colors.primary else colors.onSurfaceSecondary
    val textColor = if (isSelected) colors.primary else colors.onSurfaceSecondary

    Box(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = iconTint
            )
            Text(
                text = label,
                fontSize = 11.sp,
                fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal,
                color = textColor
            )
        }
    }
}

/**
 * MIUI X 风格圆形头像，包含占位图
 */
@Composable
fun MiuixAvatar(
    modifier: Modifier = Modifier,
    avatarUrl: String? = null,
    name: String? = null,
    size: Int = 48
) {
    val colors = LocalMiuixColorScheme.current

    Box(
        modifier = modifier
            .size(size.dp)
            .clip(CircleShape)
            .background(colors.primary.copy(alpha = 0.15f)),
        contentAlignment = Alignment.Center
    ) {
        if (name != null && name.isNotEmpty()) {
            Text(
                text = name.take(1),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = colors.primary
            )
        } else {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "头像",
                tint = colors.onSurfaceSecondary,
                modifier = Modifier.size((size * 0.5).dp)
            )
        }
    }
}

/**
 * MIUI X 风格 VIP 等级徽章
 */
@Composable
fun MiuixVipBadge(
    level: String,
    modifier: Modifier = Modifier
) {
    val colors = LocalMiuixColorScheme.current
    val (label, bgColor, textColor) = when (level.lowercase()) {
        "gold" -> Triple("黄金", MiuixColor.VipGold, Color.White)
        "silver" -> Triple("白银", MiuixColor.VipSilver, Color.White)
        "platinum" -> Triple("铂金", MiuixColor.VipPlatinum, colors.onBackground)
        else -> Triple(level, colors.surfaceVariant, colors.onSurfaceSecondary)
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(bgColor)
            .padding(horizontal = 6.dp, vertical = 2.dp)
    ) {
        Text(
            text = "VIP $label",
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium,
            color = textColor,
            lineHeight = 14.sp
        )
    }
}

/**
 * MIUI X 风格段落标题，包含可选的"更多"操作
 */
@Composable
fun MiuixSectionHeader(
    title: String,
    modifier: Modifier = Modifier,
    onMoreClick: (() -> Unit)? = null
) {
    val colors = LocalMiuixColorScheme.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = colors.onBackground
        )
        if (onMoreClick != null) {
            TextButton(
                onClick = onMoreClick,
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "更多",
                    style = MaterialTheme.typography.labelLarge,
                    color = colors.primary
                )
                Spacer(modifier = Modifier.width(2.dp))
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = "更多",
                    tint = colors.primary,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

/**
 * MIUI X 风格最近活动项
 */
@Composable
fun MiuixActivityItem(
    activity: Activity,
    modifier: Modifier = Modifier
) {
    val colors = LocalMiuixColorScheme.current
    val (icon: ImageVector, iconColor: Color) = when (activity.type.lowercase()) {
        "upload" -> Icons.Default.CloudUpload to colors.primary
        "download" -> Icons.Default.CloudDownload to MiuixColor.Success
        "share" -> Icons.Default.Share to MiuixColor.Warning
        "delete" -> Icons.Default.Delete to MiuixColor.Error
        else -> Icons.Default.Info to MiuixColor.Info
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(iconColor.copy(alpha = 0.12f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = activity.type,
                tint = iconColor,
                modifier = Modifier.size(18.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = activity.description,
                style = MaterialTheme.typography.bodyMedium,
                color = colors.onBackground,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = formatTime(activity.time),
                style = MaterialTheme.typography.bodySmall,
                color = colors.onSurfaceSecondary
            )
        }
    }
}