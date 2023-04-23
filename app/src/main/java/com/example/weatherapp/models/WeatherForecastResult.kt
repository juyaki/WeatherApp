package com.example.weatherapp.models

data class WeatherForecastResult(
    val city: City,
    val currentForecast: Forecast,
    val todayForecasts: List<Forecast>,
    val nextDaysForecasts: List<Forecast>
)