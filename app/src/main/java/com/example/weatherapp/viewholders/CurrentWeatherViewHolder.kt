package com.example.weatherapp.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.extensions.toCelsius
import com.example.weatherapp.extensions.toLocalDateTime
import com.example.weatherapp.models.Forecast
import java.time.format.DateTimeFormatter

class CurrentWeatherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val currentDate = view.findViewById<TextView>(R.id.currentDate)
    private val currentTemperature = view.findViewById<TextView>(R.id.currentTemperature)
    private val icon = view.findViewById<ImageView>(R.id.icon)

    fun bind(forecast: Forecast) {
        currentDate.text = forecast.dt.toLocalDateTime().format(
            DateTimeFormatter.ofPattern("dd MMMM, HH:ss")
        )
        currentTemperature.text = "${forecast.main.temp.toCelsius()}°C"
        icon.setImageResource(forecast.weather.first().toWeatherDrawableId())
    }
}