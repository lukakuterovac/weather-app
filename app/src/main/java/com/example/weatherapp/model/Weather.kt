package com.example.weatherapp.model

data class Weather(
    val city: String,
    val temperature: Double,
    val weather: String,
    val weatherIconId: String?
)
