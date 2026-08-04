package com.azhi.oofpan.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.azhi.oofpan.data.model.User
import com.azhi.oofpan.ui.components.MiuixAvatar
import com.azhi.oofpan.ui.components.MiuixCard
import com.azhi.oofpan.ui.components.MiuixSpaceBar
import com.azhi.oofpan.ui.components.MiuixTopBar
import com.azhi.oofpan.ui.components.MiuixVipBadge
import com.azhi.oofpan.ui.theme.LocalMiuixColorScheme
import com.azhi.oofpan.ui.theme.MiuixShape
import com.azhi.oofpan.ui.theme.MiuixTypography

@Composable
fun ProfileScreen(
    user: User,
    onSettingClick: () -> Unit,
    onAboutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = LocalMiuixColorScheme.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.background)
    ) {
        MiuixTopBar(title = "个人中心")

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            UserInfoCard(user = user)
            Spacer(modifier = Modifier.height(12.dp))
            MenuSection(
                onSettingClick = onSettingClick,
                onAboutClick = onAboutClick
            )
        }
    }
}

@Composable
private fun UserInfoCard(user: User) {
    val colors = LocalMiuixColorScheme.current

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
                modifier = Modifier.size(56.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = user.name,
                        style = MiuixTypography.titleLarge,
                        color = colors.onBackground
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    MiuixVipBadge(level = user.vipLevel)
                }
                Spacer(modifier = Modifier.height(12.dp))
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
private fun MenuSection(
    onSettingClick: () -> Unit,
    onAboutClick: () -> Unit
) {
    val colors = LocalMiuixColorScheme.current

    MiuixCard(
        shape = MiuixShape.medium,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            MenuItem(
                icon = Icons.Default.Star,
                label = "收藏夹",
                onClick = { /* TODO */ },
                colors = colors
            )
            MenuDivider(colors = colors)
            MenuItem(
                icon = Icons.Default.Delete,
                label = "回收站",
                onClick = { /* TODO */ },
                colors = colors
            )
            MenuDivider(colors = colors)
            MenuItem(
                icon = Icons.Default.SwapHoriz,
                label = "传输列表",
                onClick = { /* TODO */ },
                colors = colors
            )
            MenuDivider(colors = colors)
            MenuItem(
                icon = Icons.Default.Settings,
                label = "设置",
                onClick = onSettingClick,
                colors = colors
            )
            MenuDivider(colors = colors)
            MenuItem(
                icon = Icons.Default.Info,
                label = "关于",
                onClick = onAboutClick,
                colors = colors
            )
        }
    }
}

@Composable
private fun MenuItem(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit,
    colors: com.azhi.oofpan.ui.theme.MiuixColorScheme
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = colors.onSurfaceSecondary,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = label,
            style = MiuixTypography.bodyLarge,
            color = colors.onBackground,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            tint = colors.onSurfaceContainerVariant,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
private fun MenuDivider(colors: com.azhi.oofpan.ui.theme.MiuixColorScheme) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 60.dp)
            .height(0.5.dp)
            .background(colors.dividerLine)
    )
}