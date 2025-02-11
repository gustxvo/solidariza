package br.com.gustavoalmeidacarvalho.solidariza.ui.screens.registro

import br.com.gustavoalmeidacarvalho.solidariza.data.model.RegistroInput

data class RegistroState(
    val quantidade: String = "",
    val peso: String = "",
    val preco: String = "",
    val regiao: String,
) {
    val produto = when (regiao) {
        "Norte" -> "Cacau"
        "Nordeste" -> "Banana"
        "Centro-Oeste" -> "Cana-de-açúcar"
        else -> "Laranja"
    }
}

fun RegistroState.toRegistroInput() = RegistroInput(
    quantidade = quantidade.toIntOrNull() ?: 0,
    peso = peso.toDoubleOrNull() ?: 0.0,
    preco = preco.toDoubleOrNull() ?: 0.0
)