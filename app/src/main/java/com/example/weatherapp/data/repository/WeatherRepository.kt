package com.example.weatherapp.data.repository

import com.example.weatherapp.model.Weather
import com.example.weatherapp.model.WeatherDetails
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun weatherDetails(city: String): Flow<WeatherDetails>
    fun favoriteWeathers(): Flow<List<Weather>>
    suspend fun addWeatherToFavorite(city: String)
    suspend fun removeWeatherFromFavorites(city: String)
    suspend fun toggleFavorite(city: String)
}
