package com.weather.weatherapplication.presentation.searchcity

import android.os.Bundle
import androidx.activity.viewModels
import com.weather.weatherapplication.R
import com.weather.weatherapplication.databinding.ActivitySearchCityBinding
import com.weather.weatherapplication.presentation.base.BaseActivity
import com.weather.weatherapplication.presentation.weatherdetail.DetailWeatherActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchCityActivity : BaseActivity<ActivitySearchCityBinding>() {

    override var layoutRes = R.layout.activity_search_city
    private val _viewModel by viewModels<SearchCityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = _viewModel
    }

    override fun onStart() {
        super.onStart()
        initObserver()
    }

    private fun initObserver() {
        _viewModel.openActivity.observe(this) {
            val city = binding.etdCity.text.toString()
            val bundle = Bundle()
            bundle.putString("city", city)
            openActivity(DetailWeatherActivity::class.java, bundle)
        }
    }
}