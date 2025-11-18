package com.gorokhov.weatherappgvc.domain.repository

import com.gorokhov.weatherappgvc.domain.entity.Weather

interface WeatherRepository {

    suspend fun getCurrentWeather(cityId: Int): Weather

    suspend fun getForecast(cityId: Int): List<Weather>
}