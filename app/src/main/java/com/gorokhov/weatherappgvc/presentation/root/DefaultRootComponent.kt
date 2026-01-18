package com.gorokhov.weatherappgvc.presentation.root

import android.os.Parcelable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.gorokhov.weatherappgvc.domain.entity.City
import com.gorokhov.weatherappgvc.presentation.details.DefaultDetailsComponent
import com.gorokhov.weatherappgvc.presentation.favourite.DefaultFavouriteComponent
import com.gorokhov.weatherappgvc.presentation.search.DefaultSearchComponent
import com.gorokhov.weatherappgvc.presentation.search.OpenReason
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.parcelize.Parcelize


class DefaultRootComponent @AssistedInject constructor(
    private val detailsComponentFactory: DefaultDetailsComponent.Factory,
    private val searchComponentFactory: DefaultSearchComponent.Factory,
    private val favouriteComponentFactory: DefaultFavouriteComponent.Factory,
    @Assisted("componentContext") componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = Config.Favourite,
        handleBackButton = true,
        childFactory = { config, componentContext -> child(config, componentContext) } //::child
    )

    private fun child(
        config: Config,
        componentContext: ComponentContext
    ): RootComponent.Child = when (config) {
        is Config.Details -> {
            val component = detailsComponentFactory.create(
                componentContext = componentContext,
                city = config.city,
                onBackClicked = {
                    navigation.pop()
                }
            )
            RootComponent.Child.Details(component)
        }

        Config.Favourite -> {
            val component = favouriteComponentFactory.create(
                componentContext = componentContext,
                onCityItemClicked = { city ->
                    navigation.push(Config.Details(city))
                },
                onSearchClicked = {
                    navigation.push(Config.Search(OpenReason.RegularSearch))
                },
                onAddToFavouriteClicked = {
                    navigation.push(Config.Search(OpenReason.AddToFavourite))
                }
            )
            RootComponent.Child.Favourite(component)
        }

        is Config.Search -> {
            val component = searchComponentFactory.create(
                componentContext = componentContext,
                openReason = config.openReason,
                onBackClicked = {
                    navigation.pop()
                },
                onForecastForCityRequested = { city ->
                    navigation.push(Config.Details(city))
                },
                onCityToFavouriteSaved = {
                    navigation.pop()
                },
            )
            RootComponent.Child.Search(component)
        }
    }

    sealed interface Config : Parcelable {

        @Parcelize
        data object Favourite : Config

        @Parcelize
        data class Details(val city: City) : Config

        @Parcelize
        data class Search(val openReason: OpenReason) : Config
    }

    @AssistedFactory
    interface Factory {

        fun create(
            @Assisted("componentContext") componentContext: ComponentContext
        ): DefaultRootComponent
    }
}