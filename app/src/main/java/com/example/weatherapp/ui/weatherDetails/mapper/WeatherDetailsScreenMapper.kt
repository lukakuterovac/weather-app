package com.example.weatherapp.ui.weatherDetails.mapper

import com.example.weatherapp.model.WeatherDetails
import com.example.weatherapp.ui.weatherDetails.WeatherDetailsScreenViewState

interface WeatherDetailsScreenMapper {
    fun toWeatherDetailsScreenViewState(
        weatherDetails: WeatherDetails
    ) : WeatherDetailsScreenViewState
}
