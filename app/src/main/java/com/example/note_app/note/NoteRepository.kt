package com.example.note_app.note

import android.content.Context
import com.example.note_app.dao.NoteDao
import com.example.note_app.db.NoteDatabase
import com.example.note_app.model.Note

class NoteRepository(context: Context) {
    private val noteDao: NoteDao

    init {
        val database = NoteDatabase.getInstance(context)
        noteDao = database.noteDao()
    }

    suspend fun getAllNotes(): List<Note> {
        return noteDao.getAllNotes()
    }

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    suspend fun update(note: Note) {
        noteDao.update(note)
    }

    suspend fun delete(note: Note) {
        noteDao.delete(note)
    }
    suspend fun getNoteById(noteId: Int): Note? {
        return noteDao.getNoteById(noteId)
    }
}