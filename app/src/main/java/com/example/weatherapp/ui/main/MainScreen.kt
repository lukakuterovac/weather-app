package com.example.weatherapp.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherapp.R
import com.example.weatherapp.navigation.NavigationItem
import com.example.weatherapp.navigation.WEATHER_ID_KEY
import com.example.weatherapp.navigation.WeatherDetailsDestination
import com.example.weatherapp.ui.home.HomeScreenRoute
import com.example.weatherapp.ui.theme.Primary
import com.example.weatherapp.ui.weatherDetails.WeatherDetailsRoute
import com.example.weatherapp.ui.weatherDetails.WeatherDetailsViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val showBackIcon by remember {
        derivedStateOf {
            when (navBackStackEntry?.destination?.route) {
                WeatherDetailsDestination.route -> true
                else -> false
            }
        }
    }
    Scaffold(
        topBar = {
            TopBar(navigationIcon = {
                if (showBackIcon) BackIcon(onBackClick = navController::popBackStack)
            })
        }
    ) { padding ->
        Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
            NavHost(
                navController = navController,
                startDestination = NavigationItem.HomeDestination.route,
                modifier = Modifier.padding(padding)
            ) {
                composable(NavigationItem.HomeDestination.route) {
                    HomeScreenRoute(
                        homeScreenViewModel = getViewModel(),
                        onWeatherCardClick = { city ->
                            navController.navigate(
                                WeatherDetailsDestination.createNavigationRoute(city)
                            )
                        },
                        onSearchButtonClick = { city ->
                            navController.navigate(
                                WeatherDetailsDestination.createNavigationRoute(
                                    city
                                )
                            )
                        })
                }
                composable(
                    route = WeatherDetailsDestination.route,
                    arguments = listOf(navArgument(WEATHER_ID_KEY) { type = NavType.StringType }),
                ) {
                    val city = it.arguments?.getString(WEATHER_ID_KEY)
                    val weatherDetailsViewModel =
                        getViewModel<WeatherDetailsViewModel>(parameters = { parametersOf(city) })
                    WeatherDetailsRoute(weatherDetailsViewModel = weatherDetailsViewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(navigationIcon: @Composable (() -> Unit)? = null) {
    if (navigationIcon != null) {
        CenterAlignedTopAppBar(navigationIcon = navigationIcon,
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Primary),
            title = {
                Image(
                    painter = painterResource(id = R.drawable.ic_app_logo),
                    contentDescription = null
                )
            })
    } else {
        CenterAlignedTopAppBar(colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Primary
        ),
            title = {
                Image(
                    painter = painterResource(id = R.drawable.ic_app_logo),
                    contentDescription = null
                )
            })
    }
}

@Composable
private fun BackIcon(onBackClick: () -> Unit, modifier: Modifier = Modifier) {
    IconButton(
        onClick = { onBackClick() }, modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back_button_icon),
            contentDescription = null
        )
    }
}
