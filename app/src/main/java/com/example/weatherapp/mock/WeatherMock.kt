package com.example.weatherapp.mock

import com.example.weatherapp.model.WeatherDetails

object WeatherMock {
    fun getWeatherList(): List<WeatherDetails> = listOf(
        WeatherDetails(
            city = "Osijek",
            temperature = 10.0,
            feelsLikeTemperature = 10.0,
            pressure = 1000.0,
            weather = "Cloudy",
            weatherDescription = "",
            weatherIconId = "10d"
        ),
        WeatherDetails(
            city = "Zagreb",
            temperature = 10.0,
            feelsLikeTemperature = 10.0,
            pressure = 1000.0,
            weather = "Cloudy",
            weatherDescription = "",
            weatherIconId = "10d"
        ),
        WeatherDetails(
            city = "Split",
            temperature = 10.0,
            feelsLikeTemperature = 10.0,
            pressure = 1000.0,
            weather = "Cloudy",
            weatherDescription = "",
            weatherIconId = "10d"
        ),
        WeatherDetails(
            city = "Rijeka",
            temperature = 10.0,
            feelsLikeTemperature = 10.0,
            pressure = 1000.0,
            weather = "Cloudy",
            weatherDescription = "",
            weatherIconId = "10d"
        )
    )

    fun getWeather(): WeatherDetails = WeatherDetails(
        city = "Osijek",
        temperature = 10.0,
        feelsLikeTemperature = 10.0,
        pressure = 1000.0,
        weather = "Cloudy",
        weatherDescription = "",
        weatherIconId = "10d"
    )
}
