package com.example.weatherapp.mock

import com.example.weatherapp.model.Weather
import com.example.weatherapp.model.WeatherDetails

object WeatherMock {
    fun getWeatherList(): List<Weather> = listOf(
        Weather(
            city = "Osijek",
            temperature = 10.0,
            weather = "Cloudy",
            weatherIconId = "10d",
            isFavorite = true
        ),
        Weather(
            city = "Zagreb",
            temperature = 10.0,
            weather = "Cloudy",
            weatherIconId = "10d",
            isFavorite = true
        ),
        Weather(
            city = "Split",
            temperature = 10.0,
            weather = "Cloudy",
            weatherIconId = "10d",
            isFavorite = true
        ),
        Weather(
            city = "Rijeka",
            temperature = 10.0,
            weather = "Cloudy",
            weatherIconId = "10d",
            isFavorite = true
        )
    )

    fun getWeatherDetails(): WeatherDetails = WeatherDetails(
        weather = Weather(
            city = "Osijek",
            temperature = 10.0,
            weather = "Cloudy",
            weatherIconId = "10d",
            isFavorite = true
        ),
        feelsLikeTemperature = 10.0,
        pressure = 1000.0,
        humidity = 65.0,
        weatherDescription = "Scattered clouds",
    )
}
