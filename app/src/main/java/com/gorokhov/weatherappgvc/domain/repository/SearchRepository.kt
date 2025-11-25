package com.gorokhov.weatherappgvc.domain.repository

import com.gorokhov.weatherappgvc.domain.entity.City
import com.gorokhov.weatherappgvc.domain.entity.Weather
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    suspend fun search(query: String): List<City>
}