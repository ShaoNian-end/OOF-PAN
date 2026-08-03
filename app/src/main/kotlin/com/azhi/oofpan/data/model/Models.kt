package com.azhi.oofpan.data.model

data class User(
    val id: String,
    val name: String,
    val avatar: String,
    val vipLevel: String,
    val totalSpace: Long,
    val usedSpace: Long,
    val spacePercent: Int
)

data class FileItem(
    val id: String,
    val name: String,
    val type: String,
    val size: Long,
    val createTime: String,
    val updateTime: String,
    val parentId: String,
    val isStarred: Boolean,
    val isShared: Boolean,
    val shareLink: String? = null,
    val thumbnail: String? = null,
    val downloadUrl: String? = null,
    val children: List<FileItem>? = null,
    val path: String
)

data class Activity(
    val id: String,
    val type: String,
    val fileName: String,
    val fileId: String,
    val time: String,
    val description: String
)

data class SharedFile(
    val id: String,
    val fileId: String,
    val fileName: String,
    val shareLink: String,
    val createTime: String,
    val expireTime: String,
    val viewCount: Int,
    val downloadCount: Int
)

data class TrashItem(
    val id: String,
    val fileId: String,
    val name: String,
    val type: String,
    val size: Long,
    val deleteTime: String,
    val originalPath: String,
    val expireTime: String
)

data class CloudData(
    val user: User,
    val files: List<FileItem>,
    val recentActivities: List<Activity>,
    val sharedList: List<SharedFile>,
    val trash: List<TrashItem>
)