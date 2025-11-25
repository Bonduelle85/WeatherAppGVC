package com.gorokhov.weatherappgvc.domain.entity

data class Forecast(
    val currentWeather: Weather,
    val upcomingForecast: List<Weather>
)
