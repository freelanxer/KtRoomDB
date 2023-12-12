package com.freelanxer.ktroomdb.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteEntity(
    @PrimaryKey
    val noteId: String?,

    @ColumnInfo(name = "editor_name")
    val editorName: String?,

    @ColumnInfo(name = "subjection")
    val subjection: String?,

    @ColumnInfo(name = "content")
    val content: String?,
)
