package com.example.weatherapp.data.di

import androidx.room.Room
import com.example.weatherapp.data.database.WeatherAppDatabase
import com.example.weatherapp.data.repository.WeatherRepository
import com.example.weatherapp.data.repository.WeatherRepositoryImpl
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

private const val APP_DATABASE_NAME = "app_database.db"

val dataModule = module {
    single<WeatherRepository> {
        WeatherRepositoryImpl(get(), get(), Dispatchers.IO)
    }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            WeatherAppDatabase::class.java,
            APP_DATABASE_NAME
        ).build()
    }
    single {
        get<WeatherAppDatabase>().getWeatherDao()
    }
}
