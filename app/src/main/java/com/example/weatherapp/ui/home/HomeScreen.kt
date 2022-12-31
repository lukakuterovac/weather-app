package com.example.weatherapp.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.R
import com.example.weatherapp.mock.WeatherMock
import com.example.weatherapp.ui.component.WeatherCard
import com.example.weatherapp.ui.component.WeatherViewState
import com.example.weatherapp.ui.home.mapper.HomeScreenMapper
import com.example.weatherapp.ui.home.mapper.HomeScreenMapperImpl
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.ui.theme.spacing

val homeScreenMapper: HomeScreenMapper = HomeScreenMapperImpl()
val weathers = WeatherMock.getWeatherList()

val homeWeatherViewState = mutableStateOf(
    homeScreenMapper.toHomeScreenViewState(
        weathers
    )
)

@Composable
fun HomeScreen(
    viewState: HomeScreenViewState,
    onWeatherCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.forecasts),
            style = MaterialTheme.typography.h1,
            modifier = Modifier.padding(MaterialTheme.spacing.medium)
        )
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
            contentPadding = PaddingValues(
                start = MaterialTheme.spacing.medium,
                end = MaterialTheme.spacing.medium
            ),
            userScrollEnabled = true
        ) {
            items(
                items = viewState.weathers,
                key = { weather -> weather.city }
            ) { weather ->
                WeatherCard(
                    weatherCardViewState = WeatherViewState(
                        city = weather.city,
                        temperature = weather.temperature,
                        weather = weather.weather,
                        weatherIconId = weather.weatherIconId
                    ),
                    onClick = onWeatherCardClick,
                    modifier = Modifier
                        .height(dimensionResource(id = R.dimen.weather_card_height))
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    WeatherAppTheme {
        HomeScreen(
            viewState = homeWeatherViewState.value,
            onWeatherCardClick = { }
        )
    }
}
