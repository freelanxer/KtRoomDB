package com.freelanxer.ktroomdb.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.freelanxer.ktroomdb.db.dao.NoteDao
import com.freelanxer.ktroomdb.db.entity.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    companion object {
        private const val databaseName = "app_database"
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    databaseName)
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }

    abstract fun NoteDao(): NoteDao
}