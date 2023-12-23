package com.register.coffeapp.domain.useCase

import com.register.coffeapp.domain.entities.User
import com.register.coffeapp.domain.repository.CoffeeRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: CoffeeRepository
) {

    suspend fun register(user: User) = repository.register(user)
}