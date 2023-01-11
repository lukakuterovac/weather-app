package com.example.weatherapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
fun HomeScreenRoute(
    onWeatherCardClick: (String) -> Unit,
    onSearchButtonClick: (String) -> Unit
) {
    val homeWeather by remember {
        homeWeatherViewState
    }
    HomeScreen(
        viewState = homeWeather,
        onWeatherCardClick = onWeatherCardClick,
        onSearchButtonClick = onSearchButtonClick
    )
}

@Composable
fun HomeScreen(
    viewState: HomeScreenViewState,
    onWeatherCardClick: (String) -> Unit,
    onSearchButtonClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(
            text = stringResource(id = R.string.forecasts),
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(MaterialTheme.spacing.medium)
        )
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
            contentPadding = PaddingValues(
                horizontal = MaterialTheme.spacing.medium,
                vertical = MaterialTheme.spacing.small
            ),
            modifier = Modifier.weight(1F),
            userScrollEnabled = true
        ) {
            items(
                items = viewState.weathers,
                key = { weather -> weather.weatherViewState.city }
            ) { weather ->
                WeatherCard(
                    weatherCardViewState = WeatherViewState(
                        city = weather.weatherViewState.city,
                        temperature = weather.weatherViewState.temperature,
                        weather = weather.weatherViewState.weather,
                        weatherIconId = weather.weatherViewState.weatherIconId
                    ),
                    onClick = { onWeatherCardClick(weather.weatherViewState.city) },
                    modifier = Modifier
                        .height(dimensionResource(id = R.dimen.weather_card_height))
                        .fillMaxWidth()
                )
            }
        }
        SearchBar(onSearchButtonClick)
    }
}

@Composable
private fun SearchBar(
    onSearchButtonClick: (String) -> Unit
) {
    var query by remember { mutableStateOf(TextFieldValue("")) }
    Surface(
        color = MaterialTheme.colors.surface,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = MaterialTheme.spacing.medium,
                    vertical = MaterialTheme.spacing.small
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = {
                    query = it
                },
                modifier = Modifier.width(250.dp),
                label = { Text(text = "Enter a city name") },
                shape = RoundedCornerShape(25),
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_search_icon),
                        contentDescription = null,
                        modifier = Modifier
                            .size(12.dp)
                            .alpha(0.5F),
                        contentScale = ContentScale.Crop,
                    )
                },
                singleLine = true
            )
            Spacer(modifier = Modifier.weight(1F))
            Button(
                onClick = { onSearchButtonClick(query.toString()) },
                shape = RoundedCornerShape(25)
            ) {
                Text(text = "Search")
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
            onWeatherCardClick = { },
            onSearchButtonClick = { }
        )
    }
}
