package com.fatec.solidariza.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DistribuidorEntity::class, RegistroEntity::class], version = 1, exportSchema = false)
abstract class SolidarizaDatabase : RoomDatabase() {

    abstract fun solidarizaDao(): SolidarizaDao

    companion object {
        @Volatile
        var INSTANCE: SolidarizaDatabase? = null

        fun getDatabase(context: Context): SolidarizaDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context, SolidarizaDatabase::class.java, "solidariza_db"
                ).fallbackToDestructiveMigration()
                    .build().also { INSTANCE = it }
            }
        }
    }
}