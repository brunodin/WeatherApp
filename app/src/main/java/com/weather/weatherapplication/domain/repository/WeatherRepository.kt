package com.weather.weatherapplication.domain.repository

import com.weather.weatherapplication.data.model.searchcity.WeatherCityModel
import com.weather.weatherapplication.domain.util.Resource

interface WeatherRepository {
    suspend fun getCityWeather(city: String, country: String): Resource<WeatherCityModel>
}