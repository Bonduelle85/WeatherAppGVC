package com.gorokhov.weatherappgvc.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import com.gorokhov.weatherappgvc.WeatherApp
import com.gorokhov.weatherappgvc.domain.usecase.ChangeFavouriteStateUseCase
import com.gorokhov.weatherappgvc.domain.usecase.SearchCityUseCase
import com.gorokhov.weatherappgvc.presentation.root.DefaultRootComponent
import com.gorokhov.weatherappgvc.presentation.root.RootContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var rootComponentFactory: DefaultRootComponent.Factory

    @Inject
    lateinit var searchCityUseCase: SearchCityUseCase

    @Inject
    lateinit var changeFavouriteStateUseCase: ChangeFavouriteStateUseCase

    override fun onCreate(savedInstanceState: Bundle?) {

        (applicationContext as WeatherApp).applicationComponent.inject(this)

        super.onCreate(savedInstanceState)

        // enableEdgeToEdge()

        val component = rootComponentFactory.create(defaultComponentContext())

        val scope = CoroutineScope(Dispatchers.Main)

        scope.launch {
            val cities = searchCityUseCase("Пон")

            cities.forEach {
                changeFavouriteStateUseCase.addToFavourite(it)
            }

        }

        setContent {
            RootContent(component = component)
        }
    }
}
