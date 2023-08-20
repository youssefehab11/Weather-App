package com.example.weatherapp.model

import androidx.annotation.DrawableRes
import com.example.weatherapp.data.Days
import com.example.weatherapp.data.WeekDays

data class NextDays(
    val days: Days,
    val weekDays: WeekDays,
    @DrawableRes val icon: Int,
    val boneTemperature: Int,
    val microTemperature: Int
)
