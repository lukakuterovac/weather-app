package com.example.weatherapp.data.network.model.response

import com.example.weatherapp.model.Weather
import com.example.weatherapp.model.WeatherDetails
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    @SerialName("weather")
    val weather: List<WeatherApi>,
    @SerialName("main")
    val main: MainApi,
    @SerialName("name")
    val city: String
) {
    fun toWeatherDetails(isFavorite: Boolean) = WeatherDetails(
        weather = toWeather(isFavorite),
        feelsLikeTemperature = main.feelsLikeTemperature,
        pressure = main.pressure,
        humidity = main.humidity,
        weatherDescription = weather.first().weatherDescription
    )

    fun toWeather(isFavorite: Boolean) = Weather(
        city = city,
        temperature = main.temperature,
        weather = weather.first().weather,
        weatherIconId = weather.first().weatherIconId,
        isFavorite = isFavorite
    )
}

@Serializable
data class WeatherApi(
    @SerialName("main")
    val weather: String,
    @SerialName("description")
    val weatherDescription: String,
    @SerialName("icon")
    val weatherIconId: String?
)

@Serializable
data class MainApi(
    @SerialName("temp")
    val temperature: Double,
    @SerialName("feels_like")
    val feelsLikeTemperature: Double,
    @SerialName("pressure")
    val pressure: Double,
    @SerialName("humidity")
    val humidity: Double
)
