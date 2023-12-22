package com.register.coffeapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.register.coffeapp.domain.entities.AuthRequest

@Dao
interface AuthDataDao {

    @Query("SELECT * FROM auth_data")
    fun getAuthData(): AuthDataDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAuthData(authRequest: AuthDataDbModel)

}