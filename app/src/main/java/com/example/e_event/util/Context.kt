package com.example.e_event.util

import android.app.AlertDialog
import android.content.Context
import android.widget.EditText
import android.widget.FrameLayout

fun Context.showAlert (title: String, message: String, setup: (AlertDialog.Builder.() -> Unit)? = null) {
    val alert = AlertDialog.Builder(this)
    alert.setTitle(title)
    alert.setMessage(message)
    setup?.invoke(alert)
    alert.show()
}