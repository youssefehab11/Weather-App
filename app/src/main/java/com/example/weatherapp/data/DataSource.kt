package com.example.weatherapp.data

import com.example.weatherapp.R
import com.example.weatherapp.model.CityCard

object DataSource {
    val cityItem = listOf<CityCard>(
        CityCard(
            temperature = 6,
            city = R.string.tokyo,
            icon = R.drawable.spark_cloud,
            weather = Weather.SPARK_CLOUD
        ),
        CityCard(
            temperature = 19,
            city = R.string.new_york,
            icon = R.drawable.sun_wind,
            weather = Weather.SUN_WIND
        ),
        CityCard(
            temperature = 13,
            city = R.string.london,
            icon = R.drawable.rain_cloud_sun,
            weather = Weather.RAIN_CLOUD_SUN
        )

    )
}