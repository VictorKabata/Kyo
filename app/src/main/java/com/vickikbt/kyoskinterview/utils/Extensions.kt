package com.vickikbt.kyoskinterview.utils

import kotlin.math.roundToInt

fun String.getRating(): Int {
    val rating = this.toDouble() / 2
    return rating.roundToInt()
}