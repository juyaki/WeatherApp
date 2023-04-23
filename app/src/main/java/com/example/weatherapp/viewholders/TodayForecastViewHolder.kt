package com.example.weatherapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.adapters.TodayForecastAdapter
import com.example.weatherapp.models.Forecast

class TodayForecastViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

    fun bind(forecasts: List<Forecast>) {
        recyclerView.apply {
            adapter = TodayForecastAdapter(view.context, forecasts)
        }
    }
}