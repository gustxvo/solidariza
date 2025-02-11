package br.com.gustavoalmeidacarvalho.solidariza.data.repository

import br.com.gustavoalmeidacarvalho.solidariza.data.model.Distribuidor
import br.com.gustavoalmeidacarvalho.solidariza.data.model.DistribuidorInput
import br.com.gustavoalmeidacarvalho.solidariza.data.model.RegistroInput
import br.com.gustavoalmeidacarvalho.solidariza.data.room.DistribuidorEntity
import kotlinx.coroutines.flow.Flow

interface SolidarizaRepository {

    fun getDistribuidores(): Flow<List<DistribuidorEntity>>

    fun getDistribuidorById(id: Int): Flow<Distribuidor>

    suspend fun saveDistribuidor(distribuidorInput: DistribuidorInput)

    suspend fun saveRegistro(distribuidorId: Int, registroInput: RegistroInput)

    suspend fun refreshDistribuidores()

    suspend fun refreshRegistros(distribuidorId: Int)
}