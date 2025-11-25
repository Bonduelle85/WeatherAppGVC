package com.gorokhov.weatherappgvc.domain.usecase

import com.gorokhov.weatherappgvc.domain.repository.FavouriteRepository
import javax.inject.Inject

class ObserveFavouriteStateUseCase @Inject constructor(
    private val repository: FavouriteRepository
) {

    operator fun  invoke(cityId: Int) = repository.observeIsFavourite(cityId)
}