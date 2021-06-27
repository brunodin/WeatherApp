package com.weather.weatherapplication.presentation.weatherdetail

import android.os.Bundle
import androidx.activity.viewModels
import com.weather.weatherapplication.R
import com.weather.weatherapplication.databinding.ActivityDetailWeatherBinding
import com.weather.weatherapplication.domain.util.Resource
import com.weather.weatherapplication.presentation.base.BaseActivity
import com.weather.weatherapplication.presentation.util.LoadingHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailWeatherActivity : BaseActivity<ActivityDetailWeatherBinding>() {

    @Inject lateinit var loading: LoadingHelper
    @Inject lateinit var adapter: WeatherDetailAdapter

    override var layoutRes = R.layout.activity_detail_weather
    private val _viewModel by viewModels<DetailWeatherViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = _viewModel
    }

    override fun onStart() {
        super.onStart()
        val city = intent.getStringExtra("city")
        _viewModel.getSearchedCity(city!!)
        
        initViewElements()
        initObservers()
    }

    private fun initViewElements() {
        binding.rvInfoWeather.adapter = adapter
    }

    private fun initObservers() {
        _viewModel.weatherDetailLiveData.observe(this) {
            when(it) {
                is Resource.Success -> {
                    binding.model = it.data?.data?.get(0)
                    loading.stopLoading()
                    it.data?.data?.get(0)?.weatherInfo?.let { it1 -> adapter.setNewList(it1) }
                }
                is Resource.Loading -> {
                    loading.startLoading()
                }
                else -> {
                    loading.stopLoading()
                }
            }
        }
    }
}