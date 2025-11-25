package com.gorokhov.weatherappgvc.data.repository

import com.gorokhov.weatherappgvc.data.mapper.toEntity
import com.gorokhov.weatherappgvc.data.network.api.ApiService
import com.gorokhov.weatherappgvc.domain.entity.Forecast
import com.gorokhov.weatherappgvc.domain.entity.Weather
import com.gorokhov.weatherappgvc.domain.repository.WeatherRepository
import jakarta.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : WeatherRepository {

    override suspend fun getCurrentWeather(cityId: Int): Weather =
        apiService.loadCurrentWeather("$PREFIX_CITY_ID$cityId").toEntity()


    override suspend fun getForecast(cityId: Int): Forecast {
        return apiService.loadForecast("$PREFIX_CITY_ID$cityId").toEntity()
    }

    companion object {

        private const val PREFIX_CITY_ID = "id:"
    }
}