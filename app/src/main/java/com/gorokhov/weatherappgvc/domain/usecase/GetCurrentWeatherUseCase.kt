package com.gorokhov.weatherappgvc.domain.usecase

import com.gorokhov.weatherappgvc.domain.repository.WeatherRepository
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {

    suspend operator fun  invoke(cityId: Int) = repository.getCurrentWeather(cityId)
}