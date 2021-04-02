package com.example.e_event.util

import android.text.TextUtils
import android.util.Patterns

//fun String.isValidEmail() = !TextUtils.isEmpty(this) &&
//        Patterns.EMAIL_ADDRESS.matcher(this).matches()

 fun isValidEmail(email: String): Boolean {
    return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun isValidName(name: String): Boolean {

    // Set error if required
//    if (updateUI) {
//        val error: String? = if (valid) null else NAME_VALIDATION_MSG
//        setError(data, error)
//    }

    return name.trim().length > 2
}