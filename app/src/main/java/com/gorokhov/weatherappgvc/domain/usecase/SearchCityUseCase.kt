package com.gorokhov.weatherappgvc.domain.usecase

import com.gorokhov.weatherappgvc.domain.repository.SearchRepository
import javax.inject.Inject

class SearchCityUseCase @Inject constructor(
    private val repository: SearchRepository
) {

    suspend operator fun  invoke(query: String) = repository.search(query)
}