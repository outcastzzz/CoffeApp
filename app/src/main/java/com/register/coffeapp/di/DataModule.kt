package com.register.coffeapp.di

import android.app.Application
import com.register.coffeapp.data.database.AppDatabase
import com.register.coffeapp.data.database.AuthDataDao
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

        @Provides
        @ApplicationScope
        fun provideAuthDao(
            application: Application
        ): AuthDataDao {
            return AppDatabase.getInstance(application).authDataDao()
        }

    }

}