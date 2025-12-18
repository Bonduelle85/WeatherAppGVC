package com.gorokhov.weatherappgvc.presentation.search

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

class DefaultSearchComponent @Inject constructor(
    private val storeFactory: SearchStoreFactory,
    private val openReason: OpenReason,
    private val onBackClicked: () -> Unit,
    private val onForecastForCityRequested: (City) -> Unit,
    private val onCityToFavouriteSaved: () -> Unit,
    componentContext: ComponentContext,
) : SearchComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore { storeFactory.create(openReason) }

    private val scope = componentScope()

    init {
        scope.launch {
            store.labels.collect { label ->
                when (label) {
                    SearchStore.Label.ClickBack -> {
                        onBackClicked()
                    }
                    is SearchStore.Label.OpenForecast -> {
                        onForecastForCityRequested(label.city)
                    }
                    SearchStore.Label.SaveToFavourite -> {
                        onCityToFavouriteSaved()
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<SearchStore.State> = store.stateFlow

    override fun changeSearchQuery(query: String) {
        store.accept(SearchStore.Intent.ChangeSearchQuery(query))
    }

    override fun onCityItemClick(city: City) {
        scope.launch {
            store.accept(SearchStore.Intent.ClickCity(city))
        }
    }

    override fun onSearchClick(city: City) {
        scope.launch {
            store.accept(SearchStore.Intent.ClickSearch)
        }
    }

    override fun onClickBack() {
        store.accept(SearchStore.Intent.ClickBack)
    }
}