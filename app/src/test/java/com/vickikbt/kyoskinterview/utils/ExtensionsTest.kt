package com.vickikbt.kyoskinterview.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Assert
import org.junit.Test

class ExtensionsTest {

    @Test
    fun getRating_returnsCorrect() {
        val ratingString = "6.6"
        val rating = getRating(ratingString)

        assertThat(rating).isEqualTo(3.3)
        assertThat(rating).isNotEqualTo(ratingString)
    }

    @Test
    fun addition_isCorrect() {
        Assert.assertEquals(4, 2 + 2)
    }

}