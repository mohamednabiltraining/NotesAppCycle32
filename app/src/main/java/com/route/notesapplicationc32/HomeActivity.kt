package com.route.notesapplicationc32

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

/*
        val db = NotesDataBase.getInstance(applicationContext);
        val notes = db?.notesDao()?.getAllNotes();

        db?.notesDao()?.insertNote(Note(title="android assignment",desc = "read about singeleton pattern" +
                "read about migration"))
        db?.notesDao()?.deleteNoteById(1);

*/
        //Todo: 1- list all notes from db in home Activity
        //Todo :2- create new activity called add note to insert new note to room db
        // Todo: plus points -> swipe to delete note from list
        // Todo : plus point -> click on note will open update note activity


    }
}
