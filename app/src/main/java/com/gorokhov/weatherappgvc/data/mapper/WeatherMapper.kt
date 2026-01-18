package com.gorokhov.weatherappgvc.data.mapper

import com.gorokhov.weatherappgvc.data.network.dto.WeatherCurrentDto
import com.gorokhov.weatherappgvc.data.network.dto.WeatherDto
import com.gorokhov.weatherappgvc.data.network.dto.WeatherForecastDto
import com.gorokhov.weatherappgvc.domain.entity.Forecast
import com.gorokhov.weatherappgvc.domain.entity.Weather
import java.util.Calendar
import java.util.Date

fun WeatherCurrentDto.toEntity() = weather.toEntity()

fun WeatherDto.toEntity() = Weather(
    tempC = tempC,
    conditionText = condition.text,
    conditionUrl = condition.iconUrl.correctImageUrl(),
    date = date.toCalendar()
)

fun WeatherForecastDto.toEntity() = Forecast(
    currentWeather = this.current.toEntity(),
    upcomingForecast = this.forecastDto.forecastDay.drop(FIRST_DAY).map { dayDto ->
        val dayWeatherDto = dayDto.dayWeatherDto
        Weather(
            tempC = dayWeatherDto.tempC,
            conditionText = dayWeatherDto.condition.text,
            conditionUrl = dayWeatherDto.condition.iconUrl.correctImageUrl(),
            date = dayDto.date.toCalendar()
        )
    }
)

private fun Long.toCalendar() = Calendar.getInstance().apply {
    // timeInMillis = this@toCalendar.toMillis()
    time = Date(this@toCalendar.toMillis())
}

private fun String.correctImageUrl() = "https:$this".replace(
    oldValue = "64x64", newValue = "128x128"
)

private fun Long.toMillis() = this * 1000

private const val FIRST_DAY = 0