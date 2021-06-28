package com.weather.weatherapplication.domain.usecase

import android.content.Context
import com.weather.weatherapplication.R
import com.weather.weatherapplication.data.model.DbCity
import com.weather.weatherapplication.domain.repository.DbCityRepository
import com.weather.weatherapplication.domain.util.Resource
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SaveCityUseCase @Inject constructor(
    private val repository: DbCityRepository,
    @ApplicationContext private val context: Context
) {
    suspend fun execute (
        model: DbCity
    ): Resource<Unit> {
        var resultResponse: Resource<Unit>
        return try {
            resultResponse = if (repository.save(model) > 0) {
                Resource.Success(Unit)
            } else {
                Resource.Error(400, context.getString(R.string.error_save))
            }
            resultResponse
        } catch (e: Exception) {
            resultResponse = Resource.Failure(e)
            resultResponse.message = context.getString(R.string.error_save)
            resultResponse
        }
    }
}