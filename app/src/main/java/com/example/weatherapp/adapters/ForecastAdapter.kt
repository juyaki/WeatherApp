package com.example.weatherapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.weatherapp.R
import com.example.weatherapp.models.WeatherForecastResult
import com.example.weatherapp.viewholders.CurrentWeatherViewHolder
import com.example.weatherapp.viewholders.NextDaysForecastViewHolder
import com.example.weatherapp.viewholders.TodayForecastViewHolder
import java.lang.Exception

class ForecastAdapter(
    private val context: Context,
    private val weatherForecast: WeatherForecastResult
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        when (viewType) {
            VIEW_TYPE_CURRENT_WEATHER -> CurrentWeatherViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_current_weather, parent, false)
            )
            VIEW_TYPE_TODAY_FORECAST -> TodayForecastViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_horizontal_list, parent, false)
            )
            VIEW_TYPE_NEXT_DAYS_FORECAST -> NextDaysForecastViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_next_days_forecast, parent, false)
            )
            else -> throw Exception("Invalid viewType")
        }

    override fun getItemCount(): Int {
        if (weatherForecast.todayForecasts.isEmpty()) {
            return weatherForecast.nextDaysForecasts.size + 1
        }
        return weatherForecast.nextDaysForecasts.size + 2
    }

    override fun getItemViewType(position: Int): Int = when {
        position == 0 -> VIEW_TYPE_CURRENT_WEATHER
        position == 1 && weatherForecast.todayForecasts.isNotEmpty() -> VIEW_TYPE_TODAY_FORECAST
        else -> VIEW_TYPE_NEXT_DAYS_FORECAST
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is CurrentWeatherViewHolder -> holder.bind(weatherForecast.currentForecast)
            is TodayForecastViewHolder -> holder.bind(weatherForecast.todayForecasts)
            is NextDaysForecastViewHolder -> {
                val pos = if (weatherForecast.todayForecasts.isNotEmpty()) position - 2 else position - 1
                holder.bind(weatherForecast.nextDaysForecasts[pos])
            }
        }
    }

    companion object {
        private const val VIEW_TYPE_CURRENT_WEATHER = 0
        private const val VIEW_TYPE_TODAY_FORECAST = 1
        private const val VIEW_TYPE_NEXT_DAYS_FORECAST = 2
    }
}