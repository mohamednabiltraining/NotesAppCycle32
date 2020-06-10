package com.route.notesapplicationc32

import android.content.Intent
import android.os.Bundle
import com.route.notesapplicationc32.Base.BaseActivity
import com.route.notesapplicationc32.adapter.NotesAdapter
import com.route.notesapplicationc32.database.NotesDataBase
import kotlinx.android.synthetic.main.activity_list_notes.*
import kotlinx.android.synthetic.main.content_list_notes.*

class ListNotesActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_notes)
        setSupportActionBar(toolbar)
        recycler_view.adapter = notesAdapter;


        fab.setOnClickListener { view ->
            /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                 .setAction("Action", null).show()*/
            startActivity(Intent(this, AddNoteActivity::class.java))
        }
    }

    val notesAdapter = NotesAdapter();
    override fun onStart() {
        super.onStart()
        val notes = NotesDataBase.getInstance(applicationContext)
            ?.notesDao()?.getAllNotes();
        notesAdapter.changeData(notes ?: listOf())
    }

}
