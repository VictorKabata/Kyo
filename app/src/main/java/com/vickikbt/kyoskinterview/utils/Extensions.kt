package com.vickikbt.kyoskinterview.utils

import kotlin.math.roundToInt

fun String.getRating(): Double {
    val rating = this.toDouble() / 2
    return rating.toDouble()
}