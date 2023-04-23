package com.example.weatherapp.models

import com.example.weatherapp.R

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
) {
    fun toWeatherDrawableId(): Int = when (id) {
        200 -> R.drawable.thunder
        201 -> R.drawable.thunder
        202 -> R.drawable.thunder
        210 -> R.drawable.thunder
        211 -> R.drawable.thunder
        212 -> R.drawable.thunder
        221 -> R.drawable.thunder
        230 -> R.drawable.thunder
        231 -> R.drawable.thunder
        232 -> R.drawable.thunder
        300 -> R.drawable.rainy_4
        301 -> R.drawable.rainy_5
        302 -> R.drawable.rainy_6
        310 -> R.drawable.rainy_2
        311 -> R.drawable.rainy_1
        312 -> R.drawable.rainy_1
        313 -> R.drawable.rainy_1
        314 -> R.drawable.rainy_1
        321 -> R.drawable.sleet
        500 -> R.drawable.rainy_4
        501 -> R.drawable.rainy_1
        502 -> R.drawable.rain_and_sleet_mix
        503 -> R.drawable.rain_and_sleet_mix
        504 -> R.drawable.rain_and_snow_mix
        511 -> R.drawable.snowy_4
        520 -> R.drawable.rainy_4
        521 -> R.drawable.rainy_7
        522 -> R.drawable.rain_and_sleet_mix
        531 -> R.drawable.rainy_4
        600 -> R.drawable.snowy_4
        601 -> R.drawable.snowy_5
        602 -> R.drawable.snowy_6
        611 -> R.drawable.rain_and_snow_mix
        612 -> R.drawable.cloudy_original
        613 -> R.drawable.snowy_4
        615 -> R.drawable.rain_and_snow_mix
        616 -> R.drawable.rain_and_snow_mix
        620 -> R.drawable.snowy_4
        621 -> R.drawable.snowy_5
        622 -> R.drawable.snowy_6
        800 -> R.drawable.day
        801 -> R.drawable.cloudy_day_1
        802 -> R.drawable.cloudy_day_2
        803 -> R.drawable.cloudy_day_3
        804 -> R.drawable.cloudy
        else -> throw Exception("Invalid icon code")
    }
}