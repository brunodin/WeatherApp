package com.weather.weatherapplication.presentation.searchcity

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.weather.weatherapplication.presentation.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchCityViewModel @Inject constructor(): ViewModel() {

    val city = ObservableField("")
    val button  = object : ObservableField<Boolean>(city) {
        override fun get(): Boolean {
            return !city.get().isNullOrEmpty()
        }
    }

    private val _openActivity = SingleLiveEvent<Unit>()
    val openActivity: LiveData<Unit> = _openActivity

    fun openActivity() {
       _openActivity.value = Unit
   }
}