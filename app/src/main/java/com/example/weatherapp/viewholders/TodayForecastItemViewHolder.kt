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

class TodayForecastItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val hour = view.findViewById<TextView>(R.id.hour)
    private val temperature = view.findViewById<TextView>(R.id.temperature)
    private val icon = view.findViewById<ImageView>(R.id.icon)

    fun bind(forecast: Forecast) {
        hour.text = forecast.dt.toLocalDateTime().format(
            DateTimeFormatter.ofPattern("HH:mm")
        )
        temperature.text = "${forecast.main.temp.toCelsius()}°"
        icon.setImageResource(forecast.weather.first().toWeatherDrawableId())
    }
}