package com.example.weatherapp.repository.api

import com.example.weatherapp.models.WeatherForecast
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastApi {
    @GET("data/2.5/forecast")
    suspend fun getWeatherForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String
    ): WeatherForecast
}