package com.vickikbt.kyoskinterview.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ExtensionsTest {

    @Test
    fun getRating_returnsCorrect() {
        val ratingString = "6.6"
        val rating = ratingString.getRating()

        assertThat(rating).isEqualTo(3.3)
        assertThat(rating).isNotEqualTo(ratingString)
    }

}