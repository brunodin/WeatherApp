package com.weather.weatherapplication.domain.usecase

import com.weather.weatherapplication.data.model.DbCity
import com.weather.weatherapplication.domain.repository.DbCityRepository
import com.weather.weatherapplication.domain.util.Resource
import javax.inject.Inject

class SaveCityUseCase @Inject constructor(
    private val repository: DbCityRepository
) {
    suspend fun execute (
        model: DbCity
    ): Resource<Unit> {
        var resultResponse: Resource<Unit>
        return try {
            resultResponse = if (repository.save(model) > 0) {
                Resource.Success(Unit)
            } else {
                Resource.Error(400, "Não foi possivel gravar")
            }
            resultResponse
        } catch (e: Exception) {
            resultResponse = Resource.Failure(e)
            resultResponse.message = "Não foi possivel gravar"
            resultResponse
        }
    }
}