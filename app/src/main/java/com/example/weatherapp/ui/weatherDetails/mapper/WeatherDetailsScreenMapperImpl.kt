package com.example.weatherapp.ui.weatherDetails.mapper

import com.example.weatherapp.model.WeatherDetails
import com.example.weatherapp.ui.weatherDetails.WeatherDetailsScreenViewState

class WeatherDetailsScreenMapperImpl : WeatherDetailsScreenMapper {
    override fun toWeatherDetailsScreenViewState(weatherDetails: WeatherDetails): WeatherDetailsScreenViewState {
        return WeatherDetailsScreenViewState(
            city = weatherDetails.city,
            temperature = weatherDetails.temperature,
            feelsLikeTemperature = weatherDetails.feelsLikeTemperature,
            pressure = weatherDetails.pressure,
            humidity = weatherDetails.humidity,
            weather = weatherDetails.weather,
            weatherDescription = weatherDetails.weatherDescription,
            weatherIconId = weatherDetails.weatherIconId
        )
    }
}
