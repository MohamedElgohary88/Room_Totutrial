package com.example.room_totutrial.repository

import androidx.lifecycle.LiveData
import com.example.room_totutrial.data.Note
import com.example.room_totutrial.data.NoteDao

class NoteRepository(private val noteDao: NoteDao) {

    val readAllData: LiveData<List<Note>> = noteDao.readNotes()

    suspend fun addNotes(notes: Note) {
        noteDao.addNote(notes)
    }

    suspend fun updateNotes(notes: Note) {
        noteDao.updateNote(notes)
    }

    suspend fun deleteSingleNote(notes: Note){
        noteDao.deleteSingleNote(notes)
    }

    suspend fun deleteAllNotes(){
        noteDao.deleteAllNote()
    }
}
