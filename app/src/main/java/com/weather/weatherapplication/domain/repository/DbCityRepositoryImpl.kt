package com.weather.weatherapplication.domain.repository

import com.weather.weatherapplication.data.datasource.dataservice.DbCityDataService
import com.weather.weatherapplication.data.model.DbCity
import javax.inject.Inject

class DbCityRepositoryImpl @Inject constructor(
    private val dataService: DbCityDataService
): DbCityRepository {
    override suspend fun save(model: DbCity): Long {
        return dataService.save(model)
    }

    override suspend fun getAllCity(city: String): List<DbCity> {
        return dataService.getAllCity(city)
    }

}