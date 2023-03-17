package com.fatec.solidariza.data.repository

import com.fatec.solidariza.data.model.Distribuidor
import com.fatec.solidariza.data.model.DistribuidorInput
import com.fatec.solidariza.data.model.Registro
import com.fatec.solidariza.data.model.RegistroInput
import com.fatec.solidariza.data.room.DistribuidorEntity
import com.fatec.solidariza.data.room.DistribuidorWithRegistros
import com.fatec.solidariza.ui.screens.home.DistribuidoresPorRegiao
import kotlinx.coroutines.flow.Flow

interface SolidarizaRepository {

    fun getDistribuidores(): Flow<List<DistribuidorEntity>>

    fun getDistribuidorById(id: Int): Flow<Distribuidor>

    suspend fun saveDistribuidor(distribuidorInput: DistribuidorInput)

    suspend fun saveRegistro(distribuidorId: Int, registroInput: RegistroInput)

    suspend fun refreshDistribuidores()

    suspend fun refreshRegistros(distribuidorId: Int)
}