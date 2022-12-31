package com.example.weatherapp.ui.home.mapper

import com.example.weatherapp.model.Weather
import com.example.weatherapp.ui.home.HomeScreenViewState

interface HomeScreenMapper {
    fun toHomeScreenViewState(
        weathers: List<Weather>
    ): HomeScreenViewState
}
