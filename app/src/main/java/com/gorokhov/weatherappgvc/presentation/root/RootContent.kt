package com.gorokhov.weatherappgvc.presentation.root

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.gorokhov.weatherappgvc.presentation.details.DetailsContent
import com.gorokhov.weatherappgvc.presentation.favourite.FavouriteContent
import com.gorokhov.weatherappgvc.presentation.search.SearchContent
import com.gorokhov.weatherappgvc.presentation.ui.theme.WeatherAppGVCTheme

@Composable
fun RootContent(component: RootComponent) {

    WeatherAppGVCTheme {
        Children(
            stack = component.stack
        ) {
            when(val instance = it.instance) {
                is RootComponent.Child.Details -> {
                    DetailsContent(component = instance.component)
                }
                is RootComponent.Child.Favourite -> {
                    FavouriteContent(component = instance.component)
                }
                is RootComponent.Child.Search -> {
                    SearchContent(component = instance.component)
                }
            }
        }
    }
}