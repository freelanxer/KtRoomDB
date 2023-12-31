package com.freelanxer.ktroomdb.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.freelanxer.ktroomdb.NoteApplication
import com.freelanxer.ktroomdb.R
import com.freelanxer.ktroomdb.adapter.NoteListAdapter
import com.freelanxer.ktroomdb.databinding.ActivityMainBinding
import com.freelanxer.ktroomdb.db.entity.NoteEntity
import com.freelanxer.ktroomdb.db.repository.NoteRepository
import com.freelanxer.ktroomdb.view.NoteListBottomSheet
import com.freelanxer.ktroomdb.viewmodel.NoteViewModel
import com.freelanxer.ktroomdb.viewmodel.NoteViewModelRepository
import kotlinx.coroutines.launch

class MainActivity : BaseActivity() {
    private val mBinding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
    private val mNoteAdapter by lazy {
        NoteListAdapter(this::onNoteClicked, this::onNoteLongClicked)
    }

    private val viewModel: NoteViewModel by lazy {
        ViewModelProvider(this, NoteViewModelRepository(NoteRepository((application as NoteApplication).database.NoteDao())))[NoteViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.allNote.observe(this) {
            onReceiveNotes(it)
        }

        mBinding.addBtn.setOnClickListener(this)
        mBinding.swiperLayout.setOnRefreshListener {
            mBinding.swiperLayout.isRefreshing = false
            queryAllNotes()
        }

        mBinding.noteListRv.layoutManager = LinearLayoutManager(this)
        mBinding.noteListRv.setHasFixedSize(true)
        mBinding.noteListRv.adapter = mNoteAdapter
    }

    private fun queryAllNotes() {
        lifecycle.coroutineScope.launch {
            viewModel.queryAllNotes().collect() {
                onReceiveNotes(it)
            }
        }
    }

    private fun deleteNote(note: NoteEntity) {
        lifecycle.coroutineScope.launch {
            viewModel.deleteNote(note)
        }
    }

    private fun showBottomSheet(note: NoteEntity) {
        val sheet = NoteListBottomSheet.newInstance(note)
        sheet.setCallback {noteToDelete ->
            deleteNote(noteToDelete)
        }
        sheet.show(supportFragmentManager, NoteListBottomSheet.TAG)
    }

    private fun onReceiveNotes(noteList: List<NoteEntity>?) {
        mNoteAdapter.setData(noteList)
    }

    private fun onNoteClicked(note: NoteEntity?) {
        launchNewNote(note?.noteId)
    }

    private fun onNoteLongClicked(note: NoteEntity?) {
        note?.let {
            showBottomSheet(it)
        }
    }

    private fun launchNewNote(noteId: Int?) {
        val intent = Intent(this, NewNoteActivity::class.java).apply {
            putExtra(NewNoteActivity.EXTRA_NAME_NOTE_ID, noteId)
        }
        newNoteLauncher.launch(intent)
    }

    private val newNoteLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result == null)
            return@registerForActivityResult
        queryAllNotes()
    }

    override fun onClick(view: View?) {
        super.onClick(view)
        when (view) {
            mBinding.addBtn -> {
                launchNewNote(null)
            }
        }
    }

}