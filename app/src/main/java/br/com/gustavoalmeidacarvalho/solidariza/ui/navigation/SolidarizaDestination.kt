package br.com.gustavoalmeidacarvalho.solidariza.ui.navigation

sealed class SolidarizaDestination(val route: String) {
    object Home : SolidarizaDestination("home")
    object Distribuidor : SolidarizaDestination("distribuidor")
    object CriarDistribuidor : SolidarizaDestination("criar_distribuidor")
    object CriarRegistro : SolidarizaDestination("criar_registro")
}