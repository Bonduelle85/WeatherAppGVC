package com.gorokhov.weatherappgvc.domain.usecase

import com.gorokhov.weatherappgvc.domain.entity.City
import com.gorokhov.weatherappgvc.domain.repository.FavouriteRepository
import javax.inject.Inject

class ChangeFavouriteStateUseCase @Inject constructor(
    private val repository: FavouriteRepository
) {

    suspend fun addToFavourite(city: City) = repository.addToFavourite(city)

    suspend fun removeFromFavourite(cityId: Int) = repository.removeFromFavourite(cityId)
}