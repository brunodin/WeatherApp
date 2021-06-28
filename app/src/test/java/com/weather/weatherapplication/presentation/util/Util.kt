package com.weather.weatherapplication.presentation.util

import org.junit.Assert
import org.junit.Test

class Util {

    @Test
    fun getLocalDate_passingDate_Success() {
        val date = getLocalDate("2020-02-19 12:01")
        Assert.assertEquals("12:01 - 19/02/2020", date)
    }
}