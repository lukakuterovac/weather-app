package com.example.weatherapp.data.network

import com.example.weatherapp.data.network.model.response.WeatherResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/weather"
private const val API_KEY = "29b6ef2bd647b8aea60c93d4358e3fe9"

class WeatherServiceImpl(private val client: HttpClient) : WeatherService {
    override suspend fun fetchWeatherDetails(city: String): WeatherResponse =
        client.get("$BASE_URL?q=$city&appid=$API_KEY&units=metric").body()

    override suspend fun fetchWeatherDetails(coordinates: Pair<Double, Double>): WeatherResponse =
        client.get("$BASE_URL?lat=${coordinates.first}&lon=${coordinates.second}&appid=$API_KEY&units=metric")
            .body()
}
