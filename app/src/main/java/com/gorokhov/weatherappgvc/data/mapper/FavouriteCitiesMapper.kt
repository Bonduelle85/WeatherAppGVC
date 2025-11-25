package com.gorokhov.weatherappgvc.data.mapper

import com.gorokhov.weatherappgvc.data.local.model.CityDbModel
import com.gorokhov.weatherappgvc.domain.entity.City

fun City.toDbModel() = CityDbModel(
    id = this.id,
    name = this.name,
    country = this.country
)

fun CityDbModel.toEntity() = City(
    id = this.id,
    name = this.name,
    country = this.country
)

fun List<CityDbModel>.toEntities(): List<City> = map {
    it.toEntity()
}