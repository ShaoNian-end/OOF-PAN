package com.azhi.oofpan.data

import com.azhi.oofpan.data.model.*

object MockData {
    val cloudData = CloudData(
        user = User(
            id = "user_001",
            name = "张三",
            avatar = "https://example.com/avatar/zhangsan.jpg",
            vipLevel = "gold",
            totalSpace = 10737418240,
            usedSpace = 3221225472,
            spacePercent = 30
        ),
        files = listOf(
            FileItem(
                id = "f_001",
                name = "工作报告-2026Q3.pptx",
                type = "pptx",
                size = 15728640,
                createTime = "2026-07-28T10:30:00Z",
                updateTime = "2026-08-01T16:20:00Z",
                parentId = "root",
                isStarred = true,
                isShared = false,
                thumbnail = "https://example.com/thumb/f_001.jpg",
                downloadUrl = "https://example.com/download/f_001",
                path = "/工作报告-2026Q3.pptx"
            ),
            FileItem(
                id = "f_002",
                name = "团队合影.jpg",
                type = "jpg",
                size = 5242880,
                createTime = "2026-07-25T09:00:00Z",
                updateTime = "2026-07-25T09:00:00Z",
                parentId = "root",
                isStarred = false,
                isShared = true,
                shareLink = "https://example.com/s/abc123",
                thumbnail = "https://example.com/thumb/f_002.jpg",
                downloadUrl = "https://example.com/download/f_002",
                path = "/团队合影.jpg"
            ),
            FileItem(
                id = "f_003",
                name = "项目文档",
                type = "folder",
                size = 0,
                createTime = "2026-07-20T14:00:00Z",
                updateTime = "2026-07-30T11:00:00Z",
                parentId = "root",
                isStarred = false,
                isShared = false,
                children = listOf(
                    FileItem(
                        id = "f_003_1",
                        name = "需求文档.docx",
                        type = "docx",
                        size = 2097152,
                        createTime = "2026-07-21T10:00:00Z",
                        updateTime = "2026-07-29T17:30:00Z",
                        parentId = "f_003",
                        isStarred = false,
                        isShared = false,
                        thumbnail = "https://example.com/thumb/f_003_1.jpg",
                        downloadUrl = "https://example.com/download/f_003_1",
                        path = "/项目文档/需求文档.docx"
                    ),
                    FileItem(
                        id = "f_003_2",
                        name = "UI设计稿.fig",
                        type = "fig",
                        size = 8388608,
                        createTime = "2026-07-22T15:00:00Z",
                        updateTime = "2026-07-28T09:00:00Z",
                        parentId = "f_003",
                        isStarred = true,
                        isShared = true,
                        shareLink = "https://example.com/s/def456",
                        thumbnail = "https://example.com/thumb/f_003_2.jpg",
                        downloadUrl = "https://example.com/download/f_003_2",
                        path = "/项目文档/UI设计稿.fig"
                    )
                ),
                path = "/项目文档"
            ),
            FileItem(
                id = "f_004",
                name = "个人简历-2026.pdf",
                type = "pdf",
                size = 1048576,
                createTime = "2026-07-15T08:30:00Z",
                updateTime = "2026-07-15T08:30:00Z",
                parentId = "root",
                isStarred = false,
                isShared = false,
                thumbnail = "https://example.com/thumb/f_004.jpg",
                downloadUrl = "https://example.com/download/f_004",
                path = "/个人简历-2026.pdf"
            ),
            FileItem(
                id = "f_005",
                name = "学习资料",
                type = "folder",
                size = 0,
                createTime = "2026-07-10T20:00:00Z",
                updateTime = "2026-07-31T18:00:00Z",
                parentId = "root",
                isStarred = false,
                isShared = false,
                children = emptyList(),
                path = "/学习资料"
            ),
            FileItem(
                id = "f_006",
                name = "音乐合集",
                type = "folder",
                size = 0,
                createTime = "2026-07-05T12:00:00Z",
                updateTime = "2026-07-30T20:00:00Z",
                parentId = "root",
                isStarred = false,
                isShared = false,
                children = listOf(
                    FileItem(
                        id = "f_006_1",
                        name = "周杰伦-晴天.mp3",
                        type = "mp3",
                        size = 9437184,
                        createTime = "2026-07-06T18:00:00Z",
                        updateTime = "2026-07-06T18:00:00Z",
                        parentId = "f_006",
                        isStarred = false,
                        isShared = false,
                        downloadUrl = "https://example.com/download/f_006_1",
                        path = "/音乐合集/周杰伦-晴天.mp3"
                    ),
                    FileItem(
                        id = "f_006_2",
                        name = "古典钢琴精选.zip",
                        type = "zip",
                        size = 15728640,
                        createTime = "2026-07-08T14:00:00Z",
                        updateTime = "2026-07-08T14:00:00Z",
                        parentId = "f_006",
                        isStarred = false,
                        isShared = false,
                        downloadUrl = "https://example.com/download/f_006_2",
                        path = "/音乐合集/古典钢琴精选.zip"
                    )
                ),
                path = "/音乐合集"
            ),
            FileItem(
                id = "f_007",
                name = "2026-07-31 会议记录.mov",
                type = "mov",
                size = 209715200,
                createTime = "2026-07-31T17:00:00Z",
                updateTime = "2026-07-31T17:00:00Z",
                parentId = "root",
                isStarred = false,
                isShared = false,
                thumbnail = "https://example.com/thumb/f_007.jpg",
                downloadUrl = "https://example.com/download/f_007",
                path = "/2026-07-31 会议记录.mov"
            )
        ),
        recentActivities = listOf(
            Activity(
                id = "act_001",
                type = "upload",
                fileName = "工作报告-2026Q3.pptx",
                fileId = "f_001",
                time = "2026-08-01T16:20:00Z",
                description = "你上传了 工作报告-2026Q3.pptx"
            ),
            Activity(
                id = "act_002",
                type = "download",
                fileName = "团队合影.jpg",
                fileId = "f_002",
                time = "2026-08-01T15:10:00Z",
                description = "你下载了 团队合影.jpg"
            ),
            Activity(
                id = "act_003",
                type = "share",
                fileName = "UI设计稿.fig",
                fileId = "f_003_2",
                time = "2026-07-30T19:00:00Z",
                description = "你分享了 UI设计稿.fig"
            ),
            Activity(
                id = "act_004",
                type = "delete",
                fileName = "旧版需求.txt",
                fileId = "f_old_001",
                time = "2026-07-30T10:00:00Z",
                description = "你删除了 旧版需求.txt"
            )
        ),
        sharedList = listOf(
            SharedFile(
                id = "share_001",
                fileId = "f_002",
                fileName = "团队合影.jpg",
                shareLink = "https://example.com/s/abc123",
                createTime = "2026-07-25T09:00:00Z",
                expireTime = "2026-09-25T09:00:00Z",
                viewCount = 45,
                downloadCount = 12
            ),
            SharedFile(
                id = "share_002",
                fileId = "f_003_2",
                fileName = "UI设计稿.fig",
                shareLink = "https://example.com/s/def456",
                createTime = "2026-07-28T09:00:00Z",
                expireTime = "2026-08-28T09:00:00Z",
                viewCount = 28,
                downloadCount = 6
            )
        ),
        trash = listOf(
            TrashItem(
                id = "trash_001",
                fileId = "f_old_001",
                name = "旧版需求.txt",
                type = "txt",
                size = 102400,
                deleteTime = "2026-07-30T10:00:00Z",
                originalPath = "/项目文档/旧版需求.txt",
                expireTime = "2026-08-30T10:00:00Z"
            )
        )
    )
}