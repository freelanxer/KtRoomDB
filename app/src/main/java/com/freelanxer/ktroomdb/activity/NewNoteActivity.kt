package com.freelanxer.ktroomdb.activity

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import com.freelanxer.ktroomdb.NoteApplication
import com.freelanxer.ktroomdb.R
import com.freelanxer.ktroomdb.databinding.ActivityNewNoteBinding
import com.freelanxer.ktroomdb.db.entity.NoteEntity
import com.freelanxer.ktroomdb.db.repository.NoteRepository
import com.freelanxer.ktroomdb.extension.hideKeyboard
import com.freelanxer.ktroomdb.viewmodel.NoteViewModel
import com.freelanxer.ktroomdb.viewmodel.NoteViewModelRepository
import kotlinx.coroutines.launch

class NewNoteActivity: BaseActivity() {
    companion object {
        const val EXTRA_NAME_NOTE_ID = "EXTRA_NAME_NOTE_ID"
    }

    private lateinit var mBinding: ActivityNewNoteBinding

    private val viewModel: NoteViewModel by lazy {
        ViewModelProvider(this, NoteViewModelRepository(NoteRepository((application as NoteApplication).database.NoteDao())))[NoteViewModel::class.java]
    }

    private var mNoteId  = -1
    private var mNote: NoteEntity? = null

    private fun isNewNote() = mNoteId == -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNoteId = intent.getIntExtra(EXTRA_NAME_NOTE_ID, -1)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_new_note)

        mBinding.saveBtn.setOnClickListener(this)

        if (!isNewNote())
            queryNoteById(mNoteId)
    }

    private fun queryNoteById(notId: Int) {
        lifecycle.coroutineScope.launch {
            viewModel.queryNoteById(notId).collect() {
                mNote = it
                mBinding.note = mNote
            }
        }
    }

    private fun saveNote() {
        val noteToSave = mNote ?: NoteEntity()
        noteToSave.apply {
            subjection = mBinding.subjectionEt.text.toString()
            content = mBinding.contentEt.text.toString()
        }
        if (isNewNote()) {
            viewModel.insertNote(noteToSave).observe(this) { id ->
                noteToSave.noteId = id.toInt()
                mNote = noteToSave.also {
                    mNoteId = it.noteId
                }
                mBinding.note = mNote
            }
        } else {
            viewModel.updateNote(noteToSave)
            mBinding.note = mNote
        }
        showSnackBar(mBinding.root, R.string.snack_message_saved)
    }

    override fun onClick(view: View?) {
        super.onClick(view)
        when (view) {
            mBinding.saveBtn -> {
                hideKeyboard()
                saveNote()
            }
        }
    }

}