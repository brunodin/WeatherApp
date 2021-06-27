package com.weather.weatherapplication.data.datasource.dataserviceimpl

import com.weather.weatherapplication.BuildConfig
import com.weather.weatherapplication.data.datasource.dataservice.WeatherDataService
import com.weather.weatherapplication.data.model.searchcity.WeatherCityModel
import com.weather.weatherapplication.data.service.WeatherService
import retrofit2.Response

class WeatherDataServiceImpl(
    private val service: WeatherService
): WeatherDataService {
    override suspend fun getCityWeather(city: String, country: String): Response<WeatherCityModel>? {
        return service.getWeatherByCity(city, country, BuildConfig.WEATHER_KEY)
    }
}