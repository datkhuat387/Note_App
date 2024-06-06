package com.example.note_app.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.note_app.R
import com.example.note_app.databinding.ItemNoteBinding
import com.example.note_app.model.Note

class NoteAdapter(var notes: List<Note>) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private var noteListener: NoteListener? = null
    private lateinit var context: Context

    inner class NoteViewHolder(val binding: ItemNoteBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        context = parent.context
        return NoteViewHolder(ItemNoteBinding.inflate(LayoutInflater.from(parent.context)))
    }
    fun setListener(noteListener: NoteListener){
        this.noteListener = noteListener
    }
    override fun getItemCount(): Int {
        return notes.size
    }
    interface NoteListener{
        fun onClickNote(note: Note)
        fun onClickDeleteNote(note: Note)
        fun onConfirmDeleteNote(note: Note)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        if(notes.isNotEmpty()){
            val item = notes[position]

            holder.itemView.setOnClickListener {
                noteListener?.onClickNote(item)
            }
            holder.binding.tvDelete.setOnClickListener {
                showDeleteConfirmationDialog(item)
            }
            holder.binding.apply {
                tvTitle.text = item.title
                tvContent.text = item.content
            }
        }
    }
    private fun showDeleteConfirmationDialog(note: Note) {
        val dialog = AlertDialog.Builder(context)
            .setTitle("Xóa ghi chú")
            .setMessage("Bạn chắc chắn muốn xóa ghi chú?")
            .setPositiveButton("Xóa") { _, _ ->
                noteListener?.onConfirmDeleteNote(note)
            }
            .setNegativeButton("Hủy", null)
            .create()
        dialog.setOnShowListener {
            val positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            positiveButton.setTextColor(ContextCompat.getColor(context, R.color.red))
        }
        dialog.show()
    }
}