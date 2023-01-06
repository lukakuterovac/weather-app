package com.example.weatherapp.model

data class WeatherDetails(
    val city: String,
    val temperature: Double,
    val feelsLikeTemperature: Double,
    val pressure: Double,
    val humidity: Double,
    val weather: String,
    val weatherDescription: String,
    val weatherIconId: String?
)
