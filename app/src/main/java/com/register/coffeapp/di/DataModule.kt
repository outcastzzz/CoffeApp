package com.register.coffeapp.di

import com.register.coffeapp.data.network.ApiFactory
import com.register.coffeapp.data.network.ApiService
import com.register.coffeapp.data.repository.CoffeeRepositoryImpl
import com.register.coffeapp.domain.repository.CoffeeRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindCoffeeRepository(impl: CoffeeRepositoryImpl): CoffeeRepository

    companion object {

        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }

    }

}