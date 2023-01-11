package com.example.weatherapp.ui.home

import com.example.weatherapp.ui.component.WeatherViewState

data class HomeWeatherViewState(
    val weatherViewState: WeatherViewState
)

data class HomeScreenViewState(
    val weathers: List<HomeWeatherViewState>
)
