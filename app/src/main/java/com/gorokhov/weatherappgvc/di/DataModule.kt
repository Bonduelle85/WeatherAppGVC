package com.gorokhov.weatherappgvc.di

import android.content.Context
import com.gorokhov.weatherappgvc.data.local.db.FavouriteCitiesDao
import com.gorokhov.weatherappgvc.data.local.db.FavouriteDatabase
import com.gorokhov.weatherappgvc.data.network.api.ApiFactory
import com.gorokhov.weatherappgvc.data.network.api.ApiService
import com.gorokhov.weatherappgvc.data.repository.FavouriteRepositoryImpl
import com.gorokhov.weatherappgvc.data.repository.SearchRepositoryImpl
import com.gorokhov.weatherappgvc.data.repository.WeatherRepositoryImpl
import com.gorokhov.weatherappgvc.domain.repository.FavouriteRepository
import com.gorokhov.weatherappgvc.domain.repository.SearchRepository
import com.gorokhov.weatherappgvc.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindFavouriteRepository(impl: FavouriteRepositoryImpl): FavouriteRepository

    @ApplicationScope
    @Binds
    fun bindSearchRepository(impl: SearchRepositoryImpl): SearchRepository

    @ApplicationScope
    @Binds
    fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideApiService(): ApiService = ApiFactory.apiService

        @ApplicationScope
        @Provides
        fun provideFavouriteDatabase(
            context: Context
        ): FavouriteDatabase {
            return FavouriteDatabase.getInstance(context)
        }

        @ApplicationScope
        @Provides
        fun provideFavouriteCitiesDao(
            database: FavouriteDatabase
        ): FavouriteCitiesDao {
            return database.favouriteCitiesDao()
        }
    }
}