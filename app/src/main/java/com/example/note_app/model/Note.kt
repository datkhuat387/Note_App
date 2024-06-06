package com.example.note_app.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String? = null,
    var content: String? = null
)
