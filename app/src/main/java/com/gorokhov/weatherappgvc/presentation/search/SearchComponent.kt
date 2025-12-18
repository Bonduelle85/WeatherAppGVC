package com.gorokhov.weatherappgvc.presentation.search

import com.gorokhov.weatherappgvc.domain.entity.City
import kotlinx.coroutines.flow.StateFlow

interface SearchComponent {

    val model: StateFlow<SearchStore.State>

    fun changeSearchQuery(query: String)

    fun onCityItemClick(city: City)

    fun onSearchClick(city: City)

    fun onClickBack()
}