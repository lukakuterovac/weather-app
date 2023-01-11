package com.example.weatherapp.ui.home.mapper

import com.example.weatherapp.model.Weather
import com.example.weatherapp.ui.component.WeatherViewState
import com.example.weatherapp.ui.home.HomeScreenViewState
import com.example.weatherapp.ui.home.HomeWeatherViewState

class HomeScreenMapperImpl : HomeScreenMapper {
    override fun toHomeScreenViewState(weathers: List<Weather>): HomeScreenViewState {
        return HomeScreenViewState(weathers.map { weather ->
            HomeWeatherViewState(
                weatherViewState = WeatherViewState(
                    city = weather.city,
                    temperature = weather.temperature,
                    weather = weather.weather,
                    weatherIconId = weather.weatherIconId
                )
            )
        })
    }
}
