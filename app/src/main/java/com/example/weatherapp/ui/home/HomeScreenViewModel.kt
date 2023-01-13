package com.example.weatherapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.repository.WeatherRepository
import com.example.weatherapp.ui.home.mapper.HomeScreenMapper
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val weatherRepository: WeatherRepository,
    private val homeScreenMapper: HomeScreenMapper
) : ViewModel() {
    val weatherViewState: StateFlow<HomeScreenViewState> =
        weatherRepository.favoriteWeathers().map { weathers ->
            homeScreenMapper.toHomeScreenViewState(weathers)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = HomeScreenViewState(emptyList())
        )

    fun toggleFavorite(city: String) {
        viewModelScope.launch {
            weatherRepository.toggleFavorite(city)
        }
    }
}
