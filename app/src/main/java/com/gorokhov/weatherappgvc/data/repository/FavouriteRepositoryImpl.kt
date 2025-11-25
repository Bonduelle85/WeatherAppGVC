package com.gorokhov.weatherappgvc.data.repository

import com.gorokhov.weatherappgvc.data.local.db.FavouriteCitiesDao
import com.gorokhov.weatherappgvc.data.mapper.toDbModel
import com.gorokhov.weatherappgvc.data.mapper.toEntities
import com.gorokhov.weatherappgvc.domain.entity.City
import com.gorokhov.weatherappgvc.domain.repository.FavouriteRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavouriteRepositoryImpl @Inject constructor(
    private val dao: FavouriteCitiesDao
): FavouriteRepository {

    override val favouriteCities: Flow<List<City>> = dao.getFavouriteCities()
        .map { it.toEntities() }

    override fun observeIsFavourite(cityId: Int): Flow<Boolean> = dao.observeIsFavourite(cityId)


    override suspend fun addToFavourite(city: City) {
        dao.addToFavourite(city.toDbModel())
    }

    override suspend fun removeFromFavourite(cityId: Int) {
        dao.removeFromFavourite(cityId)
    }
}