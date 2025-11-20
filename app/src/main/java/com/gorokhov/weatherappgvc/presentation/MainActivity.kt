package com.gorokhov.weatherappgvc.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.gorokhov.weatherappgvc.data.network.api.ApiFactory
import com.gorokhov.weatherappgvc.data.network.api.ApiService
import com.gorokhov.weatherappgvc.presentation.ui.theme.WeatherAppGVCTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val apiService = ApiFactory.apiService

        CoroutineScope(Dispatchers.Main).launch {
            val currentWeather = apiService.loadCurrentWeather("London")
            val forecast = apiService.loadForecast("London")
            val city = apiService.searchCity("London")
            Log.d("MainActivity", "${currentWeather.weather}")
            Log.d("MainActivity", "${forecast.forecastDto.forecastDay[0].dayWeatherDto}")
            Log.d("MainActivity", city[0].name)
        }

        setContent {
            WeatherAppGVCTheme {

            }
        }
    }
}
