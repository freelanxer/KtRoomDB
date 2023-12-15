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

    suspend fun insertNote(note: NoteEntity): Long {
        return noteDao.insertNotes(note)
    }

    suspend fun updateNote(note: NoteEntity) {
        noteDao.updateNote(note.apply {
            updateTime = System.currentTimeMillis()
        })
    }

    suspend fun deleteNote(note: NoteEntity) {
        noteDao.deleteNote(note)
    }
}