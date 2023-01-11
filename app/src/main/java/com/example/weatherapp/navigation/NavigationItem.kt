package com.example.weatherapp.navigation

const val HOME_ROUTE = "Home"

sealed class NavigationItem(
    override val route: String
) : WeatherAppDestination(route) {

    object HomeDestination : NavigationItem(
        route = HOME_ROUTE
    )

}
