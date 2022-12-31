package com.example.weatherapp.ui.home.mapper

import com.example.weatherapp.model.Weather
import com.example.weatherapp.ui.home.HomeScreenViewState

class HomeScreenMapperImpl : HomeScreenMapper {
    override fun toHomeScreenViewState(weathers: List<Weather>): HomeScreenViewState {
        return HomeScreenViewState(weathers)
    }
}
