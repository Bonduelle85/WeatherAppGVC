package com.gorokhov.weatherappgvc

import android.app.Application
import com.gorokhov.weatherappgvc.di.ApplicationComponent
import com.gorokhov.weatherappgvc.di.DaggerApplicationComponent

class WeatherApp: Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }
}