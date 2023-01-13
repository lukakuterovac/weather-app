package com.example.weatherapp

import android.app.Application
import android.util.Log
import com.example.weatherapp.data.di.dataModule
import com.example.weatherapp.ui.home.di.homeScreenModule
import com.example.weatherapp.ui.weatherDetails.di.weatherDetailsScreenModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class WeatherApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@WeatherApp)
            modules(dataModule, weatherDetailsScreenModule, homeScreenModule)
        }

        Log.d("WeatherApp", "App started")
    }
}
