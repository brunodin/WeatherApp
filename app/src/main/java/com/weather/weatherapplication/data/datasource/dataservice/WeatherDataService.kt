package com.weather.weatherapplication.data.datasource.dataservice

import com.weather.weatherapplication.data.model.searchcity.WeatherCityModel
import retrofit2.Response

interface WeatherDataService {
    suspend fun getCityWeather(city: String, country: String): Response<WeatherCityModel>?
}