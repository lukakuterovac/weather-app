package com.example.weatherapp.ui.weatherDetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherapp.R
import com.example.weatherapp.ui.component.FavoriteButton
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.ui.theme.spacing
import org.koin.androidx.compose.getViewModel

private const val WEATHER_ICON_BASE_URL = "https://openweathermap.org/img/wn/"
private const val WEATHER_ICON_EXTENSION = "@2x.png"

@Composable
fun WeatherDetailsRoute(weatherDetailsViewModel: WeatherDetailsViewModel) {
    val viewState: WeatherDetailsScreenViewState by weatherDetailsViewModel.weatherDetailsViewState.collectAsState()
    WeatherDetailsScreen(viewState, weatherDetailsViewModel::toggleFavorite)
}

@Composable
fun WeatherDetailsScreen(
    viewState: WeatherDetailsScreenViewState,
    onFavoriteButtonClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.weight(1F))
        WeatherDetailsHeader(viewState)
        Spacer(modifier = Modifier.weight(1F))
        WeatherDetails(viewState, onFavoriteButtonClick)
        Spacer(modifier = Modifier.weight(1F))
    }
}

@Composable
private fun WeatherDetailsHeader(viewState: WeatherDetailsScreenViewState) {
    Card(
        modifier = Modifier
            .height(250.dp)
            .width(250.dp),
        elevation = 10.dp,
        shape = RoundedCornerShape(12.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1F))
            Text(
                text = viewState.city,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
            )
            Text(
                text = String.format("%.0f", viewState.temperature) + "°",
                fontSize = 70.sp,
                fontWeight = FontWeight.Medium
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = viewState.weather, fontSize = 16.sp)
                AsyncImage(
                    model = WEATHER_ICON_BASE_URL + viewState.weatherIconId + WEATHER_ICON_EXTENSION,
                    contentDescription = viewState.weather,
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.weather_details_icon_size))
                )
            }
            Spacer(modifier = Modifier.weight(1F))
        }
    }
}

@Composable
private fun WeatherDetails(
    viewState: WeatherDetailsScreenViewState,
    onFavoriteButtonClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .height(300.dp)
            .width(300.dp)
            .padding(top = 50.dp),
        elevation = 10.dp,
        shape = RoundedCornerShape(12.dp),
    ) {
        Column {
            Spacer(modifier = Modifier.weight(1F))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Weather details",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(MaterialTheme.spacing.small)
                )
                Spacer(modifier = Modifier.weight(1F))
                FavoriteButton(
                    isFavorite = viewState.isFavorite,
                    onClick = { onFavoriteButtonClick(viewState.city) },
                    modifier = Modifier.padding(MaterialTheme.spacing.small)
                )
            }
            Spacer(modifier = Modifier.weight(1F))
            Divider()
            Spacer(modifier = Modifier.weight(1F))
            WeatherDetailsSection(
                section = "Description",
                measurement = viewState.weatherDescription.capitalize(Locale.current)
            )
            Spacer(modifier = Modifier.weight(1F))
            Divider()
            Spacer(modifier = Modifier.weight(1F))
            WeatherDetailsSection(
                section = "Feels like",
                measurement = String.format("%.0f", viewState.feelsLikeTemperature) + "°"
            )
            Spacer(modifier = Modifier.weight(1F))
            Divider()
            Spacer(modifier = Modifier.weight(1F))
            WeatherDetailsSection(
                section = "Humidity",
                measurement = String.format("%.0f", viewState.humidity) + "%"
            )
            Spacer(modifier = Modifier.weight(1F))
            Divider()
            Spacer(modifier = Modifier.weight(1F))
            WeatherDetailsSection(
                section = "Pressure",
                measurement = String.format("%.0f", viewState.pressure) + "hPa"
            )
            Spacer(modifier = Modifier.weight(1F))
        }
    }
}

@Composable
private fun WeatherDetailsSection(section: String, measurement: String) {
    val spacing = Modifier.padding(MaterialTheme.spacing.small)
    Row {
        Text(text = section, fontSize = 16.sp, modifier = spacing)
        Spacer(modifier = Modifier.weight(1F))
        Text(text = measurement, fontSize = 16.sp, modifier = spacing)
    }
}

@Preview
@Composable
fun WeatherDetailsScreenPreview() {
    WeatherAppTheme {
        WeatherDetailsScreen(viewState = getViewModel(), {})
    }
}
