package com.register.coffeapp.domain.useCase

import com.register.coffeapp.domain.repository.CoffeeRepository
import javax.inject.Inject

class GetMenuUseCase @Inject constructor(
    private val repository: CoffeeRepository
) {

    suspend fun getMenuItem(token: String, id: String ) = repository.getMenuItems(token, id )

}