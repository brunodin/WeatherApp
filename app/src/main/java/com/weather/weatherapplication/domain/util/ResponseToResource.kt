package com.weather.weatherapplication.domain.util

import retrofit2.Response

object ResponseToResource {
    fun <Z> responseToResource(response: Response<Z>?): Resource<Z> {
        if (response != null) {
            if (response.isSuccessful) {
                return Resource.Success(response.body())
            }
        }
        return Resource.Error(response?.code()!!, response.errorBody()?.string()!!)
    }
}