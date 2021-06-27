package com.weather.weatherapplication.presentation.weatherdetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weather.weatherapplication.data.model.searchcity.WeatherCityModel
import com.weather.weatherapplication.domain.usecase.SearchCityUseCase
import com.weather.weatherapplication.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailWeatherViewModel @Inject constructor(
    private val useCase: SearchCityUseCase
) : ViewModel() {

    private val _weatherDetailLiveData = MutableLiveData<Resource<WeatherCityModel>>()
    val weatherDetailLiveData: LiveData<Resource<WeatherCityModel>> = _weatherDetailLiveData

    fun getSearchedCity(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _weatherDetailLiveData.postValue(Resource.Loading())
            _weatherDetailLiveData.postValue(useCase.execute(city, "BR"))
        }
    }

    fun onNavigationClick() {
        Log.d("TAG", "onNavigationClick: sdf")
    }
}