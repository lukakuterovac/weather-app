package com.example.weatherapp.model

data class WeatherDetails(
    val weather: Weather,
    val feelsLikeTemperature: Double,
    val pressure: Double,
    val humidity: Double,
    val weatherDescription: String,
)
