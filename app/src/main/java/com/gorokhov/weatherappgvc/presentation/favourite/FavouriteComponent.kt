package com.gorokhov.weatherappgvc.presentation.favourite


import com.gorokhov.weatherappgvc.domain.entity.City
import kotlinx.coroutines.flow.StateFlow

interface FavouriteComponent {

    val model: StateFlow<FavouriteStore.State>

    fun onClickAddToFavourite()

    fun onClickCityItem(city: City)

    fun onClickSearch()
}