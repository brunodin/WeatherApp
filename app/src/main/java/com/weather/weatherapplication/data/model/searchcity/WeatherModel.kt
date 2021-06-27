package com.weather.weatherapplication.data.model.searchcity


import com.google.gson.annotations.SerializedName

data class WeatherModel(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("icon")
    val icon: String?
)