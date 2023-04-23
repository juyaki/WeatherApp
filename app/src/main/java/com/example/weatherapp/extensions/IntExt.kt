package com.example.weatherapp.extensions

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

fun Int.toLocalDateTime(): LocalDateTime = LocalDateTime.ofInstant(
    Instant.ofEpochSecond(this.toLong()), ZoneId.systemDefault()
)