package com.freelanxer.ktroomdb.db.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.freelanxer.ktroomdb.extension.millisToDefaultDateTimeFormat

@Entity(tableName = "table_note")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    var noteId: Int = 0,

    @ColumnInfo(name = "editor_name")
    var editorName: String? = null,

    @ColumnInfo(name = "subject")
    var subject: String? = null,

    @ColumnInfo(name = "content")
    var content: String? = null,

    @ColumnInfo(name = "update_time")
    var updateTime: Long? = System.currentTimeMillis(),
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Long::class.java.classLoader) as? Long
    ) {
    }

    fun formattedUpdateTime(): String? {
        return updateTime?.millisToDefaultDateTimeFormat()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(noteId)
        parcel.writeString(editorName)
        parcel.writeString(subject)
        parcel.writeString(content)
        parcel.writeValue(updateTime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NoteEntity> {
        override fun createFromParcel(parcel: Parcel): NoteEntity {
            return NoteEntity(parcel)
        }

        override fun newArray(size: Int): Array<NoteEntity?> {
            return arrayOfNulls(size)
        }
    }
}