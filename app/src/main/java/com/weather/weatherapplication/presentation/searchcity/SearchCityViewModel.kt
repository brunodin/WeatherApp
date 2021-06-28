package com.weather.weatherapplication.presentation.searchcity

import android.os.Bundle
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weather.weatherapplication.data.constants.Constants
import com.weather.weatherapplication.data.model.DbCity
import com.weather.weatherapplication.domain.usecase.GetAllCityUseCase
import com.weather.weatherapplication.domain.usecase.SaveCityUseCase
import com.weather.weatherapplication.domain.util.Resource
import com.weather.weatherapplication.presentation.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchCityViewModel @Inject constructor(
    private val saveCityUseCase: SaveCityUseCase,
    private val getCityUseCase: GetAllCityUseCase
): ViewModel() {

    val city = ObservableField("")
    val button  = object : ObservableField<Boolean>(city) {
        override fun get(): Boolean {
            return !city.get().isNullOrEmpty()
        }
    }

    private val _openActivity = SingleLiveEvent<Pair<Bundle, Boolean>>()
    val openActivity: LiveData<Pair<Bundle, Boolean>> = _openActivity

    private val _saveCityLiveData = SingleLiveEvent<Resource<Unit>>()
    val saveCityLiveData: LiveData<Resource<Unit>> = _saveCityLiveData

    private val _getCityLiveData = SingleLiveEvent<Resource<List<String>>>()
    val getCityLiveData: LiveData<Resource<List<String>>> = _getCityLiveData

    fun openActivity(showDialog: Boolean = true) {
        val bundle = Bundle()
        bundle.putString(Constants.CITY, city.get())
       _openActivity.value = Pair(bundle, showDialog)
   }

    fun saveCity() {
        viewModelScope.launch(Dispatchers.IO) {
            _saveCityLiveData.postValue(Resource.Loading())
            _saveCityLiveData.postValue(saveCityUseCase.execute(DbCity(city = city.get()!!)))
        }
    }

    fun getCityList() {
        viewModelScope.launch(Dispatchers.IO) {
            _getCityLiveData.postValue(getCityUseCase.execute(city.get()!!))
        }
    }
}