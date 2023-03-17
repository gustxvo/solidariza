package com.fatec.solidariza.data.model

data class Registro(
    val id: Int,
    val quantidade: Int,
    val peso: Double,
    val preco: Double,
)

data class RegistroInput(
    val quantidade: Int,
    val peso: Double,
    val preco: Double,
)