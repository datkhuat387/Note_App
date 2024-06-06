package com.example.note_app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.note_app.adapter.NoteAdapter
import com.example.note_app.dao.NoteDao
import com.example.note_app.databinding.ActivityMainBinding
import com.example.note_app.db.NoteDatabase
import com.example.note_app.model.Note
import com.example.note_app.note.AddNoteActivity
import com.example.note_app.note.DetailActivity
import com.example.note_app.note.NoteController
import com.example.note_app.note.NoteView

class MainActivity : AppCompatActivity(), NoteView {
    private lateinit var binding: ActivityMainBinding
    private lateinit var noteController: NoteController
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var noteDao: NoteDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteController = NoteController(this,this)
        noteAdapter = NoteAdapter(emptyList())

        noteAdapter.setListener(object : NoteAdapter.NoteListener{
            override fun onClickNote(note: Note) {
                val intent = Intent(this@MainActivity,DetailActivity::class.java)
                intent.putExtra("idNote",note.id)
                startActivity(intent)
            }

            override fun onClickDeleteNote(note: Note) {
//                noteController.deleteNote(note)
            }

            override fun onConfirmDeleteNote(note: Note) {
                noteController.deleteNote(note)
            }

        })
        noteDao = NoteDatabase.getInstance(this).noteDao()
        binding.rcvListNote.adapter = noteAdapter
        binding.imgAdd.setOnClickListener {
            val intent = Intent(this,AddNoteActivity::class.java)
            startActivity(intent)
        }
        noteController.getAllNotes()
    }

    override fun displayNotes(notes: List<Note>) {
        runOnUiThread {
            noteAdapter.notes = notes
            noteAdapter.notifyDataSetChanged()
        }
    }
    override fun onResume() {
        super.onResume()
        noteController.getAllNotes()
    }
}