package com.freelanxer.ktroomdb.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.freelanxer.ktroomdb.db.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("Select * from table_note")
    fun queryAllNotes(): Flow<List<NoteEntity>?>

    @Query("Select * from table_note TN where TN.noteId = :noteId")
    fun queryNoteById(noteId: Int): Flow<NoteEntity?>

    @Insert
    suspend fun insertNotes(vararg notes: NoteEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)
}