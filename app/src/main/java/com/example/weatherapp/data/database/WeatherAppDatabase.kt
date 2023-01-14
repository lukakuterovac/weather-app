package com.example.weatherapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DbFavoriteWeather::class],
    version = 1,
    exportSchema = false
)
abstract class WeatherAppDatabase : RoomDatabase() {
    abstract fun getWeatherDao(): FavoriteWeatherDao
}
