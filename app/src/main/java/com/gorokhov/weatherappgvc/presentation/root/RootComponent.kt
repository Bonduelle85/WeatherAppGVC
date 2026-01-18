package com.gorokhov.weatherappgvc.presentation.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.gorokhov.weatherappgvc.presentation.details.DetailsComponent
import com.gorokhov.weatherappgvc.presentation.favourite.FavouriteComponent
import com.gorokhov.weatherappgvc.presentation.search.SearchComponent

interface RootComponent {

    val stack : Value<ChildStack<*, Child>>

    sealed interface Child {

        data class Favourite(val component: FavouriteComponent) : Child

        data class Details(val component: DetailsComponent) : Child

        data class Search(val component: SearchComponent) : Child
    }
}