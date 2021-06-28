package com.weather.weatherapplication.data.service

import com.weather.weatherapplication.data.constants.Weather
import com.weather.weatherapplication.data.model.searchcity.WeatherCityModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET(Weather.WEATHER_CURRENT)
    suspend fun getWeatherByCity(
        @Query("city") city: String,
        @Query("country") country: String,
        @Query("key") key: String
    ): Response<WeatherCityModel>?
}