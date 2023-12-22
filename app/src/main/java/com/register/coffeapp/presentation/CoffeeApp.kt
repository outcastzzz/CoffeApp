package com.register.coffeapp.presentation

import android.app.Application
import com.register.coffeapp.di.ApplicationComponent
import com.register.coffeapp.di.DaggerApplicationComponent

class CoffeeApp: Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory()
            .create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

}