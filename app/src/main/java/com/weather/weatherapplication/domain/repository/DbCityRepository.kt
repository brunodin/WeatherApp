package com.weather.weatherapplication.domain.repository

import com.weather.weatherapplication.data.model.DbCity

interface DbCityRepository {
    suspend fun save(model: DbCity): Long
    suspend fun getAllCity(city: String): List<DbCity>

}