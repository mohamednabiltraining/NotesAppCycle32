package com.route.notesapplicationc32.Base

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity


/**
 * Created by Mohamed Nabil Mohamed on 6/10/2020.
 * m.nabil.fci2015@gmail.com
 */
open class BaseActivity : AppCompatActivity() {
    fun showMessage(
        title: String?, message: String?,
        posActionString: String? = null,
        posAction: DialogInterface.OnClickListener? = null,
        negActionString: String? = null,
        negAction: DialogInterface.OnClickListener? = null,
        isCancelable: Boolean = true
    ) {

        val builder = AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(posActionString, posAction)
            .setNegativeButton(negActionString, negAction)
            .setCancelable(isCancelable)
        builder.show()
    }

    fun showMessage(
        title: Int? = null, message: Int? = null,
        posActionString: Int? = null,
        posAction: DialogInterface.OnClickListener? = null,
        negActionString: Int? = null,
        negAction: DialogInterface.OnClickListener? = null,
        isCancelable: Boolean = true
    ) {

        val builder = AlertDialog.Builder(this);
        if (title != null)
            builder.setTitle(title)
        if (message != null)
            builder.setMessage(message)
        if (posActionString != null)
            builder.setPositiveButton(posActionString, posAction)
        if (negActionString != null)
            builder.setNegativeButton(negActionString, negAction)

        builder.setCancelable(isCancelable)

        builder.show()
    }
}