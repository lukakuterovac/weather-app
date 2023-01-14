package com.example.weatherapp.data.network.model

import com.example.weatherapp.model.Weather
import com.example.weatherapp.model.WeatherDetails
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiWeatherDetails(
    @SerialName("city")
    val city: String,
    @SerialName("temperature")
    val temperature: Double,
    @SerialName("weather")
    val weather: String,
    @SerialName("weatherIconId")
    val weatherIconId: String?,
    @SerialName("feelsLikeTemperature")
    val feelsLikeTemperature: Double,
    @SerialName("pressure")
    val pressure: Double,
    @SerialName("humidity")
    val humidity: Double,
    @SerialName("weatherDescription")
    val weatherDescription: String
) {
    fun toWeatherDetails(isFavorite: Boolean) = WeatherDetails(
        weather = toWeather(isFavorite),
        feelsLikeTemperature = feelsLikeTemperature,
        pressure = pressure,
        humidity = humidity,
        weatherDescription = weatherDescription
    )

    fun toWeather(isFavorite: Boolean) = Weather(
        city = city,
        temperature = temperature,
        weather = weather,
        weatherIconId = weatherIconId,
        isFavorite = isFavorite
    )
}
