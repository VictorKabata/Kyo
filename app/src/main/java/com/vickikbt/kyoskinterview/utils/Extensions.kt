package com.vickikbt.kyoskinterview.utils

import android.content.Context
import android.widget.Toast

fun String.getRating(): Double {
    val rating = this.toDouble() / 2
    return rating.toDouble()
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}