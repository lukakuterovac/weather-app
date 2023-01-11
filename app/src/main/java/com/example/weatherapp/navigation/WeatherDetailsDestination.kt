package com.example.weatherapp.navigation

const val WEATHER_DETAILS_ROUTE = "WeatherDetails"
const val WEATHER_ID_KEY = "city"
const val WEATHER_DETAILS_ROUTE_WITH_PARAMS = "$WEATHER_DETAILS_ROUTE/{$WEATHER_ID_KEY}"

object WeatherDetailsDestination : WeatherAppDestination(WEATHER_DETAILS_ROUTE_WITH_PARAMS) {
    fun createNavigationRoute(city: String): String = "$WEATHER_DETAILS_ROUTE/$city"
}
