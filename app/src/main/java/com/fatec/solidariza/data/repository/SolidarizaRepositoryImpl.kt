package com.fatec.solidariza.data.repository

import com.fatec.solidariza.data.model.Distribuidor
import com.fatec.solidariza.data.model.DistribuidorInput
import com.fatec.solidariza.data.model.RegistroInput
import com.fatec.solidariza.data.model.toDistribuidorEntity
import com.fatec.solidariza.data.network.SolidarizaApiService
import com.fatec.solidariza.data.room.DistribuidorEntity
import com.fatec.solidariza.data.room.DistribuidorWithRegistros
import com.fatec.solidariza.data.room.RegistroEntity
import com.fatec.solidariza.data.room.SolidarizaDao
import com.fatec.solidariza.data.room.toDistribuidor
import com.fatec.solidariza.data.room.toRegistroEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SolidarizaRepositoryImpl(
    private val solidarizaApi: SolidarizaApiService,
    private val solidarizaDao: SolidarizaDao,
) : SolidarizaRepository {

    override fun getDistribuidores(): Flow<List<DistribuidorEntity>> =
        solidarizaDao.getDistribuidores()

    override fun getDistribuidorById(id: Int): Flow<Distribuidor> =
        solidarizaDao.getDistribuidorById(id).map { it.toDistribuidor() }

    override suspend fun saveDistribuidor(distribuidorInput: DistribuidorInput) {
        val distribuidor = solidarizaApi.saveDistribuidor(distribuidorInput)
        solidarizaDao.saveDistribuidor(distribuidor.toDistribuidorEntity())
    }

    override suspend fun saveRegistro(distribuidorId: Int, registroInput: RegistroInput) {
        val registro = solidarizaApi.saveRegistro(distribuidorId, registroInput)
        solidarizaDao.saveRegistro(
            RegistroEntity(
                id = registro.id,
                distribuidorId = distribuidorId,
                quantidade = registro.quantidade,
                peso = registro.peso,
                preco = registro.preco,
            )
        )
    }

    override suspend fun refreshDistribuidores() {
        val distribuidores = solidarizaApi.getDistribuidores()
        solidarizaDao.refreshDistribuidores(distribuidores.map { it.toDistribuidorEntity() })
    }

    override suspend fun refreshRegistros(distribuidorId: Int) {
        val registros = solidarizaApi.getRegistrosByDistribuidorId(distribuidorId)
        solidarizaDao.refreshRegistros(registros.toRegistroEntity(distribuidorId))
    }
}