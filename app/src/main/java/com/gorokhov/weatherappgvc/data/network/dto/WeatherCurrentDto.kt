package com.gorokhov.weatherappgvc.data.network.dto

import com.google.gson.annotations.SerializedName

data class WeatherCurrentDto(
    @SerializedName("current") val weather: WeatherDto,
)
