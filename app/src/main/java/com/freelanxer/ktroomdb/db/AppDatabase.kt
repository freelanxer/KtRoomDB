package com.freelanxer.ktroomdb.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.freelanxer.ktroomdb.db.dao.NoteDao
import com.freelanxer.ktroomdb.db.entity.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun NoteDao(): NoteDao
}