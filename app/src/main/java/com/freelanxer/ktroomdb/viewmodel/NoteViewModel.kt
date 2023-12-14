package com.freelanxer.ktroomdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.freelanxer.ktroomdb.db.entity.NoteEntity
import com.freelanxer.ktroomdb.db.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NoteRepository): ViewModel() {
    val allNote: LiveData<List<NoteEntity>?> = repository.allNote.asLiveData()

    fun queryAllNotes(): Flow<List<NoteEntity>?> = repository.allNote

    fun queryNoteById(noteId: Int): Flow<NoteEntity?> = repository.queryById(noteId)

    fun insertNote(note: NoteEntity) = viewModelScope.launch {
        repository.insertNote(note)
    }

    fun updateNote(note: NoteEntity) = viewModelScope.launch {
        repository.updateNote(note)
    }

    fun deleteNote(note: NoteEntity) = viewModelScope.launch {
        repository.deleteNote(note)
    }

}

class NoteViewModelRepository(private val repository: NoteRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}