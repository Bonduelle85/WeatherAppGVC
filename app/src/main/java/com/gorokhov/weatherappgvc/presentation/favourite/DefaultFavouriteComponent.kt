package com.gorokhov.weatherappgvc.presentation.favourite

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.gorokhov.weatherappgvc.domain.entity.City
import com.gorokhov.weatherappgvc.presentation.extensions.componentScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DefaultFavouriteComponent @Inject constructor(
    private val storeFactory: FavouriteStoreFactory,
    private val onCityItemClicked: (City) -> Unit,
    private val onSearchClicked: () -> Unit,
    private val onAddToFavouriteClicked: () -> Unit,
    componentContext: ComponentContext
) : FavouriteComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore { storeFactory.create() }

    private val scope = componentScope()

    init {
        scope.launch {
            store.labels.collect { label ->
                when(label) {
                    is FavouriteStore.Label.ClickCityItem -> onCityItemClicked(label.city)
                    FavouriteStore.Label.ClickSearch -> onSearchClicked()
                    FavouriteStore.Label.ClickToFavourite -> onAddToFavouriteClicked()
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<FavouriteStore.State> = store.stateFlow

    override fun onClickAddToFavourite() {
        store.accept(FavouriteStore.Intent.ClickAddToFavourite)
    }

    override fun onClickCityItem(city: City) {
        store.accept(FavouriteStore.Intent.ClickCityItem(city))
    }

    override fun onClickSearch() {
        store.accept(FavouriteStore.Intent.ClickSearch)
    }
}