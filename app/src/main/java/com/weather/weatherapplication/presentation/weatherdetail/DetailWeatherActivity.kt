package com.weather.weatherapplication.presentation.weatherdetail

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import com.weather.weatherapplication.R
import com.weather.weatherapplication.data.constants.Constants.CITY
import com.weather.weatherapplication.databinding.ActivityDetailWeatherBinding
import com.weather.weatherapplication.domain.util.Resource
import com.weather.weatherapplication.presentation.base.BaseActivity
import com.weather.weatherapplication.presentation.util.AlertHelper
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
        val city = intent.getStringExtra(CITY)
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
                    loading.stopLoading()
                    if (!it.data?.data.isNullOrEmpty()) {
                        binding.model = it.data?.data?.get(0)
                        it.data?.data?.get(0)?.weatherInfo?.let { it1 -> adapter.setNewList(it1) }
                    } else {
                        displayAlert(getString(R.string.dialog_no_weather))
                    }
                }
                is Resource.Loading -> {
                    loading.startLoading()
                }
                else -> {
                    loading.stopLoading()
                    displayAlert(getString(R.string.error_net))
                }
            }
        }
        _viewModel.onBackPressLiveData.observe(this) { onBackPressed() }
    }

    private fun displayAlert(message: String) {
        val alert = AlertHelper
            .alert(
                this,
                getString(R.string.dialog_alert),
                message
            )
        alert.setButton(
            Dialog.BUTTON_POSITIVE,
            getString(R.string.ok)
        ) { _, _ ->
            onBackPressed()
        }
        alert.setOnShowListener {
            alert.getButton(Dialog.BUTTON_POSITIVE).setTextColor(Color.BLACK)
        }
        alert.show()
    }
}