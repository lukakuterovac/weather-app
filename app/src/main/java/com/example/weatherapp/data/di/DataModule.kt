package com.example.weatherapp.data.di

import com.example.weatherapp.data.repository.FakeWeatherRepository
import com.example.weatherapp.data.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dataModule = module {
    single<WeatherRepository> {
        FakeWeatherRepository(ioDispatcher = Dispatchers.IO)
    }
}
