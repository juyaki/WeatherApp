package com.example.weatherapp

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.adapters.ForecastAdapter
import com.example.weatherapp.extensions.checkForPermission
import com.example.weatherapp.extensions.getLastKnownLocation
import com.example.weatherapp.extensions.isVisible
import com.example.weatherapp.extensions.showErrorDialog
import com.example.weatherapp.repository.RemoteData

class ForecastActivity : AppCompatActivity() {

    private val viewModel: ForecastViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupObserver()
        fetchWeatherForecast()
    }

    private fun setupObserver() {
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        viewModel.weatherForecast.observe(this) { weatherForecast ->
            progressBar.isVisible = weatherForecast is RemoteData.Loading
            when (weatherForecast) {
                is RemoteData.Loading -> {
                    // Nothing since the loading state was already handled
                }
                is RemoteData.Success -> {
                    recyclerView.adapter = ForecastAdapter(this, weatherForecast.value)
                }
                is RemoteData.Failure -> {
                    showErrorDialog { fetchWeatherForecast() }
                }
            }
        }
    }

    private fun fetchWeatherForecast() {
        checkForPermission(Manifest.permission.ACCESS_COARSE_LOCATION) {
            val coordinates = getLastKnownLocation() ?: return@checkForPermission
            viewModel.fetchWeatherForecast(coordinates)
        }
    }
}