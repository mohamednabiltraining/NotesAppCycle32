package com.route.notesapplicationc32

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.route.notesapplicationc32.database.Note
import com.route.notesapplicationc32.database.NotesDataBase
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    var adapter: NoteAdapter = NoteAdapter();


    override fun onResume() {
        super.onResume()
        val db = NotesDataBase.getInstance(applicationContext);
        val notes = db?.notesDao()?.getAllNotes();
        adapter.changeData(notes)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //this code fill recycle view
        val db = NotesDataBase.getInstance(applicationContext);
        val notes = db?.notesDao()?.getAllNotes();
        adapter.changeData(notes)
        recycler_view.adapter = adapter


        //this code transitioning to add note activity
        btn_add.setOnClickListener(View.OnClickListener {

            val intent = Intent(this, InsertEditActivity::class.java)
            intent.putExtra(Constant.EXTRA_OPERATION, "add")
            startActivity(intent)
        })


        adapter.onItemClickListener = object : NoteAdapter.OnItemClickListener {

            override fun onItemClick(note: Note?) {

                val intent = Intent(this@HomeActivity, InsertEditActivity::class.java)
                intent.putExtra(Constant.EXTRA_NOTE, note)
                intent.putExtra(Constant.EXTRA_OPERATION, "update")

                startActivity(intent);

            }


        }

        //this code that delete the item when you swipe it to the right or the left
        val helper = ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                db?.notesDao()?.deleteNote(adapter.getNote(position));

                val db = NotesDataBase.getInstance(applicationContext);
                val notes = db?.notesDao()?.getAllNotes();
                adapter.changeData(notes)


            }
        }).attachToRecyclerView(recycler_view)


    }
}


/*
        db?.notesDao()?.insertNote(
            Note(title="android assignment",desc = "read about singeleton pattern" +
                "read about migration")
        )
        db?.notesDao()?.deleteNoteById(1);
*/

//Todo: 1- list all notes from db in home Activity
//Todo :2- create new activity called add note to insert new note to room db
// Todo: plus points -> swipe to delete note from list
// Todo : plus point -> click on note will open update note activity





