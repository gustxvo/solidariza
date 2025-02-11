package br.com.gustavoalmeidacarvalho.solidariza.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface SolidarizaDao {

    @Query("SELECT * FROM distribuidor")
    fun getDistribuidores(): Flow<List<DistribuidorEntity>>

    @Transaction
    @Query("SELECT * FROM distribuidor WHERE id = :id")
    fun getDistribuidorById(id: Int): Flow<DistribuidorWithRegistros>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun refreshDistribuidores(distribuidores: List<DistribuidorEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun refreshRegistros(registro: List<RegistroEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveDistribuidor(distribuidor: DistribuidorEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRegistro(registro: RegistroEntity)
}