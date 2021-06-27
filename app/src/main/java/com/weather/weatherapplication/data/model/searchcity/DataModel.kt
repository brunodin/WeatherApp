package com.weather.weatherapplication.data.model.searchcity


import com.google.gson.annotations.SerializedName
import com.weather.weatherapplication.data.model.weatherInfoModel.WeatherInfoModel

data class DataModel(
    @SerializedName("app_temp")
    val appTemp: Double?,
    @SerializedName("aqi")
    val aqi: Int?,
    @SerializedName("city_name")
    val cityName: String?,
    @SerializedName("clouds")
    val clouds: Int?,
    @SerializedName("country_code")
    val countryCode: String?,
    @SerializedName("datetime")
    val datetime: String?,
    @SerializedName("dewpt")
    val dewpt: Double?,
    @SerializedName("dhi")
    val dhi: Double?,
    @SerializedName("dni")
    val dni: Double?,
    @SerializedName("elev_angle")
    val elevAngle: Double?,
    @SerializedName("ghi")
    val ghi: Double?,
    @SerializedName("h_angle")
    val hAngle: Int?,
    @SerializedName("lat")
    val lat: Double?,
    @SerializedName("lon")
    val lon: Double?,
    @SerializedName("ob_time")
    val obTime: String?,
    @SerializedName("pod")
    val pod: String?,
    @SerializedName("precip")
    val precip: Int?,
    @SerializedName("pres")
    val pres: Double?,
    @SerializedName("rh")
    val rh: Int?,
    @SerializedName("slp")
    val slp: Int?,
    @SerializedName("snow")
    val snow: Int?,
    @SerializedName("solar_rad")
    val solarRad: Double?,
    @SerializedName("state_code")
    val stateCode: String?,
    @SerializedName("station")
    val station: String?,
    @SerializedName("sunrise")
    val sunrise: String?,
    @SerializedName("sunset")
    val sunset: String?,
    @SerializedName("temp")
    val temp: Int?,
    @SerializedName("timezone")
    val timezone: String?,
    @SerializedName("ts")
    val ts: Int?,
    @SerializedName("uv")
    val uv: Double?,
    @SerializedName("vis")
    val vis: Int?,
    @SerializedName("weather")
    val weather: WeatherModel?,
    @SerializedName("wind_cdir")
    val windCdir: String?,
    @SerializedName("wind_cdir_full")
    val windCdirFull: String?,
    @SerializedName("wind_dir")
    val windDir: Int?,
    @SerializedName("wind_spd")
    val windSpd: Double?
) {
    var weatherInfo: List<WeatherInfoModel>? = null
    var dateTimeNow: String? = null
}