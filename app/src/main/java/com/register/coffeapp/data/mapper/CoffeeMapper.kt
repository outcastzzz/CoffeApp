package com.register.coffeapp.data.mapper

import com.register.coffeapp.data.database.AuthDataDbModel
import com.register.coffeapp.domain.entities.AuthRequest
import javax.inject.Inject

class CoffeeMapper @Inject constructor(){

    fun mapDbModelToEntity(dbModel: AuthDataDbModel) = AuthRequest(
        dbModel.login,
        dbModel.password
    )

    fun mapStringsToDbModel(str: AuthRequest) = AuthDataDbModel(
        str.login,
        str.password
    )

}