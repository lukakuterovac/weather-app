package com.example.weatherapp.ui.home.di

import com.example.weatherapp.ui.home.HomeScreenViewModel
import com.example.weatherapp.ui.home.mapper.HomeScreenMapper
import com.example.weatherapp.ui.home.mapper.HomeScreenMapperImpl
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val homeScreenModule = module {
    viewModel {
        HomeScreenViewModel(get(), get())
    }
    single<HomeScreenMapper> { HomeScreenMapperImpl() }
}
