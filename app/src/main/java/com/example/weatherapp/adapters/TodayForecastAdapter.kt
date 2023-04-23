package com.example.weatherapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.models.Forecast
import com.example.weatherapp.viewholders.TodayForecastItemViewHolder

class TodayForecastAdapter(
    private val context: Context,
    private val forecasts: List<Forecast>
) : RecyclerView.Adapter<TodayForecastItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodayForecastItemViewHolder =
            TodayForecastItemViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_today_forecast, parent, false)
            )

    override fun getItemCount(): Int = forecasts.size

    override fun onBindViewHolder(holder: TodayForecastItemViewHolder, position: Int) {
        holder.bind(forecasts[position])
    }
}