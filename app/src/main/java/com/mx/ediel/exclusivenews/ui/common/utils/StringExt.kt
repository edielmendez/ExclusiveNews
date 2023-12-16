package com.mx.ediel.exclusivenews.ui.common.utils

import android.util.Log
import android.util.Patterns
import java.util.regex.Pattern

fun String.emailValid() = Patterns.EMAIL_ADDRESS.matcher(this).matches()
fun String.passwordValid(): Boolean{
    val regex = "^(?=(?:.*[A-Za-z]){5})(?=(?:.*\\d){2}).{7,}\$".toRegex()
    return regex.matches(this)
}
