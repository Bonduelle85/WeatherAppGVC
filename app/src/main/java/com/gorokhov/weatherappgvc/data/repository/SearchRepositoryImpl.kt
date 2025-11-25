package com.gorokhov.weatherappgvc.data.repository

import com.gorokhov.weatherappgvc.data.mapper.toEntities
import com.gorokhov.weatherappgvc.data.network.api.ApiService
import com.gorokhov.weatherappgvc.domain.entity.City
import com.gorokhov.weatherappgvc.domain.repository.SearchRepository
import jakarta.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : SearchRepository {

    override suspend fun search(query: String) = apiService.searchCity(query).toEntities()
}