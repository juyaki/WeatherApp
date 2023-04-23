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

class NextDaysForecastViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val date = view.findViewById<TextView>(R.id.date)
    private val description = view.findViewById<TextView>(R.id.description)
    private val maxTemperature = view.findViewById<TextView>(R.id.maxTemperature)
    private val minTemperature = view.findViewById<TextView>(R.id.minTemperature)
    private val icon = view.findViewById<ImageView>(R.id.icon)

    fun bind(forecast: Forecast) {
        date.text = forecast.dt.toLocalDateTime().format(
            DateTimeFormatter.ofPattern("EEEE dd MMMM")
        )
        description.text = forecast.weather.first().description.replaceFirstChar { it.uppercase() }
        maxTemperature.text = "${forecast.main.temp_max.toCelsius()}°"
        minTemperature.text = "${forecast.main.temp_min.toCelsius()}°"

        icon.setImageResource(forecast.weather.first().toWeatherDrawableId())
    }
}