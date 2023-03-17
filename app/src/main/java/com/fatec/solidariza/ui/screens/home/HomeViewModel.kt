package com.fatec.solidariza.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fatec.solidariza.data.model.Distribuidor
import com.fatec.solidariza.data.repository.SolidarizaRepository
import com.fatec.solidariza.data.room.DistribuidorEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

typealias DistribuidoresPorRegiao = Map<String, List<Distribuidor>>

class HomeViewModel(
    private val solidarizaRepository: SolidarizaRepository,
) : ViewModel() {

    val distribuidores: StateFlow<List<DistribuidorEntity>> =
        solidarizaRepository.getDistribuidores()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = listOf()
            )

//    private val _distribuidoresPorRegiao: MutableStateFlow<DistribuidoresPorRegiao> =
//        MutableStateFlow(mapOf())
//    val distribuidores: StateFlow<DistribuidoresPorRegiao> = _distribuidoresPorRegiao.asStateFlow()

    init {
        refreshDistribuidoresFromNetwork()
//        _distribuidores.combine((1..2).asFlow()) { dist, _ ->
//            _distribuidoresPorRegiao.value = dist
//                .map { it.toDistribuidorEntity() }
//                .groupBy { it.regiao }
//        }.launchIn(viewModelScope)
    }

    private fun refreshDistribuidoresFromNetwork() = viewModelScope.launch {
        solidarizaRepository.refreshDistribuidores()
    }
}