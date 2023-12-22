package com.register.coffeapp.domain.useCase

import com.register.coffeapp.domain.entities.AuthRequest
import com.register.coffeapp.domain.repository.CoffeeRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val repository: CoffeeRepository
) {

    suspend fun signIn(authRequest: AuthRequest) = repository.signIn(authRequest)

}