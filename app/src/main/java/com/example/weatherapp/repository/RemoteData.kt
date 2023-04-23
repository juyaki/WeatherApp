package com.example.weatherapp.repository

sealed class RemoteData<out V, out E : Throwable> {
    object Loading : RemoteData<Nothing, Nothing>()
    class Success<out V : Any>(val value: V) : RemoteData<V, Nothing>()
    class Failure<out E : Throwable>(val error: E) : RemoteData<Nothing, E>()
}