package com.example.weatherapp.model

import androidx.annotation.DrawableRes

data class TodayInfo(
    val time: Int,
    @DrawableRes val icon: Int,
    val temperature: Int,
    val isMorning: Boolean
)
