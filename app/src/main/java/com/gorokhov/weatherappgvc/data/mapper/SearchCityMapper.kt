package com.gorokhov.weatherappgvc.data.mapper

import com.gorokhov.weatherappgvc.data.network.dto.CityDto
import com.gorokhov.weatherappgvc.domain.entity.City

fun CityDto.toEntity(): City = City(
    id = this.id,
    name = this.name,
    country = this.country
)

fun List<CityDto>.toEntities(): List<City> = map { it.toEntity() }