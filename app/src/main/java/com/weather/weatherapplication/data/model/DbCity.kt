package com.weather.weatherapplication.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_city",)
data class DbCity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id" )
    val id: Int? = null,
    @ColumnInfo(name = "city" )
    val city: String
        )