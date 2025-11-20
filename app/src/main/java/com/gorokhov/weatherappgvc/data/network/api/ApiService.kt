package com.gorokhov.weatherappgvc.data.network.api

import com.gorokhov.weatherappgvc.data.network.dto.CityDto
import com.gorokhov.weatherappgvc.data.network.dto.WeatherCurrentDto
import com.gorokhov.weatherappgvc.data.network.dto.WeatherForecastDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // http://api.weatherapi.com/v1/current.json?key=da74b1afd6884952a46135943232311&q=London&aqi=no
    @GET("current.json?key=da74b1afd6884952a46135943232311")
    suspend fun loadCurrentWeather(
        @Query("q") query: String,
    ): WeatherCurrentDto

    // http://api.weatherapi.com/v1/forecast.json?key=da74b1afd6884952a46135943232311&q=London&days=4&aqi=no&alerts=no
    @GET("forecast.json?key=da74b1afd6884952a46135943232311")
    suspend fun loadForecast(
        @Query("q") query: String,
        @Query("days") daysCount: Int = 4,
    ): WeatherForecastDto

    // http://api.weatherapi.com/v1/search.json?key=da74b1afd6884952a46135943232311&q=London
    @GET("search.json?key=da74b1afd6884952a46135943232311")
    suspend fun searchCity(
        @Query("q") query: String,
    ): List<CityDto>
}