package com.weather.weatherapplication.data.datasource.dataserviceimpl

import com.weather.weatherapplication.data.dao.CityDao
import com.weather.weatherapplication.data.datasource.dataservice.DbCityDataService
import com.weather.weatherapplication.data.model.DbCity
import javax.inject.Inject

class DbCityDataServiceImpl @Inject constructor(
    private val service: CityDao
): DbCityDataService {
    override suspend fun save(model: DbCity): Long {
        return service.save(model)
    }

    override suspend fun getAllCity(city: String): List<DbCity> {
        val newCity = "%$city%"
        return service.getAllCity(newCity)
    }
}