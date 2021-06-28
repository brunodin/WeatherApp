package com.weather.weatherapplication.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.weather.weatherapplication.data.model.DbCity

@Dao
interface CityDao {
    @Insert
    suspend fun save(mode: DbCity): Long

    @Query("SELECT * FROM tb_city WHERE city Like :city")
    suspend fun getAllCity(city: String): List<DbCity>
}