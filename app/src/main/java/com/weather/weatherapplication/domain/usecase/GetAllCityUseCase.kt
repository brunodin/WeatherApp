package com.weather.weatherapplication.domain.usecase

import com.weather.weatherapplication.data.model.DbCity
import com.weather.weatherapplication.domain.repository.DbCityRepository
import com.weather.weatherapplication.domain.util.Resource
import javax.inject.Inject

class GetAllCityUseCase @Inject constructor(
    private val repository: DbCityRepository
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
            resultResponse.message = "Não foi recuperar os dados"
            resultResponse
        }
    }
}