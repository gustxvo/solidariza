package br.com.gustavoalmeidacarvalho.solidariza.ui.screens.registro

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gustavoalmeidacarvalho.solidariza.data.repository.SolidarizaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegistroViewModel(
    savedStateHandle: SavedStateHandle,
    private val solidarizaRepository: SolidarizaRepository,
) : ViewModel() {

    private val regiao: String = checkNotNull(savedStateHandle["regiao"])

    private val _registroState: MutableStateFlow<RegistroState> =
        MutableStateFlow(RegistroState(regiao = regiao))
    val registro: StateFlow<RegistroState> = _registroState.asStateFlow()

    private val distribuidorId: Int = checkNotNull(savedStateHandle["distribuidor_id"])

    fun updateRegistro(registro: RegistroState) {
        _registroState.value = registro
    }

    fun addRegistro() = viewModelScope.launch {
        val registroInput = _registroState.value.toRegistroInput()
        solidarizaRepository.saveRegistro(
            distribuidorId = distribuidorId,
            registroInput = registroInput
        )
    }
}