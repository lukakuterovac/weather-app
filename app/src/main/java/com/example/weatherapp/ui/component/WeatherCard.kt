package com.example.weatherapp.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherapp.mock.WeatherMock
import com.example.weatherapp.ui.theme.spacing
import com.example.weatherapp.R

private const val WEATHER_ICON_BASE_URL = "https://openweathermap.org/img/wn/"
private const val WEATHER_ICON_EXTENSION = "@2x.png"

data class WeatherViewState(
    val city: String,
    val temperature: Double,
    val weather: String,
    val weatherIconId: String?
)

@Composable
fun WeatherCard(
    weatherCardViewState: WeatherViewState,
    onClick: () -> Unit,
    modifier: Modifier
) {
    Card(
        modifier = modifier.clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = 10.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = weatherCardViewState.city,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(MaterialTheme.spacing.small)
            )
            Spacer(modifier = Modifier.weight(1F))
            WeatherInfo(weatherCardViewState = weatherCardViewState)
        }
    }
}

@Composable
private fun WeatherInfo(weatherCardViewState: WeatherViewState) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(MaterialTheme.spacing.extraSmall)
    ) {
        Text(
            text = String.format("%.0f", weatherCardViewState.temperature) + "°C",
            fontSize = 16.sp
        )
        Text(
            text = weatherCardViewState.weather.uppercase(),
            fontSize = 10.sp,
            color = Color.Gray
        )
    }
    AsyncImage(
        model = WEATHER_ICON_BASE_URL + weatherCardViewState.weatherIconId + WEATHER_ICON_EXTENSION,
        contentDescription = null,
        modifier = Modifier
            .size(dimensionResource(id = R.dimen.weather_card_icon_size))
            .padding(end = MaterialTheme.spacing.small)
    )
}

@Preview(showBackground = true)
@Composable
private fun WeatherCardPreview() {
    val weather = WeatherMock.getWeather()

    WeatherCard(
        weatherCardViewState = WeatherViewState(
            city = weather.city,
            temperature = weather.temperature,
            weather = weather.weather,
            weatherIconId = weather.weatherIconId
        ),
        onClick = { },
        modifier = Modifier
            .size(width = 250.dp, height = 70.dp)
    )
}
