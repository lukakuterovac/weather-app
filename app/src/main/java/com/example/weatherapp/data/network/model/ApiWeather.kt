package com.example.weatherapp.data.network.model

import com.example.weatherapp.model.Weather
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiWeather(
    @SerialName("city")
    val city: String,
    @SerialName("temperature")
    val temperature: Double,
    @SerialName("weather")
    val weather: String,
    @SerialName("weatherIconId")
    val weatherIconId: String?,
) {
    fun toWeather(isFavorite: Boolean) = Weather(
        city = city,
        temperature = temperature,
        weather = weather,
        weatherIconId = weatherIconId,
        isFavorite = isFavorite
    )
}
