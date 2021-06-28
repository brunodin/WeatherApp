package com.weather.weatherapplication.domain.usecase

import com.weather.weatherapplication.data.model.searchcity.DataModel
import com.weather.weatherapplication.data.model.searchcity.WeatherCityModel
import com.weather.weatherapplication.data.model.weatherInfoModel.WeatherInfoModel
import com.weather.weatherapplication.domain.repository.WeatherRepository
import com.weather.weatherapplication.domain.util.Resource
import com.weather.weatherapplication.presentation.util.getLocalDate
import com.weather.weatherapplication.presentation.util.setDecimalScaleInDouble
import javax.inject.Inject

class SearchCityUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend fun execute(
        city: String,
        country: String
    ): Resource<WeatherCityModel> {
        val resultResponse: Resource<WeatherCityModel>

        return try {
            resultResponse = repository.getCityWeather(city, country)
            if (resultResponse is Resource.Success) {
                resultResponse.data?.data?.let {
                    val list = getNewListForAdapter(it[0])
                    it[0].temp = setDecimalScaleInDouble(it[0].temp!!)
                    it[0].weatherInfo = list
                    it[0].dateTimeNow = getTime(it[0])
                }
            }
            resultResponse
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }

    private fun getTime(model: DataModel): String {
        return getLocalDate(model.obTime!!)
    }

    private fun getNewListForAdapter(model: DataModel): List<WeatherInfoModel> {
        return ArrayList<WeatherInfoModel>(
            arrayListOf(
                WeatherInfoModel(Pair("Localidade", model.cityName!!)),
                WeatherInfoModel(Pair("Pressão atmosférica", "${model.pres}mb")),
                WeatherInfoModel(Pair("Pressão do nível do mar", "${model.slp}mb")),
                WeatherInfoModel(Pair("Velocidade do vento", "${model.windSpd}m/s")),
                WeatherInfoModel(Pair("Direção do vento", "${model.windDir}°")),
                WeatherInfoModel(Pair("Temperatura aparente", "${model.appTemp}°C")),
                WeatherInfoModel(Pair("Umidade relativa do ar", "${model.rh}%")),
                WeatherInfoModel(Pair("Cobertura das nuvens", "${model.clouds}%"))
            )
        )
    }
}