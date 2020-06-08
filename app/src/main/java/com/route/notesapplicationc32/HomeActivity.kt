package com.route.notesapplicationc32

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.route.notesapplicationc32.adapter.NoteAdapter
import com.route.notesapplicationc32.database.Note
import com.route.notesapplicationc32.database.NotesDataBase
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    lateinit var noteAdapter: NoteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        val db = NotesDataBase.getInstance(applicationContext)


        var note: List<Note>? = db?.notesDao()?.getAllNotes()
        noteAdapter = NoteAdapter(note)
        home_note_rv.adapter = noteAdapter

        home_add_btn.setOnClickListener(View.OnClickListener {
            var intent = Intent(this, AddnoteActivity::class.java)
            startActivity(intent)
        })





        //Todo: 1- list all notes from db in home Activity
        //Todo :2- create new activity called add note to insert new note to room db
        // Todo: plus points -> swipe to delete note from list
        // Todo : plus point -> click on note will open update note activity


    }

    override fun onStart() {
        super.onStart()
        val db = NotesDataBase.getInstance(applicationContext)
        var note: List<Note>? = db?.notesDao()?.getAllNotes()
        noteAdapter.changeData(note)
    }
}
