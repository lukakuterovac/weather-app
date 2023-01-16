package com.example.weatherapp.data.repository

import com.example.weatherapp.data.network.WeatherService
import com.example.weatherapp.model.Weather
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CurrentLocationRepositoryImpl(
    private val weatherService: WeatherService,
    private val bgDispatcher: CoroutineDispatcher
) : CurrentLocationRepository {
    var currentCoordinates: Pair<Double, Double> = Pair(0.0, 0.0)

    private val current = flow {
        val weather = weatherService.fetchWeatherDetails(currentCoordinates).toWeather(true)
        emit(
            listOf<Weather>(
                Weather(
                    city = weather.city,
                    temperature = weather.temperature,
                    weather = weather.weather,
                    weatherIconId = weather.weatherIconId,
                    isFavorite = true
                )
            )
        )
    }

    override fun currentLocation(): Flow<List<Weather>> = current

    override suspend fun setCurrentLocation(coordinates: Pair<Double, Double>) {
        currentCoordinates = coordinates
    }

}
