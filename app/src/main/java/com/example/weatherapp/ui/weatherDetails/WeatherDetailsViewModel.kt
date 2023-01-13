package com.example.weatherapp.ui.weatherDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.repository.WeatherRepository
import com.example.weatherapp.ui.weatherDetails.mapper.WeatherDetailsScreenMapper
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class WeatherDetailsViewModel(
    city: String,
    private val weatherRepository: WeatherRepository,
    private val weatherDetailsScreenMapper: WeatherDetailsScreenMapper
) : ViewModel() {
    private val initialViewState = WeatherDetailsScreenViewState(
        city = "A",
        temperature = 0.0,
        feelsLikeTemperature = 0.0,
        pressure = 0.0,
        humidity = 0.0,
        weather = "",
        weatherDescription = "",
        weatherIconId = null,
        isFavorite = false
    )

    val weatherDetailsViewState = weatherRepository.weatherDetails(city).map { details ->
        weatherDetailsScreenMapper.toWeatherDetailsScreenViewState(details)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = initialViewState
    )

    fun toggleFavorite(city: String){
        viewModelScope.launch {
            weatherRepository.toggleFavorite(city)
        }
    }
}
