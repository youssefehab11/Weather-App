package com.example.weatherapp.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.data.DataSource

enum class WeatherAppScreens{
    Cities,
    CityWeatherDetail
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherApp(
    navController: NavHostController = rememberNavController(),
    viewModel: WeatherViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    NavHost(
        navController = navController,
        startDestination = WeatherAppScreens.Cities.name,
    ) {
        composable(route = WeatherAppScreens.Cities.name) {
            Cities (items = DataSource.cityItem){
                println(it)
                viewModel.updateCityInfo(it)
                navController.navigate(WeatherAppScreens.CityWeatherDetail.name)
            }
        }
        composable(route = WeatherAppScreens.CityWeatherDetail.name) {
            CityWeatherDetail(uiState)
        }
    }
}