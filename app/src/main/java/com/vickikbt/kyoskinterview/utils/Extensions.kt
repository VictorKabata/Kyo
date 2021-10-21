package com.vickikbt.kyoskinterview.utils

import kotlin.math.roundToInt

fun getRating(ratingString:String): Int {
    val rating = ratingString.toDouble() / 2
    return rating.roundToInt()
}