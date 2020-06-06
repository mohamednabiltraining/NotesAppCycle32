package com.route.notesapplicationc32.database

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


/**
 * Created by Mohamed Nabil Mohamed on 6/6/2020.
 * m.nabil.fci2015@gmail.com
 */
@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var title: String? = null,
    var desc: String? = null,
    var date: String? = null,
    @Ignore
    var metaData: String? = null
)