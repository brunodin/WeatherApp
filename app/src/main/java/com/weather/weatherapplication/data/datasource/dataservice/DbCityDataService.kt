package com.weather.weatherapplication.data.datasource.dataservice

import com.weather.weatherapplication.data.model.DbCity

interface DbCityDataService {
    suspend fun save(model: DbCity): Long
    suspend fun getAllCity(city: String): List<DbCity>
}