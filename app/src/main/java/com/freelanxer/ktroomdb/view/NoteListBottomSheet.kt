package com.freelanxer.ktroomdb.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.freelanxer.ktroomdb.databinding.BottomSheetNoteListBinding
import com.freelanxer.ktroomdb.db.entity.NoteEntity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NoteListBottomSheet: BottomSheetDialogFragment() {
    private lateinit var mBinding: BottomSheetNoteListBinding
    private var mNote: NoteEntity? = null
    private var callback: ((note: NoteEntity) -> Unit)? = null

    companion object {
        val TAG = NoteListBottomSheet::class.simpleName
        const val EXTRA_NAME_NOTE = "EXTRA_NAME_NOTE"
        fun newInstance(note: NoteEntity?) = NoteListBottomSheet().apply {
            arguments = Bundle().also {
                it.putParcelable(EXTRA_NAME_NOTE, note)
                it.putParcelable("AA", note)
            }
        }
    }

    fun setCallback(callback: (note: NoteEntity) -> Unit) {
        this.callback = callback
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            mNote = arguments?.getParcelable(EXTRA_NAME_NOTE, NoteEntity::class.java)
        else
            mNote = arguments?.getParcelable(EXTRA_NAME_NOTE) as NoteEntity?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = BottomSheetNoteListBinding.inflate(inflater)
        mBinding.deleteLl.setOnClickListener {
            dismiss()
            mNote?.let {
                callback?.invoke(it)
            }
        }
        return mBinding.root
    }

}