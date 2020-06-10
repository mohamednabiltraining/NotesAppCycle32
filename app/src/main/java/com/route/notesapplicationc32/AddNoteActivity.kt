package com.route.notesapplicationc32

import android.app.AlertDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.route.notesapplicationc32.database.Note
import com.route.notesapplicationc32.database.NotesDataBase
import kotlinx.android.synthetic.main.activity_add_note.*
import java.util.*

class AddNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        add_btn.setOnClickListener({
            addNote()
        })
        time.setOnClickListener({
            showTimeForNote()
        })
    }

    private fun addNote() {
        if (validData()) {
            //add note in db
            val note = Note(
                title = note_title.editText?.text.toString(),
                desc = desc.editText?.text.toString(),
                date = "" + hours + ":" + minutes
            )
            NotesDataBase.getInstance(applicationContext)
                ?.notesDao()?.insertNote(note)
            val builder = AlertDialog.Builder(this)
                .setMessage("Note added")
                .setPositiveButton("ok", DialogInterface.OnClickListener { dialogInterface, i ->
                    dialogInterface.dismiss()
                    finish()

                })
                .setCancelable(false)
            builder.show()
        }
    }

    private fun validData(): Boolean {
        var isValid = true

        if (note_title.editText?.text.isNullOrBlank()) {
            isValid = false
            note_title.error = "please enter note title"
        } else {
            note_title.error = null
        }
        if (desc.editText?.text.isNullOrBlank()) {
            isValid = false
            desc.error = "please enter note description"
        } else {
            desc.error = null
        }
        if (hours == null || minutes == null) {
            isValid = false
            val builder = AlertDialog.Builder(this)
                .setTitle("error")
                .setMessage("please select note time")
                .setPositiveButton("ok", DialogInterface.OnClickListener { dialogInterface, i ->
                    {
                        dialogInterface.dismiss()
                    }
                })
            builder.show()
        }
        return isValid
    }

    var hours: Int? = null
    var minutes: Int? = null

    private fun showTimeForNote() {
        val cal = Calendar.getInstance()
        val timePicker =
            TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { timePicker, hrs, mins ->
                //function
                hours = hrs
                minutes = mins
                time.text = "" + hours + ":" + mins
            }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false)

        timePicker.show()
    }
}
