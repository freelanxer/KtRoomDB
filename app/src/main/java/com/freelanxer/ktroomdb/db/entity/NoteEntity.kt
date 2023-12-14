package com.freelanxer.ktroomdb.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_note")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    var noteId: Int = 0,

    @ColumnInfo(name = "editor_name")
    var editorName: String? = null,

    @ColumnInfo(name = "subjection")
    var subjection: String? = null,

    @ColumnInfo(name = "content")
    var content: String? = null,
)
