package com.example.weatherapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.models.Coordinates
import com.example.weatherapp.models.WeatherForecastResult
import com.example.weatherapp.repository.RemoteData
import com.example.weatherapp.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ForecastViewModel(context: Application): AndroidViewModel(context) {
    private val weatherRepository = WeatherRepository()
    val weatherForecast: MutableLiveData<RemoteData<WeatherForecastResult, Throwable>> = MutableLiveData()

    fun fetchWeatherForecast(coordinates: Coordinates) {
        weatherForecast.value = RemoteData.Loading
        viewModelScope.launch(Dispatchers.IO) {
            weatherForecast.postValue(
                weatherRepository.fetchWeatherForecast(coordinates)
            )
        }
    }
}