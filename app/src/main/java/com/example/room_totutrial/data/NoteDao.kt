package com.example.room_totutrial.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteSingleNote(note: Note)

    @Query("DELETE FROM note_table ")
    suspend fun deleteAllNote()

    @Query("Select * From note_table Order By id ASC")
    fun readNotes(): LiveData<List<Note>>
}