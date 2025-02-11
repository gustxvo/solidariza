package br.com.gustavoalmeidacarvalho.solidariza.ui.screens.distribuidor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gustavoalmeidacarvalho.solidariza.data.repository.SolidarizaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CriarDistribuidorViewModel(
    private val solidarizaRepository: SolidarizaRepository,
) : ViewModel() {

    private val _distribuidor: MutableStateFlow<DistribuidorState> =
        MutableStateFlow(DistribuidorState())
    val distribuidor: StateFlow<DistribuidorState> = _distribuidor.asStateFlow()

    fun updateDistribuidor(distribuidorState: DistribuidorState) {
        _distribuidor.value = distribuidorState
    }

    fun saveDistribuidor() = viewModelScope.launch {
        val distribuidorInput = _distribuidor.value.toDistribuidorInput()
        solidarizaRepository.saveDistribuidor(
            distribuidorInput.copy(produto = findProdutoByRegiao())
        )
    }

    private fun findProdutoByRegiao() = when (_distribuidor.value.regiao) {
        "Norte" -> "Cacau"
        "Nordeste" -> "Banana"
        "Centro-Oeste" -> "Cana-de-açúcar"
        else -> "Laranja"
    }
}