package com.example.note_app.note

import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.note_app.R
import com.example.note_app.databinding.ActivityDetailBinding
import com.example.note_app.model.Note

class DetailActivity : AppCompatActivity(), NoteView {
    private lateinit var binding:ActivityDetailBinding
    private lateinit var noteController: NoteController
    private var idNote: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        noteController = NoteController(this,this)
        idNote = intent.getIntExtra("idNote",Int.MAX_VALUE)
        noteController.getNoteById(idNote!!){ note, error->
            if(note!=null){
                binding.edtTitle.text = Editable.Factory.getInstance().newEditable(note.title)
                binding.edtContent.text = Editable.Factory.getInstance().newEditable(note.content)
            }else{
                Toast.makeText(this@DetailActivity, "Đã xảy ra lỗi", Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvSave.setOnClickListener {
            val title = binding.edtTitle.text.toString()
            val content = binding.edtContent.text.toString()
            if(title.isEmpty()){
                Toast.makeText(this,"Tiêu đề không được bỏ trống", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val note = Note(id = idNote!!, title = title,content = content)
            noteController.updateNote(note)
            finish()
        }
        binding.imgBack.setOnClickListener {
            finish()
        }
    }

    override fun displayNotes(notes: List<Note>) {}
}