package com.example.weatherapp.ui.weatherDetails.di

import com.example.weatherapp.ui.weatherDetails.WeatherDetailsViewModel
import com.example.weatherapp.ui.weatherDetails.mapper.WeatherDetailsScreenMapper
import com.example.weatherapp.ui.weatherDetails.mapper.WeatherDetailsScreenMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val weatherDetailsScreenModule = module {
    viewModel { parameters ->
        WeatherDetailsViewModel(
            city = parameters.get(),
            weatherRepository = get(),
            weatherDetailsScreenMapper = get()
        )
    }
    single<WeatherDetailsScreenMapper> { WeatherDetailsScreenMapperImpl() }
}
