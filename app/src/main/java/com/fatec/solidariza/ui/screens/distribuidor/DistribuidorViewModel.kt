package com.fatec.solidariza.ui.screens.distribuidor

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fatec.solidariza.data.repository.SolidarizaRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DistribuidorViewModel(
    savedStateHandle: SavedStateHandle,
    solidarizaRepository: SolidarizaRepository,
) : ViewModel() {

    private val id: String = checkNotNull(savedStateHandle["id"])

    val distribuidor: StateFlow<DistribuidorState> =
        solidarizaRepository.getDistribuidorById(id.toInt())
            .map { it.toDistribuidorState() }
            .stateIn(
                viewModelScope, SharingStarted.WhileSubscribed(5000),
                DistribuidorState()
            )

    init {
        viewModelScope.launch {
            solidarizaRepository.refreshRegistros(id.toInt())
        }
    }
}