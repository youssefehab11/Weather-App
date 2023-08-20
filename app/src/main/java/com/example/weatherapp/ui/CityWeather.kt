package com.example.weatherapp.ui


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.data.CheckValue
import com.example.weatherapp.data.Days
import com.example.weatherapp.data.Directions
import com.example.weatherapp.data.GetDay
import com.example.weatherapp.data.WeekDays
import com.example.weatherapp.model.DrawerItem
import com.example.weatherapp.model.NextDays
import com.example.weatherapp.model.Space
import com.example.weatherapp.model.TodayInfo
import com.example.weatherapp.model.WeatherInfo
import com.example.weatherapp.model.WeatherUiState
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.ui.theme.arcBackgroundColor
import com.example.weatherapp.ui.theme.button_add_city_color
import com.example.weatherapp.ui.theme.city_weather_black_color
import com.example.weatherapp.ui.theme.city_weather_dark_gray_color
import com.example.weatherapp.ui.theme.city_weather_gray_color
import com.example.weatherapp.ui.theme.rainCloudSunGradient
import com.example.weatherapp.ui.theme.sunColor
import com.example.weatherapp.ui.theme.todayCardColorGradient
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CityWeatherDetail(
    uiState: WeatherUiState
) {

    var drawerIsOpen by rememberSaveable {
        mutableStateOf(false)
    }
    Box {
        if (drawerIsOpen) {
            Column(modifier = Modifier.padding(start = 18.dp, top = 180.dp)) {
                Drawer(drawerItem = DrawerItem(R.drawable.add, R.string.add_city))
                Drawer(drawerItem = DrawerItem(R.drawable.settings, R.string.settings))
                Drawer(drawerItem = DrawerItem(R.drawable.map, R.string.map))
            }
        }
        Surface(
            modifier = if (drawerIsOpen) {
                Modifier
                    .rotate(15f)
                    .absoluteOffset(x = 180.dp, y = 40.dp)
                    .shadow(
                        1.dp,
                        clip = false,
                        shape = RoundedCornerShape(30.dp),
                        spotColor = Color.White
                    )
                    .clip(shape = RoundedCornerShape(30.dp))
                    .fillMaxSize()
            } else {
                Modifier

                    .fillMaxSize()
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(
                        rememberScrollState(),
                        enabled = !drawerIsOpen
                    )
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    IconButton(
                        onClick = { drawerIsOpen = !drawerIsOpen },
                        modifier = Modifier
                            .padding(12.dp)
                    ){
                        Icon(
                            painter = painterResource(id = R.drawable.menu),
                            contentDescription = null,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                }
                TodayCityWeather(uiState)
                Space().SpaceHeight(value = 23)

                WeatherWithTime()
                Space().SpaceHeight(value = 46)
                NextDaysCard()
                Space().SpaceHeight(value = 31)
                HumidityPercentage(72f)
                Column {
                    Text(
                        text = stringResource(id = R.string.sun_rise_sun_set),
                        style = MaterialTheme.typography.bodyMedium,
                        color = city_weather_black_color,
                        modifier = Modifier.padding(start = 20.dp, top = 10.dp)
                    )
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center

                    ) {
                        SunRise(40f)
                        SunSet()
                    }
                }
                Space().SpaceHeight(value = 10)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height = 1.dp)
                        .background(city_weather_gray_color)
                )
                WindStatus(Directions.NORTH, 12)
            }
        }
    }
}

@Composable
fun Drawer(drawerItem: DrawerItem) {
    Row(modifier = Modifier.padding(vertical = 10.dp)) {
        Image(
            painter = painterResource(id = drawerItem.icon), contentDescription = null,
            modifier = Modifier.size(30.dp),
            colorFilter = ColorFilter.tint(city_weather_black_color)
        )
        Space().SpaceWidth(value = 15)
        Text(
            text = stringResource(id = drawerItem.text),
            style = MaterialTheme.typography.titleLarge
        )

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TodayCityWeather(uiState: WeatherUiState) {
    val currentDate = LocalDate.now()
    Text(
        text = stringResource(id = R.string.today),
        style = MaterialTheme.typography.labelSmall,
        color = city_weather_black_color,
        modifier = Modifier.padding(top = 8.dp, start = 20.dp)
    )
    Text(
        text = stringResource(id = uiState.city),
        style = MaterialTheme.typography.bodyMedium,
        color = city_weather_black_color,
        modifier = Modifier.padding(start = 20.dp)
    )
    Text(
        text = currentDate.toString(),
        style = MaterialTheme.typography.titleSmall,
        color = city_weather_gray_color,
        modifier = Modifier.padding(start = 20.dp)
    )
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 33.dp)
    ) {
        Image(
            painter = painterResource(id = uiState.icon),
            contentDescription = null,
            modifier = Modifier.size(width = 79.dp, height = 89.dp)
        )
        Box(
            modifier = Modifier
                .size(width = 1.dp, height = 42.dp)
                .background(city_weather_gray_color)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = if (uiState.temperature < 10) "0${uiState.temperature}${Typography.degree}"
                else "${uiState.temperature}${Typography.degree}",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 40.sp,
                color = city_weather_black_color
            )
            Text(
                text = stringResource(id = R.string.rainy_shower),
                style = MaterialTheme.typography.titleSmall,
                color = city_weather_gray_color,
            )
        }
    }
    Space().SpaceHeight(value = 27)
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        WeatherInformation(
            weatherInfo = WeatherInfo(
                R.drawable.wind,
                R.string.speed,
                CheckValue.SPEED
            )
        )
        WeatherInformation(
            weatherInfo = WeatherInfo(
                R.drawable.cloud,
                R.string.cloud_percentage,
                CheckValue.PERCENTAGE
            )
        )
        WeatherInformation(
            weatherInfo = WeatherInfo(
                R.drawable.water,
                R.string.rain_percentage,
                CheckValue.PERCENTAGE
            )
        )
    }
}

@Composable
fun WeatherInformation(weatherInfo: WeatherInfo) {
    val formatArgs = when (weatherInfo.checkValue) {
        CheckValue.SPEED -> "Km/h"
        else -> "%"
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.size(44.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = weatherInfo.icon),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
        Text(
            text = stringResource(id = weatherInfo.value, formatArgs),
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun WeatherWithTime() {

    Text(
        text = stringResource(id = R.string.today),
        style = MaterialTheme.typography.bodyMedium,
        color = city_weather_black_color,
        modifier = Modifier.padding(start = 20.dp)
    )
    Row(
        modifier = Modifier
            .padding(start = 20.dp)
            .horizontalScroll(rememberScrollState())
    ) {
        CardWeatherWithTime(
            todayInfo = TodayInfo(
                temperature = 13,
                icon = R.drawable.spark_cloud_outlined,
                time = 11,
                isMorning = true
            )
        )
        CardWeatherWithTime(
            todayInfo = TodayInfo(
                temperature = 13,
                icon = R.drawable.rain_cloud_sun,
                time = 12,
                isMorning = true
            )
        )
        CardWeatherWithTime(
            todayInfo = TodayInfo(
                temperature = 13,
                icon = R.drawable.rainy,
                time = 1,
                isMorning = false
            )
        )
        CardWeatherWithTime(
            todayInfo = TodayInfo(
                temperature = 13,
                icon = R.drawable.cloud_outlined,
                time = 2,
                isMorning = false
            )
        )
        CardWeatherWithTime(
            todayInfo = TodayInfo(
                temperature = 13,
                icon = R.drawable.sun_outline,
                time = 3,
                isMorning = false
            )
        )
    }
}

@Composable
fun CardWeatherWithTime(todayInfo: TodayInfo) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .size(width = 80.dp, height = 110.dp)
    ) {
        Box(
            modifier = if (todayInfo.time != 12)
                Modifier.background(button_add_city_color)
            else
                Modifier.background(todayCardColorGradient),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxSize()
            ) {
                if (todayInfo.isMorning)
                    if (todayInfo.time < 10)
                        Text(
                            text = "0${todayInfo.time}:00am",
                            style = MaterialTheme.typography.labelSmall,
                            fontSize = 8.sp,
                            color = city_weather_black_color,
                            modifier = Modifier.padding(top = 12.dp)
                        )
                    else
                        Text(
                            text = "${todayInfo.time}:00am",
                            style = MaterialTheme.typography.labelSmall,
                            fontSize = 12.sp,
                            color = city_weather_black_color,
                            modifier = Modifier.padding(top = 12.dp)
                        )
                else
                    if (todayInfo.time < 10)
                        Text(
                            text = "0${todayInfo.time}:00pm",
                            style = MaterialTheme.typography.labelSmall,
                            fontSize = 12.sp,
                            color = city_weather_black_color,
                            modifier = Modifier.padding(top = 12.dp)
                        )
                    else
                        Text(
                            text = "${todayInfo.time}:00pm",
                            style = MaterialTheme.typography.labelSmall,
                            fontSize = 12.sp,
                            color = city_weather_black_color,
                            modifier = Modifier.padding(top = 12.dp)
                        )
                Image(
                    painter = painterResource(id = todayInfo.icon),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
                Text(
                    text = "${todayInfo.temperature}${Typography.degree}",
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 12.sp,
                    color = city_weather_black_color,
                )
            }
        }
    }
    Space().SpaceWidth(value = 14)
}

@Composable
fun NextDaysCard() {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(12.dp)
    ) {
        Column(modifier = Modifier.padding(bottom = 35.dp)) {
            Text(
                text = stringResource(id = R.string.next_days),
                style = MaterialTheme.typography.bodyMedium,
                color = city_weather_black_color,
                modifier = Modifier.padding(20.dp)
            )
            Box(
                modifier = Modifier
                    .height(2.dp)
                    .padding(horizontal = 15.dp)
                    .fillMaxWidth()
                    .background(city_weather_gray_color)
            )
            NextDayItem(
                NextDays(
                    Days.TWENTY,
                    WeekDays.MONDAY,
                    R.drawable.rainy,
                    14,
                    12
                )
            )
            NextDayItem(
                NextDays(
                    Days.TWENTY_ONE,
                    WeekDays.TUESDAY,
                    R.drawable.cloud_outlined,
                    14,
                    13
                )
            )
            NextDayItem(
                NextDays(
                    Days.TWENTY_TWO,
                    WeekDays.WEDNESDAY,
                    R.drawable.sun_outline,
                    14,
                    12
                )
            )
            NextDayItem(
                NextDays(
                    Days.TWENTY_THREE,
                    WeekDays.THURSDAY,
                    R.drawable.rainy,
                    14,
                    12
                )
            )
            NextDayItem(
                NextDays(
                    Days.TWENTY_FOUR,
                    WeekDays.FRIDAY,
                    R.drawable.cloud_outlined,
                    14,
                    12
                )
            )
            NextDayItem(
                NextDays(
                    Days.TWENTY_FIVE,
                    WeekDays.SATURDAY,
                    R.drawable.sun_outline,
                    14,
                    12
                )
            )

        }
    }
}

@Composable
fun NextDayItem(nextDays: NextDays) {
    val day = GetDay().getDay(nextDays)
    val weekDay = GetDay().getWeekDay(nextDays)
    Column {

        Box(
            //verticalAlignment = Alignment.CenterVertically,
            //horizontalArrangement = Arrangement.SpaceEvenly,
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = "${weekDay},Feb $day",
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(start = 20.dp)
            )
            Image(
                painter = painterResource(id = nextDays.icon),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 210.dp)
                    .size(40.dp)
            )
            Text(
                text = "${nextDays.boneTemperature}${Typography.degree}/${nextDays.microTemperature}${Typography.degree}",
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(start = 300.dp)
            )
        }
        Box(
            modifier = Modifier
                .height(2.dp)
                .padding(horizontal = 15.dp)
                .fillMaxWidth()
                .background(city_weather_gray_color)
        )
    }

}

@Composable
fun HumidityPercentage(humidityPercentage: Float) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(id = R.string.comfort_level),
            style = MaterialTheme.typography.bodyMedium,
            color = city_weather_black_color,
            modifier = Modifier.padding(start = 20.dp)
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                DrawCustomArc(
                    initialValue = 140f,
                    sweepAngle = 260f,
                    value = humidityPercentage,
                    width = 400f,
                    height = 400f,
                    strokeWidth = 40f,
                    offsetX = 205f,
                    offsetY = 80f,
                    isDashed = false,
                    backgroundBrush = arcBackgroundColor,
                    foregroundBrush = rainCloudSunGradient
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "${humidityPercentage.toInt()}%",
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = city_weather_black_color,
                    )
                    Text(
                        text = stringResource(id = R.string.humidity),
                        style = MaterialTheme.typography.titleSmall,
                        fontSize = 16.sp,
                        color = city_weather_black_color,
                    )
                }
            }
        }
        Row {
            Text(
                text = stringResource(id = R.string.feels_like),
                style = MaterialTheme.typography.titleSmall,
                color = city_weather_gray_color,
                modifier = Modifier.padding(start = 30.dp)
            )
            Text(
                text = "10${Typography.degree}",
                style = MaterialTheme.typography.titleSmall,
                color = city_weather_black_color,
                modifier = Modifier.padding(start = 5.dp)
            )
            Space().SpaceWidth(value = 55)
            Box(
                modifier = Modifier
                    .size(width = 1.dp, height = 25.dp)
                    .background(city_weather_gray_color)
            )
            Text(
                text = stringResource(id = R.string.uv_index),
                style = MaterialTheme.typography.titleSmall,
                color = city_weather_gray_color,
                modifier = Modifier.padding(start = 30.dp)
            )
            Text(
                text = "0 low",
                style = MaterialTheme.typography.titleSmall,
                color = city_weather_black_color,
                modifier = Modifier.padding(start = 5.dp)
            )
        }
        Space().SpaceHeight(value = 20)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 1.dp)
                .background(city_weather_gray_color)
        )
    }
}

@Composable
fun DrawCustomArc(
    initialValue: Float,
    sweepAngle: Float,
    value: Float,
    width: Float,
    height: Float,
    strokeWidth: Float,
    offsetX: Float,
    offsetY: Float,
    isDashed: Boolean,
    backgroundBrush: Brush,
    foregroundBrush: Brush
) {
    Canvas(
        modifier = Modifier
            .size(300.dp, 200.dp)
            .drawBehind {
                drawArc(
                    brush = backgroundBrush,
                    startAngle = initialValue,
                    sweepAngle = sweepAngle,
                    size = Size(width = width, height = height),
                    useCenter = false,
                    topLeft = Offset(
                        x = offsetX,
                        y = offsetY
                    ),
                    style = if (isDashed) {
                        Stroke(
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f)),
                        )
                    } else {
                        Stroke(
                            width = strokeWidth,
                            cap = StrokeCap.Round
                        )
                    }
                )
            },
        onDraw = {
            drawArc(
                brush = foregroundBrush,
                startAngle = initialValue,
                sweepAngle = value * sweepAngle / 100f,
                size = Size(width = width, height = height),
                useCenter = false,
                style = if (isDashed) {
                    Stroke(
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f)),
                    )
                } else {
                    Stroke(
                        width = strokeWidth,
                        cap = StrokeCap.Round
                    )
                },
                topLeft = Offset(
                    x = offsetX,
                    y = offsetY
                ),
            )
        }
    )
}


@Composable
fun WindStatus(directions: Directions, speed: Int) {
    val direction = when (directions) {
        Directions.NORTH -> "North"
        Directions.EAST -> "East"
        Directions.WEST -> "West"
        else -> "South"
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)
    ) {
        Text(
            text = stringResource(id = R.string.wind),
            style = MaterialTheme.typography.bodyMedium,
            color = city_weather_black_color,
            modifier = Modifier.padding(start = 20.dp)
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.wind_fan),
                contentDescription = null,
                modifier = Modifier.size(75.dp)
            )
        }
        Space().SpaceHeight(value = 25)
        Row {
            Text(
                text = stringResource(id = R.string.direction),
                style = MaterialTheme.typography.titleSmall,
                color = city_weather_gray_color,
                modifier = Modifier.padding(start = 30.dp)
            )
            Text(
                text = direction,
                style = MaterialTheme.typography.titleSmall,
                color = city_weather_black_color,
                modifier = Modifier.padding(start = 5.dp)
            )
            Space().SpaceWidth(value = 40)
            Box(
                modifier = Modifier
                    .size(width = 1.dp, height = 25.dp)
                    .background(city_weather_gray_color)
            )
            Text(
                text = stringResource(id = R.string.speed_text),
                style = MaterialTheme.typography.titleSmall,
                color = city_weather_gray_color,
                modifier = Modifier.padding(start = 30.dp)
            )
            Text(
                text = "${speed}Km/h",
                style = MaterialTheme.typography.titleSmall,
                color = city_weather_black_color,
                modifier = Modifier.padding(start = 5.dp)
            )
        }
    }
}

@Composable
fun SunRise(time: Float) {
    Row {
        Box(
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.sun),
                contentDescription = null,
                modifier = Modifier
                    .padding(bottom = 132.dp, end = 45.dp)
                    .size(20.dp)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(bottom = 55.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.sun_rise),
                    contentDescription = null,
                    modifier = Modifier
                        .size(35.dp)
                        .padding(top = 20.dp)
                )
                Text(
                    text = stringResource(id = R.string.sun_rise),
                    style = MaterialTheme.typography.titleSmall,
                    fontSize = 10.sp,
                    color = city_weather_gray_color,
                    modifier = Modifier.padding(top = 5.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 20.dp)
                ) {
                    Text(
                        text = "06:10",
                        style = MaterialTheme.typography.labelSmall,
                    )
                    Space().SpaceWidth(value = 10)
                    Box(
                        modifier = Modifier
                            .size(185.dp, 1.dp)
                            .background(city_weather_dark_gray_color)
                    )
                    Space().SpaceWidth(value = 10)
                    Text(
                        text = "18:45",
                        style = MaterialTheme.typography.labelSmall,
                    )

                }
            }
            DrawCustomArc(
                initialValue = 180f,
                sweepAngle = 180f,
                value = time,
                width = 400f,
                height = 400f,
                strokeWidth = 1f,
                offsetX = 220f,
                offsetY = 80f,
                isDashed = true,
                backgroundBrush = arcBackgroundColor,
                foregroundBrush = sunColor
            )

        }
    }
}

@Composable
fun SunSet() {
    Box(contentAlignment = Alignment.Center) {
        DrawCustomArc(
            initialValue = 0f,
            sweepAngle = 180f,
            value = 0f,
            width = 400f,
            height = 400f,
            strokeWidth = 1f,
            offsetX = 220f,
            offsetY = 80f,
            isDashed = false,
            backgroundBrush = arcBackgroundColor,
            foregroundBrush = sunColor
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(id = R.string.sun_set),
                style = MaterialTheme.typography.titleSmall,
                fontSize = 10.sp,
                color = city_weather_gray_color,
                modifier = Modifier.padding(top = 80.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.sun_set),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .padding(bottom = 30.dp)
            )
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun CityWeatherDetailPreview() {
    WeatherAppTheme {
        //CityWeatherDetail()
    }
}