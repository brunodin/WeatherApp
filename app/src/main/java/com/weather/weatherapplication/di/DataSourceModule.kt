package com.weather.weatherapplication.di

import com.weather.weatherapplication.data.dao.CityDao
import com.weather.weatherapplication.data.datasource.dataservice.DbCityDataService
import com.weather.weatherapplication.data.datasource.dataservice.WeatherDataService
import com.weather.weatherapplication.data.datasource.dataserviceimpl.DbCityDataServiceImpl
import com.weather.weatherapplication.data.datasource.dataserviceimpl.WeatherDataServiceImpl
import com.weather.weatherapplication.data.service.WeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {
    @Provides
    fun providerWeatherDataService(
        service: WeatherService
    ): WeatherDataService = WeatherDataServiceImpl(service)

    @Provides
    fun providerCityDataService(
        service: CityDao
    ): DbCityDataService = DbCityDataServiceImpl(service)
}