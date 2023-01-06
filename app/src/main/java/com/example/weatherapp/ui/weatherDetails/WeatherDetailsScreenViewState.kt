package com.example.weatherapp.ui.weatherDetails

data class WeatherDetailsScreenViewState(
    val city: String,
    val temperature: Double,
    val feelsLikeTemperature: Double,
    val pressure: Double,
    val humidity: Double,
    val weather: String,
    val weatherDescription: String,
    val weatherIconId: String?
)
