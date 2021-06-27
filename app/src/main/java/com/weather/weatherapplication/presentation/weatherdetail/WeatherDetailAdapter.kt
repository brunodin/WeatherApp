package com.weather.weatherapplication.presentation.weatherdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.weather.weatherapplication.R
import com.weather.weatherapplication.data.model.weatherInfoModel.WeatherInfoModel
import com.weather.weatherapplication.databinding.ItemWeatherDetailBinding
import javax.inject.Inject

class WeatherDetailAdapter @Inject constructor(): RecyclerView.Adapter<WeatherDetailAdapter.ViewHolder>() {

    private var _list = ArrayList<WeatherInfoModel>(arrayListOf())

    fun setNewList(newList: List<WeatherInfoModel>) {
        _list = newList as ArrayList<WeatherInfoModel>
        notifyDataSetChanged()
    }
    inner class ViewHolder(private val binding: ItemWeatherDetailBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(model: WeatherInfoModel) {
            binding.model = model
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemWeatherDetailBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_weather_detail,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder
        .bind(_list[position])

    override fun getItemCount(): Int = _list.size
}