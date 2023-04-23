package com.example.weatherapp.repository

import com.example.weatherapp.extensions.toLocalDateTime
import com.example.weatherapp.models.Coordinates
import com.example.weatherapp.models.WeatherForecastResult
import com.example.weatherapp.repository.api.ForecastApi
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit.DAYS

class WeatherRepository {

    private val forecastApi = RetrofitBuilder.getInstance().create(ForecastApi::class.java)

    suspend fun fetchWeatherForecast(coordinates: Coordinates): RemoteData<WeatherForecastResult, Throwable> =
        try {
            val weatherForecast = forecastApi.getWeatherForecast(
                latitude = coordinates.lat,
                longitude = coordinates.lon,
                apiKey = RetrofitBuilder.apiKey
            )
            val today = LocalDateTime.now()
            val todayForecasts = weatherForecast.list.filter {
                it.dt.toLocalDateTime().truncatedTo(DAYS).isEqual(today.truncatedTo(DAYS))
            }
            val nextDaysForecasts =  weatherForecast.list.map {
                it.dt.toLocalDateTime() to it
            }.filter { (date, _) ->
                date.truncatedTo(DAYS).isAfter(today.truncatedTo(DAYS))
            }.groupBy { (date, _) ->
                date.truncatedTo(DAYS)
            }.map {
                it.value.first().second
            }
            RemoteData.Success(
                WeatherForecastResult(
                    city = weatherForecast.city,
                    currentForecast = weatherForecast.list.first(),
                    todayForecasts = todayForecasts,
                    nextDaysForecasts = nextDaysForecasts
                )
            )
        } catch (error: Throwable) {
            RemoteData.Failure(error)
        }
}