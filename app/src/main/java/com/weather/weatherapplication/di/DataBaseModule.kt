package com.weather.weatherapplication.di

import android.content.Context
import androidx.room.Room
import com.weather.weatherapplication.data.WeatherDataBase
import com.weather.weatherapplication.data.dao.CityDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            WeatherDataBase::class.java, "weather"
        )
            .build()

    @Provides
    fun provideWeatherDao(dataBase: WeatherDataBase): CityDao {
        return dataBase.weatherDao()
    }
}