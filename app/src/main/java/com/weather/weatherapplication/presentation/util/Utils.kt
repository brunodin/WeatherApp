package com.weather.weatherapplication.presentation.util

import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun setDecimalScaleInDouble(value: Double, scale: Int = 1): Double {
    return BigDecimal(value)
        .setScale(scale, RoundingMode.HALF_EVEN)
        .toDouble()
}
fun getLocalDate(date: String): String {
   return LocalDateTime
        .parse(
            date,
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        ).format(DateTimeFormatter.ofPattern("HH:mm - dd/MM/yyyy"))
}