package com.freelanxer.ktroomdb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.freelanxer.ktroomdb.databinding.ListItemNoteBinding
import com.freelanxer.ktroomdb.db.entity.NoteEntity

class NoteListAdapter(
    private val clickedHandler: (NoteEntity?) -> Unit,
    private val longClickedHandler: (NoteEntity?) -> Unit,
): RecyclerView.Adapter<NoteListAdapter.ViewHolder>() {
    private var mData: List<NoteEntity>? = null

    fun setData(list: List<NoteEntity>?) {
        mData = list ?: mutableListOf()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ListItemNoteBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = mData?.get(position)
        holder.itemView.setOnClickListener {
            clickedHandler.invoke(note)
        }
        holder.itemView.setOnLongClickListener {
            longClickedHandler.invoke(note)
            true
        }
        holder.bind(note)
    }

    override fun getItemCount(): Int  = mData?.size ?: 0

    inner class ViewHolder(
        private val binding: ListItemNoteBinding
    ): RecyclerView.ViewHolder(binding.root)
    {
        fun bind(note: NoteEntity?) {
            binding.note = note
            binding.executePendingBindings()
        }
    }
}