package com.fatec.solidariza.ui.screens.distribuidor

import com.fatec.solidariza.data.model.Distribuidor
import com.fatec.solidariza.data.model.DistribuidorInput
import com.fatec.solidariza.data.model.Registro

data class DistribuidorState(
    val id: Int = 0,
    val nome: String = "",
    val produto: String = "",
    val regiao: String = "",
    val endereco: String = "",
    val registros: List<Registro> = emptyList(),
)

fun Distribuidor.toDistribuidorState() = DistribuidorState(
    id = id,
    nome = nome,
    produto = produto,
    regiao = regiao,
    endereco = endereco,
    registros = registros ?: emptyList()
)

fun DistribuidorState.toDistribuidorInput() = DistribuidorInput(
    nome = nome,
    produto = produto,
    regiao = regiao,
    endereco = endereco
)