package com.freelanxer.ktroomdb.db.repository

import com.freelanxer.ktroomdb.db.dao.NoteDao
import com.freelanxer.ktroomdb.db.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao)
{
    val allNote: Flow<List<NoteEntity>?> = noteDao.queryAllNotes()

    fun queryById(noteId: Int): Flow<NoteEntity?> {
        return noteDao.queryNoteById(noteId)
    }

    suspend fun insert(note: NoteEntity) {
        noteDao.insertNotes(note)
    }

    suspend fun deleteNote(note: NoteEntity) {
        noteDao.deleteNote(note)
    }
}