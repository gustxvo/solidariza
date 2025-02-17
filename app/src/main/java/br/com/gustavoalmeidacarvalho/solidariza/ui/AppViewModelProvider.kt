/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.com.gustavoalmeidacarvalho.solidariza.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import br.com.gustavoalmeidacarvalho.solidariza.SolidarizaApplication
import br.com.gustavoalmeidacarvalho.solidariza.ui.screens.distribuidor.CriarDistribuidorViewModel
import br.com.gustavoalmeidacarvalho.solidariza.ui.screens.distribuidor.DistribuidorViewModel
import br.com.gustavoalmeidacarvalho.solidariza.ui.screens.home.HomeViewModel
import br.com.gustavoalmeidacarvalho.solidariza.ui.screens.registro.RegistroViewModel

/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(solidarizaApplication().container.solidarizaRepository)
        }

        initializer {
            DistribuidorViewModel(
                this.createSavedStateHandle(),
                solidarizaApplication().container.solidarizaRepository
            )
        }

        initializer {
            CriarDistribuidorViewModel(
                solidarizaApplication().container.solidarizaRepository
            )
        }

        initializer {
            RegistroViewModel(
                this.createSavedStateHandle(),
                solidarizaApplication().container.solidarizaRepository
            )
        }
    }
}

fun CreationExtras.solidarizaApplication(): SolidarizaApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as SolidarizaApplication)
