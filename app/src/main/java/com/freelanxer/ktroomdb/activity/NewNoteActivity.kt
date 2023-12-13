package com.freelanxer.ktroomdb.activity

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import com.freelanxer.ktroomdb.NoteApplication
import com.freelanxer.ktroomdb.R
import com.freelanxer.ktroomdb.databinding.ActivityNewNoteBinding
import com.freelanxer.ktroomdb.db.repository.NoteRepository
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNoteId = intent.getIntExtra(EXTRA_NAME_NOTE_ID, -1)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_new_note)

        queryNoteById(mNoteId)
    }

    private fun queryNoteById(notId: Int) {
        lifecycle.coroutineScope.launch {
            viewModel.queryNoteById(notId).collect() {
                mBinding.note = it
            }
        }
    }

    override fun onClick(view: View?) {
        super.onClick(view)
    }
}