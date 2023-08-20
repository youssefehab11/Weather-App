package com.example.weatherapp.ui

import androidx.lifecycle.ViewModel
import com.example.weatherapp.model.CityCard
import com.example.weatherapp.model.WeatherUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class WeatherViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    fun updateCityInfo(cityCard: CityCard){
        _uiState.update {
            currentState ->
            currentState.copy(
                city = cityCard.city,
                temperature = cityCard.temperature,
                icon = cityCard.icon
            )
        }
    }
}