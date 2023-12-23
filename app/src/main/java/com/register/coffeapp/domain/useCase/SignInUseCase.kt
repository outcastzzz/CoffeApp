package com.register.coffeapp.domain.useCase

import com.register.coffeapp.domain.entities.User
import com.register.coffeapp.domain.repository.CoffeeRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val repository: CoffeeRepository
) {

    suspend fun signIn(user: User) = repository.signIn(user)

}