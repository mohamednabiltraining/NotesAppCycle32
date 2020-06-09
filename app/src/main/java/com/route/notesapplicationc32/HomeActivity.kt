package com.route.notesapplicationc32

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
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
            intent.putExtra(Constants.EXTRA_TRANSACTION_TYPE,"1")
            startActivity(intent)
        })

        noteAdapter.onItemClick=object :NoteAdapter.OnItemClickListner{
            override fun onClick(note: Note?) {
                var intent=Intent(this@HomeActivity,AddnoteActivity::class.java)
                intent.putExtra(Constants.EXTRA_TRANSACTION_TYPE,"2")
                intent.putExtra(Constants.EXTR_NOTE_MODIFY,note)
                startActivity(intent)
            }
        }


        ItemTouchHelper( object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                db?.notesDao()?.deleteNote(noteAdapter.getNoteByPosition(viewHolder.adapterPosition))
                val dbb = NotesDataBase.getInstance(applicationContext)
                var notes: List<Note>? = dbb?.notesDao()?.getAllNotes()
                noteAdapter.changeData(notes)
            }
        }).attachToRecyclerView(home_note_rv)
    }

    override fun onStart() {
        super.onStart()
        val db = NotesDataBase.getInstance(applicationContext)
        var note: List<Note>? = db?.notesDao()?.getAllNotes()
        noteAdapter.changeData(note)
    }
}
