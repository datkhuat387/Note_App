package com.example.note_app.note

import com.example.note_app.model.Note

interface NoteView {
    fun displayNotes(notes: List<Note>)
}