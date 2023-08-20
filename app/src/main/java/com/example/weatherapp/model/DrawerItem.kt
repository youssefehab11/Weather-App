package com.example.weatherapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class DrawerItem(
    @DrawableRes val icon: Int,
    @StringRes val text: Int
)
