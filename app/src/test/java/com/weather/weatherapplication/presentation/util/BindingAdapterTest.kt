package com.weather.weatherapplication.presentation.util

import com.weather.weatherapplication.R
import org.junit.Assert.assertEquals
import org.junit.Test

class BindingAdapterTest {

    @Test
    fun imageIcon_codeGiven_returnsCorrectResult() {
        val icon = BindingAdapter.getIconFromCode("t01d")
        assertEquals(R.drawable.t01d, icon)
    }

    @Test
    fun imageIcon_codeGiven_returnsZeroResult() {
        val icon = BindingAdapter.getIconFromCode("5645")
        assertEquals(0, icon)
    }
}