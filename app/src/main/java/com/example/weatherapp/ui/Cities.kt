package com.example.weatherapp.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.data.Weather
import com.example.weatherapp.model.CityCard
import com.example.weatherapp.model.Space
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.ui.theme.add_icon_color
import com.example.weatherapp.ui.theme.button_add_city_color
import com.example.weatherapp.ui.theme.rainCloudSunGradient
import com.example.weatherapp.ui.theme.search_icon_color
import com.example.weatherapp.ui.theme.sparkCloudGradient
import com.example.weatherapp.ui.theme.sunWindGradient

@Composable
fun Cities(
    items: List<CityCard>,
    onClick: (CityCard) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = stringResource(id = R.string.find_location),
            style = MaterialTheme.typography.titleLarge
        )
        Space().SpaceHeight(value = 15)
        Search()
        Space().SpaceHeight(value = 40)
        Text(
            text = stringResource(id = R.string.cities),
            style = MaterialTheme.typography.titleLarge
        )
        Space().SpaceHeight(value = 27)
        items.forEach{
                it ->
            CityCardItem(cityCard = it, onClick = {onClick(it)})
            Spacer(modifier = Modifier.height(38.dp))
        }
        AddCityButton()

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search() {
    var search by remember {
        mutableStateOf("")
    }
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = search,
            onValueChange = { search = it },
            shape = RoundedCornerShape(50.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black,
                unfocusedBorderColor = Color(0xffE4E4EE)
            ),
            textStyle = MaterialTheme.typography.titleSmall,

            placeholder = {
                Row {
                    Text(
                        text = stringResource(id = R.string.hint),
                        style = MaterialTheme.typography.bodySmall,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = null,
                        tint = search_icon_color
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        )
    }
}

@Composable
fun CityCardItem(
    modifier: Modifier = Modifier, cityCard: CityCard,
    onClick: (CityCard) -> Unit
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Card(
            shape = MaterialTheme.shapes.medium,
            modifier = modifier.height(125.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(cityCardColor(cityCard.weather))
                    .fillMaxSize()
                    .clickable { onClick(cityCard) },
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                        .padding(horizontal = 30.dp, vertical = 15.dp)
                ) {
                    Column {
                        if (cityCard.temperature < 10)
                            Text(
                                text = "0${cityCard.temperature}${Typography.degree}",
                                style = MaterialTheme.typography.bodyLarge,
                            )
                        else
                            Text(
                                text = "${cityCard.temperature}${Typography.degree}",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        Text(
                            text = stringResource(id = cityCard.city),
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(id = cityCard.icon),
                        contentDescription = null,
                        modifier = Modifier.size(width = 64.dp, height = 72.dp)
                    )
                }
            }
        }

    }
}

fun cityCardColor(weather: Weather): Brush {
    val color: Brush = when (weather) {
        Weather.SPARK_CLOUD -> sparkCloudGradient
        Weather.SUN_WIND -> sunWindGradient
        else -> rainCloudSunGradient
    }
    return color
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCityButton() {
    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(button_add_city_color),
        modifier = Modifier.height(82.dp),
        onClick = {}

    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.add),
                    contentDescription = null,
                    Modifier.size(18.dp),
                    tint = add_icon_color
                )
                Spacer(modifier = Modifier.width(13.dp))
                Text(
                    text = stringResource(id = R.string.add_city),
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun CitiesActivityPreview() {
    WeatherAppTheme {
        WeatherApp()
    }
}








