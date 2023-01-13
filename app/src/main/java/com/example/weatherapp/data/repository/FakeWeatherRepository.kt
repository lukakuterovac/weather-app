package com.example.weatherapp.data.repository

import android.util.Log
import com.example.weatherapp.mock.FavoritesDBMock
import com.example.weatherapp.mock.WeatherMock
import com.example.weatherapp.model.Weather
import com.example.weatherapp.model.WeatherDetails
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest

class FakeWeatherRepository(
    private val ioDispatcher: CoroutineDispatcher,
) : WeatherRepository {
    private val fakeWeathers = WeatherMock.getWeatherList().toMutableList()
    private val weathers: Flow<List<Weather>> = FavoritesDBMock.favoriteCities
        .mapLatest { favoriteCities ->
            fakeWeathers.map { weather ->
                weather.copy(isFavorite = favoriteCities.contains(weather.city))
            }
        }
        .flowOn(ioDispatcher)

    override fun weatherDetails(city: String): Flow<WeatherDetails> =
        FavoritesDBMock.favoriteCities.mapLatest { favoriteCities ->
            val details = getWeatherDetails(city)
            val weather = details.weather.copy(isFavorite = favoriteCities.contains(city))
            details.copy(weather = weather)
        }.flowOn(ioDispatcher)

    override fun favoriteWeathers(): Flow<List<Weather>> =
        weathers.map { it.filter { weather -> weather.isFavorite } }

    override suspend fun addWeatherToFavorite(city: String) {
        FavoritesDBMock.insert(city)
    }

    override suspend fun removeWeatherFromFavorites(city: String) {
        FavoritesDBMock.delete(city)
    }

    override suspend fun toggleFavorite(city: String) {
        if (FavoritesDBMock.favoriteCities.value.contains(city)) {
            removeWeatherFromFavorites(city)
        } else {
            addWeatherToFavorite(city)
        }
    }

    private fun getWeatherDetails(city: String): WeatherDetails {
        val weather = findMatchingWeather(city)
        val details = WeatherMock.getWeatherDetails()
        Log.d("FAKEREPO", findMatchingWeather(city).toString())
        return WeatherDetails(
            weather = weather,
            feelsLikeTemperature = details.feelsLikeTemperature,
            pressure = details.feelsLikeTemperature,
            humidity = details.humidity,
            weatherDescription = details.weatherDescription
        )
    }

    private fun findMatchingWeather(city: String): Weather = fakeWeathers.first { it.city == city }
}
