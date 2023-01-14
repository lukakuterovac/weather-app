package com.example.weatherapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoriteWeathers")
data class DbFavoriteWeather(
    @PrimaryKey val city: String,
    val temperature: Double,
    val weather: String,
    val weatherIconId: String
)
