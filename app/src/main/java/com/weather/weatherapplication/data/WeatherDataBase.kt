package com.weather.weatherapplication.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.weather.weatherapplication.data.dao.CityDao
import com.weather.weatherapplication.data.model.DbCity

@Database(entities = [DbCity::class], version = 1)
abstract class WeatherDataBase: RoomDatabase() {
        abstract fun weatherDao(): CityDao
    }