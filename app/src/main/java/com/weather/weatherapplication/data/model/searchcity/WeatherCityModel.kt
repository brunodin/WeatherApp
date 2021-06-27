package com.weather.weatherapplication.data.model.searchcity


import com.google.gson.annotations.SerializedName

data class WeatherCityModel(
    @SerializedName("data")
    val `data`: List<DataModel>?
)