package com.weather.weatherapplication.domain.repository

import com.weather.weatherapplication.data.datasource.dataservice.WeatherDataService
import com.weather.weatherapplication.data.model.searchcity.WeatherCityModel
import com.weather.weatherapplication.domain.util.Resource
import com.weather.weatherapplication.domain.util.ResponseToResource

class WeatherRepositoryImpl(
    private val dataService: WeatherDataService
) : WeatherRepository {
    override suspend fun getCityWeather(
        city: String,
        country: String
    ): Resource<WeatherCityModel> {
        return ResponseToResource
            .responseToResource(
                dataService.getCityWeather(city, country)
            )
    }

}