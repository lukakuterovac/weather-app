package com.example.weatherapp.data.network

import com.example.weatherapp.data.network.model.response.WeatherResponse

interface WeatherService {
    suspend fun fetchWeatherDetails(city: String): WeatherResponse
}
