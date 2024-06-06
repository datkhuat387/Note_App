package com.example.note_app.note

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.note_app.R
import com.example.note_app.databinding.ActivityAddNoteBinding
import com.example.note_app.model.Note

class AddNoteActivity : AppCompatActivity(), NoteView {
    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var noteController: NoteController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteController = NoteController(this,this)
        binding.tvSave.setOnClickListener {
            val title = binding.edtTitle.text.toString()
            val content = binding.edtContent.text.toString()
            if(title.isEmpty()){
                Toast.makeText(this,"Tiêu đề không được bỏ trống",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val note = Note(title = title,content = content)
            noteController.addNote(note)
            finish()
        }
        binding.imgBack.setOnClickListener {
            finish()
        }
    }

    override fun displayNotes(notes: List<Note>) {}
}