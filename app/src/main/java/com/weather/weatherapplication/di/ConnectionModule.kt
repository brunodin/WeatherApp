package com.weather.weatherapplication.di

import com.weather.weatherapplication.BuildConfig
import com.weather.weatherapplication.data.service.WeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ConnectionModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.connectTimeout(2000, TimeUnit.MILLISECONDS)
        okHttpClient.readTimeout(2000, TimeUnit.MILLISECONDS)
        okHttpClient.writeTimeout(2000, TimeUnit.MILLISECONDS)
        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideConverterService(retrofit: Retrofit): WeatherService {
        return retrofit.create(WeatherService::class.java)
    }
}