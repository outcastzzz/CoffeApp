package com.register.coffeapp.di

import android.app.Application
import com.register.coffeapp.presentation.screen.CafeListFragment
import com.register.coffeapp.presentation.CoffeeApp
import com.register.coffeapp.presentation.screen.MapFragment
import com.register.coffeapp.presentation.screen.MenuFragment
import com.register.coffeapp.presentation.screen.OrderFragment
import com.register.coffeapp.presentation.screen.RegisterFragment
import com.register.coffeapp.presentation.screen.SignInFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(fragment: CafeListFragment)

    fun inject(fragment: MenuFragment)

    fun inject(fragment: RegisterFragment)

    fun inject(fragment: SignInFragment)

    fun inject(fragment: MapFragment)

    fun inject(fragment: OrderFragment)

    fun inject(application: CoffeeApp)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }

}