package com.example.weatherapp

import android.app.Application
import android.util.Log

class WeatherApp : Application() {
    override fun onCreate() {
        super.onCreate()

        Log.d("WeatherApp", "App started")
    }
}
