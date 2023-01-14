package com.example.weatherapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteWeatherDao {
    @Query("SELECT * FROM favoriteWeathers")
    fun favorites(): Flow<List<DbFavoriteWeather>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: DbFavoriteWeather)

    @Query("DELETE FROM favoriteWeathers WHERE city=:city")
    suspend fun deleteWeather(city: String)
}
