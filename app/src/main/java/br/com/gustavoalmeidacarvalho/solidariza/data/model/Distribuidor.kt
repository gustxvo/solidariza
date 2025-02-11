package br.com.gustavoalmeidacarvalho.solidariza.data.model

import br.com.gustavoalmeidacarvalho.solidariza.data.room.DistribuidorEntity

data class Distribuidor(
    val id: Int,
    val nome: String,
    val produto: String,
    val regiao: String,
    val endereco: String,
    val registros: List<Registro>? = null
)

data class DistribuidorInput(
    val nome: String,
    val produto: String,
    val regiao: String,
    val endereco: String
)

fun Distribuidor.toDistribuidorEntity() = DistribuidorEntity(
    id = id,
    nome = nome,
    produto = produto,
    regiao = regiao,
    endereco = endereco,
)

fun DistribuidorEntity.toDistribuidor() = Distribuidor(
    id = id,
    nome = nome,
    produto = produto,
    regiao = regiao,
    endereco = endereco,
)