package com.freelanxer.ktroomdb.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.freelanxer.ktroomdb.db.entity.NoteEntity

interface NoteDao {
    @Insert
    fun insertNotes(vararg notes: NoteEntity)

    @Delete
    fun deleteNote(note: NoteEntity)

    @Query("Select * from table_note")
    fun getAllNotes(): List<NoteEntity>

    @Query("Select * from table_note TN where TN.noteId = noteId")
    fun getNoteById(noteId: String)
}