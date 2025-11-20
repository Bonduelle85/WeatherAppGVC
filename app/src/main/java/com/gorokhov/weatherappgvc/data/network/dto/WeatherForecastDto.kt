package com.gorokhov.weatherappgvc.data.network.dto

import com.google.gson.annotations.SerializedName

data class WeatherForecastDto(
    @SerializedName("current") val weather: WeatherDto,
    @SerializedName("forecast") val forecastDto: ForecastDto,
)
