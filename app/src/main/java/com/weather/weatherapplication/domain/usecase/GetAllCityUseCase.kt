package com.weather.weatherapplication.domain.usecase

import android.content.Context
import com.weather.weatherapplication.R
import com.weather.weatherapplication.data.model.DbCity
import com.weather.weatherapplication.domain.repository.DbCityRepository
import com.weather.weatherapplication.domain.util.Resource
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GetAllCityUseCase @Inject constructor(
    private val repository: DbCityRepository,
    @ApplicationContext private val context: Context
) {
    suspend fun execute(
        city: String
    ): Resource<List<String>> {
        var resultResponse: Resource<List<String>>
        return try {
            val list = repository.getAllCity(city)
            val cityList = list.map { dbCity -> dbCity.city  }
            resultResponse = Resource.Success(cityList)
            resultResponse
        } catch (e: Exception) {
            resultResponse = Resource.Failure(e)
            resultResponse.message = context.getString(R.string.error_couldnt_get)
            resultResponse
        }
    }
}