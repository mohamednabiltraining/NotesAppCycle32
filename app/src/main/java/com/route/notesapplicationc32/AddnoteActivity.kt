package com.route.notesapplicationc32

import android.app.TimePickerDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.route.notesapplicationc32.database.Note
import com.route.notesapplicationc32.database.NotesDataBase
import kotlinx.android.synthetic.main.activity_addnote.*
import java.text.SimpleDateFormat
import java.util.*

class AddnoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addnote)

        val db = NotesDataBase.getInstance(applicationContext)
        add_addnote_btn.setOnClickListener(View.OnClickListener {

            var title = add_title_ed.text.toString()
            var desc = add_desc_ed.text.toString()
            var time = add_time_ed.text.toString()

            if (!TextUtils.isEmpty(title)) {
                var note = Note(title = title, desc = desc, date = time)
                db?.notesDao()?.insertNote(note)
                finish()
            }

        })

        add_time_ed.setOnClickListener(View.OnClickListener {
            val calendar = Calendar.getInstance()
            val timeSetListner = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE, minute)
                var t = SimpleDateFormat("HH:mm").format(calendar.time)
                add_time_ed.setText(t)
            }
            TimePickerDialog(
                this,
                timeSetListner,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            ).show()
        })

    }
}
