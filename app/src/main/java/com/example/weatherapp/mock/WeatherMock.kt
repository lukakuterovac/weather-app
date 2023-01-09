package com.example.weatherapp.mock

import com.example.weatherapp.model.Weather
import com.example.weatherapp.model.WeatherDetails

object WeatherMock {
    fun getWeatherList(): List<Weather> = listOf(
        Weather(
            city = "Osijek",
            temperature = 10.0,
            weather = "Cloudy",
            weatherIconId = "10d"
        ),
        Weather(
            city = "Zagreb",
            temperature = 10.0,
            weather = "Cloudy",
            weatherIconId = "10d"
        ),
        Weather(
            city = "Split",
            temperature = 10.0,
            weather = "Cloudy",
            weatherIconId = "10d"
        ),
        Weather(
            city = "Rijeka",
            temperature = 10.0,
            weather = "Cloudy",
            weatherIconId = "10d"
        )
    )

    fun getWeather(): WeatherDetails = WeatherDetails(
        city = "Osijek",
        temperature = 10.0,
        feelsLikeTemperature = 10.0,
        pressure = 1000.0,
        humidity = 65.0,
        weather = "Cloudy",
        weatherDescription = "Scattered clouds",
        weatherIconId = "10d",
        isFavorite = true
    )
}
