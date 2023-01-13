package com.example.weatherapp.mock

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object FavoritesDBMock {
    private val cities = MutableStateFlow(setOf<String>())
    val favoriteCities: StateFlow<Set<String>> = cities.asStateFlow()
    fun insert(city: String) = cities.update { it.plus(city) }
    fun delete(city: String) = cities.update { it.minus(city) }
}
