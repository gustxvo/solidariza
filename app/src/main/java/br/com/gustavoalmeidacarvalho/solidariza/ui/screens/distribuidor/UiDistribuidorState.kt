package br.com.gustavoalmeidacarvalho.solidariza.ui.screens.distribuidor

import br.com.gustavoalmeidacarvalho.solidariza.data.model.Distribuidor
import br.com.gustavoalmeidacarvalho.solidariza.data.model.DistribuidorInput
import br.com.gustavoalmeidacarvalho.solidariza.data.model.Registro

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