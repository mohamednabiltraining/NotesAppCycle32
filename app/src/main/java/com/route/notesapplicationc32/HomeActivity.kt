package com.route.notesapplicationc32

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.route.notesapplicationc32.adapter.NoteAdapter
import com.route.notesapplicationc32.database.Note
import com.route.notesapplicationc32.database.NotesDataBase
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        val db = NotesDataBase.getInstance(applicationContext)

//        db?.notesDao()?.insertNote(Note(title="mahmoud moussa",desc="mahmoud moussa",date = "25/10"))
//        db?.notesDao()?.insertNote(Note(title="android",desc="mahmoud moussa",date = "25/10"))
//        db?.notesDao()?.insertNote(Note(title="ios",desc="mahmoud moussa",date = "25/10"))
//        db?.notesDao()?.insertNote(Note(title="full stack",desc="mahmoud moussa",date = "25/10"))

        var note: List<Note>? = db?.notesDao()?.getAllNotes()

        var noteAdapter = NoteAdapter()
        noteAdapter.changeData(note)
        home_note_rv.adapter = noteAdapter





        //Todo: 1- list all notes from db in home Activity
        //Todo :2- create new activity called add note to insert new note to room db
        // Todo: plus points -> swipe to delete note from list
        // Todo : plus point -> click on note will open update note activity


    }
}
