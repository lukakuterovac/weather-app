package com.example.weatherapp.data.repository

import com.example.weatherapp.model.Weather
import kotlinx.coroutines.flow.Flow

interface CurrentLocationRepository {
    fun currentLocation() : Flow<Weather>
    suspend fun setCurrentLocation(coordinates: Pair<Double, Double>)
}
