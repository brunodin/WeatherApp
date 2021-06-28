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
    ): Resource<List<DbCity>> {
        var resultResponse: Resource<List<DbCity>>
        return try {
            resultResponse = Resource.Success(repository.getAllCity(city))
            resultResponse
        } catch (e: Exception) {
            resultResponse = Resource.Failure(e)
            resultResponse.message = context.getString(R.string.error_couldnt_get)
            resultResponse
        }
    }
}