package com.route.notesapplicationc32.database

import androidx.room.*


/**
 * Created by Mohamed Nabil Mohamed on 6/6/2020.
 * m.nabil.fci2015@gmail.com
 */

@Dao
interface NotesDao {

    @Insert
    fun insertNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Query("delete from Note where id=:id")
    fun deleteNoteById(id: Int)

    @Query("select * from note")
    fun getAllNotes(): List<Note>

}