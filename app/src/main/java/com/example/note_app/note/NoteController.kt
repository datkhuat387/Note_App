package com.example.note_app.note

import android.content.Context
import com.example.note_app.model.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteController(private val context: Context, private val view: NoteView) {
    private val noteRepository = NoteRepository(context)

    fun getAllNotes(){
        CoroutineScope(Dispatchers.IO).launch {
            val notes = noteRepository.getAllNotes()
            view.displayNotes(notes)
        }
    }
    fun addNote(note: Note) {
        CoroutineScope(Dispatchers.IO).launch {
            noteRepository.insert(note)
            getAllNotes()
        }
    }

    fun updateNote(note: Note) {
        CoroutineScope(Dispatchers.IO).launch {
            noteRepository.update(note)
            getAllNotes()
        }
    }

    fun deleteNote(note: Note) {
        CoroutineScope(Dispatchers.IO).launch {
            noteRepository.delete(note)
            getAllNotes()
        }
    }
    fun getNoteById(id: Int, callback: (Note?,String?) ->Unit){
        CoroutineScope(Dispatchers.IO).launch {
            val note = noteRepository.getNoteById(id)
            if(note!=null){
                callback(note,null)
            }else{
                callback(null,"Lỗi")
            }

        }
    }
}