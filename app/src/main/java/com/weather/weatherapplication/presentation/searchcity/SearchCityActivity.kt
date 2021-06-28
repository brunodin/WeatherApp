package com.weather.weatherapplication.presentation.searchcity

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.weather.weatherapplication.R
import com.weather.weatherapplication.databinding.ActivitySearchCityBinding
import com.weather.weatherapplication.domain.util.Resource
import com.weather.weatherapplication.presentation.base.BaseActivity
import com.weather.weatherapplication.presentation.util.AlertHelper
import com.weather.weatherapplication.presentation.util.LoadingHelper
import com.weather.weatherapplication.presentation.weatherdetail.DetailWeatherActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchCityActivity : BaseActivity<ActivitySearchCityBinding>() {

    override var layoutRes = R.layout.activity_search_city
    private val _viewModel by viewModels<SearchCityViewModel>()
    @Inject lateinit var loading: LoadingHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = _viewModel
    }

    override fun onStart() {
        super.onStart()
        initObserver()
        initAutoComplete()
    }

    private fun initAutoComplete() {
        binding.etdCity.addTextChangedListener {
            _viewModel.getCityList()
        }
    }

    private fun initObserver() {
        _viewModel.openActivity.observe(this) { result ->
            if (result.second) {
                displayAlert(result.first)
            } else {
                startActivity(result.first)
            }
        }
        _viewModel.saveCityLiveData.observe(this) {
            when(it) {
                is Resource.Success -> {
                    loading.stopLoading()
                    Toast.makeText(this, getString(R.string.txt_success_save), Toast.LENGTH_SHORT).show()
                    _viewModel.openActivity(false)
                }
                is Resource.Loading -> {
                    loading.startLoading()
                }

                else -> {
                    loading.stopLoading()
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    _viewModel.openActivity(false)
                }
            }
        }
        _viewModel.getCityLiveData.observe(this) {
            if (it is Resource.Success) {
                setAutoCompleteField(it.data)
            }
        }
    }

    private fun displayAlert(bundle: Bundle) {
        val alert = AlertHelper
            .alert(
                this,
                getString(R.string.dialog_alert),
                getString(R.string.dialog_save_city)
            )
        alert.setButton(
            Dialog.BUTTON_POSITIVE,
            getString(R.string.txt_yes)
        ) { _, _ ->
            _viewModel.saveCity()
        }
        alert.setButton(
            Dialog.BUTTON_NEGATIVE,
            getString(R.string.txt_no)
        ) { _, _ ->
            startActivity(bundle)
        }

        alert.setOnShowListener {
            alert.getButton(Dialog.BUTTON_POSITIVE).setTextColor(Color.BLACK)
            alert.getButton(Dialog.BUTTON_NEGATIVE).setTextColor(Color.RED)
        }
        alert.show()
    }

    private fun startActivity(bundle: Bundle) {
        openActivity(DetailWeatherActivity::class.java, bundle)
    }

    private fun setAutoCompleteField(list: List<String>?) {
        list?.let {
            binding.etdCity
                .setAdapter(
                    ArrayAdapter(
                        applicationContext,
                        R.layout.support_simple_spinner_dropdown_item,
                        list
                        )
                )

            binding.etdCity.setOnItemClickListener { _, _, position, _ ->
                _viewModel.city.set(list[position])
                binding.etdCity.dismissDropDown()
                _viewModel.openActivity(false)
            }
        }
    }
}