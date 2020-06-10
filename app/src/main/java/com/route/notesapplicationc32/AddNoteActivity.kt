package com.route.notesapplicationc32

import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import com.route.notesapplicationc32.Base.BaseActivity
import com.route.notesapplicationc32.database.Note
import com.route.notesapplicationc32.database.NotesDataBase
import kotlinx.android.synthetic.main.activity_add_note.*
import java.util.*

class AddNoteActivity : BaseActivity() {

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
            showMessage(title = null, message = "Note added", posActionString = "ok"
                , posAction = DialogInterface.OnClickListener { dialogInterface, i ->
                    dialogInterface.dismiss()
                    finish()

                }, isCancelable = false
            )
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
            showMessage("error", "please select note time",

                "ok", DialogInterface.OnClickListener { dialogInterface, i ->
                    {
                        dialogInterface.dismiss()
                    }
                });
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
