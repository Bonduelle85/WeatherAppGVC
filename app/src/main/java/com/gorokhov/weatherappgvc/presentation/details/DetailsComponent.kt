package com.gorokhov.weatherappgvc.presentation.details

import com.gorokhov.weatherappgvc.domain.entity.City

import kotlinx.coroutines.flow.StateFlow

interface DetailsComponent {

    val model: StateFlow<DetailsStore.State>

    fun onBackClick()

    fun onChangeFavouriteStatusClick(cityId: Int)
}