package com.route.notesapplicationc32

import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.route.notesapplicationc32.database.Note
import com.route.notesapplicationc32.database.NotesDataBase
import kotlinx.android.synthetic.main.activity_insert.*
import java.util.*

class InsertEditActivity : AppCompatActivity() {
    lateinit var adapter: NoteAdapter;

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)


        val db = NotesDataBase.getInstance(applicationContext);
        val format = SimpleDateFormat("hh:mma dd-MM-yyyy")
        val currentTime = format.format(Date())

        var opreation = intent.getStringExtra(Constant.EXTRA_OPERATION)
        if (opreation == "update") {

            var note = intent.getSerializableExtra(Constant.EXTRA_NOTE) as Note
            title_input.setText(note.title)
            disc_input.setText(note.desc)
            var dt = note.date

            btn_insert.text = "UPDATE"

            btn_insert.setOnClickListener(View.OnClickListener {

                note?.title = title_input.text.toString()
                note?.desc = disc_input.text.toString()
                db?.notesDao()?.updateNote(note)

                Toast.makeText(this, "Note Updated Successfully", Toast.LENGTH_LONG).show();
                finish();
            })
        }


        if (opreation == "add") {
            btn_insert.text = "ADD";
            btn_insert.setOnClickListener(View.OnClickListener {

                db?.notesDao()?.insertNote(

                    Note(
                        title = title_input.text.toString(), desc = disc_input.text.toString(),
                        date = currentTime.toString()
                    )
                )

                Toast.makeText(this, "Note Added Successfully", Toast.LENGTH_LONG).show();
                finish();

            })
        }
    }
}




