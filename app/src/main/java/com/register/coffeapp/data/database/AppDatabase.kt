package com.register.coffeapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AuthDataDbModel::class], version = 2, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    companion object {

        private var db: AppDatabase? = null
        private const val DB_NAME = "authData.db"
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {

            db?.let { return it }
            val instance =
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
            db = instance
            return instance
        }

    }

    abstract fun authDataDao(): AuthDataDao

}