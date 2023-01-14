package com.example.weatherapp.data.repository

import com.example.weatherapp.data.database.DbFavoriteWeather
import com.example.weatherapp.data.database.FavoriteWeatherDao
import com.example.weatherapp.data.network.WeatherService
import com.example.weatherapp.model.Weather
import com.example.weatherapp.model.WeatherDetails
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

private const val STOP_TIMEOUT_MILLIS = 1000L

class WeatherRepositoryImpl(
    private val weatherService: WeatherService,
    private val weatherDao: FavoriteWeatherDao,
    private val bgDispatcher: CoroutineDispatcher
) : WeatherRepository {
    private val favorites = weatherDao.favorites().map { dbFavoriteWeathers ->
        dbFavoriteWeathers.map { favorite ->
            val weather = weatherService.fetchWeatherDetails(favorite.city).toWeather(true)
            Weather(
                city = weather.city,
                temperature = weather.temperature,
                weather = weather.weather,
                weatherIconId = weather.weatherIconId,
                isFavorite = true
            )
        }
    }.shareIn(
        scope = CoroutineScope(bgDispatcher),
        started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILLIS),
        replay = 1
    )

    override fun weatherDetails(city: String): Flow<WeatherDetails> = flow {
        emit(weatherService.fetchWeatherDetails(city) to { } )
    }.flatMapLatest { (weatherResponse, _) ->
        weatherDao.favorites().map { favoriteWeathers ->
            weatherResponse.toWeatherDetails(
                isFavorite = favoriteWeathers.any { it.city == weatherResponse.city },
            )
        }
    }.flowOn(bgDispatcher)

    override fun favoriteWeathers(): Flow<List<Weather>> = favorites

    override suspend fun addWeatherToFavorite(city: String) {
        val weather = weatherService.fetchWeatherDetails(city).toWeather(true)
        weatherDao.insertWeather(
            DbFavoriteWeather(
                city = weather.city,
                temperature = weather.temperature,
                weather = weather.weather,
                weatherIconId = weather.weatherIconId ?: ""
            )
        )
    }

    private suspend fun findWeather(city: String): Weather {
        favorites.first().forEach { weather ->
            if (weather.city == city) {
                return weather
            }
        }
        throw java.lang.IllegalArgumentException("No city called $city found!")
    }

    override suspend fun removeWeatherFromFavorites(city: String) = weatherDao.deleteWeather(city)

    override suspend fun toggleFavorite(city: String) {
        val favoriteWeathers = favorites.first()
        val favoriteWeather = favoriteWeathers.filter {
            it.city == city
        }
        if (favoriteWeather.isNotEmpty()) {
            removeWeatherFromFavorites(city)
        } else {
            addWeatherToFavorite(city)
        }
    }

}
