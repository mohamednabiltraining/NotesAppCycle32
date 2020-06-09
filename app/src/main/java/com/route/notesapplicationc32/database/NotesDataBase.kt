package com.route.notesapplicationc32.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


/**
 * Created by Mohamed Nabil Mohamed on 6/6/2020.
 * m.nabil.fci2015@gmail.com
 */

@Database(entities = arrayOf(Note::class), version = 1)
abstract class NotesDataBase : RoomDatabase() {
    abstract fun notesDao(): NotesDao

    companion object {
        val DB_NAME = "Notes-DB"
        private var notesDataBase: NotesDataBase? = null //single object

        fun getInstance(context: Context): NotesDataBase? {

            if (notesDataBase == null) {
                //create object
                notesDataBase = Room.databaseBuilder(
                    context, NotesDataBase::class.java, DB_NAME
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return notesDataBase
        }

    }

}