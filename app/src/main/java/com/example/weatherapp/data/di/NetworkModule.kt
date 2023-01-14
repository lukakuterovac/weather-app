package com.example.weatherapp.data.di

import android.util.Log
import com.example.weatherapp.data.network.WeatherService
import com.example.weatherapp.data.network.WeatherServiceImpl
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single<WeatherService> { WeatherServiceImpl(client = get()) }

    single {
        HttpClient(Android) {
            expectSuccess = true;
            engine {
                connectTimeout = 100_000
                socketTimeout = 100_000
            };
            install(ContentNegotiation){
                json(Json {
                    isLenient = true;
                    ignoreUnknownKeys = true
                })
            }
            install(Logging) {
                level = LogLevel.BODY
                logger = object : Logger{
                    override fun log(message: String) {
                        Log.d("Ktor: ", message)
                    }
                }
            }
        }
    }
}
