package com.gorokhov.weatherappgvc.presentation.details

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


class DefaultDetailsComponent @Inject constructor(
    private val city: City,
    private val storeFactory: DetailsStoreFactory,
    private val onBackClicked: () -> Unit,
    componentContext: ComponentContext
) : DetailsComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore { storeFactory.create(city) }

    private val scope = componentScope()

    init {
        scope.launch {
            store.labels.collect { label ->
                when (label) {
                    DetailsStore.Label.ClickBack -> {
                        onBackClicked()
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<DetailsStore.State> = store.stateFlow

    override fun onBackClick() {
        store.accept(DetailsStore.Intent.ClickBack)
    }

    override fun onChangeFavouriteStatusClick(cityId: Int) {
        scope.launch {
            store.accept(DetailsStore.Intent.ClickChangeFavouriteStatus)
        }
    }
}