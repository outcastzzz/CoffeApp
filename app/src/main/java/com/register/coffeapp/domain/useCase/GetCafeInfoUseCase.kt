package com.register.coffeapp.domain.useCase

import com.register.coffeapp.domain.repository.CoffeeRepository
import javax.inject.Inject

class GetCafeInfoUseCase @Inject constructor(
    private val repository: CoffeeRepository
) {

    suspend fun getCafeInfo(token: String) = repository.getCafeInfo(token)

}