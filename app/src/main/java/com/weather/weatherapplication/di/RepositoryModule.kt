package com.weather.weatherapplication.di

import com.weather.weatherapplication.data.datasource.dataservice.DbCityDataService
import com.weather.weatherapplication.data.datasource.dataservice.WeatherDataService
import com.weather.weatherapplication.domain.repository.DbCityRepository
import com.weather.weatherapplication.domain.repository.DbCityRepositoryImpl
import com.weather.weatherapplication.domain.repository.WeatherRepository
import com.weather.weatherapplication.domain.repository.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun providerWeatherRepository(
        dataService: WeatherDataService
    ): WeatherRepository = WeatherRepositoryImpl(dataService)

    @Provides
    fun providerCityRepository(
        dataService: DbCityDataService
    ): DbCityRepository = DbCityRepositoryImpl(dataService)
}