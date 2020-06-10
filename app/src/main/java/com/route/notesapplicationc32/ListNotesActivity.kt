package com.route.notesapplicationc32

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.route.notesapplicationc32.Base.BaseActivity
import com.route.notesapplicationc32.adapter.NotesAdapter
import com.route.notesapplicationc32.database.Note
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

        val simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object :
            ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                //Remove swiped item from list and notify the RecyclerView
                val position = viewHolder.adapterPosition
                val noteToDelete = notesAdapter.removeItem(position)
                showSnackBarToDeleteItem(noteToDelete)
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recycler_view)
    }

    var undoClicked = false;
    private fun showSnackBarToDeleteItem(noteToDelete: Note) {
        val snackbar = Snackbar.make(recycler_view, "note deleted", 3000)
        snackbar.setAction("undo", {
            getNotesFromDB()
            undoClicked = true
        });
        snackbar.addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                super.onDismissed(transientBottomBar, event)
                if (!undoClicked) {
                    deleteNoteFromDB(noteToDelete);
                }
                undoClicked = false

            }
        })
        snackbar.show()
    }

    private fun deleteNoteFromDB(noteToDelete: Note) {
        NotesDataBase.getInstance(applicationContext)
            ?.notesDao()
            ?.deleteNote(noteToDelete);
    }

    fun getNotesFromDB() {
        val notes = NotesDataBase.getInstance(applicationContext)
            ?.notesDao()?.getAllNotes();
        notesAdapter.changeData(notes ?: listOf())
    }

    val notesAdapter = NotesAdapter();
    override fun onStart() {
        super.onStart()
        getNotesFromDB()
    }

}
