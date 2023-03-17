package com.fatec.solidariza.ui.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fatec.solidariza.data.model.toDistribuidor
import com.fatec.solidariza.data.model.toDistribuidorEntity
import com.fatec.solidariza.ui.AppViewModelProvider
import com.fatec.solidariza.ui.SolidarizaAppBar
import com.fatec.solidariza.ui.theme.SolidarizaTheme

@Composable
fun HomeScreen(
    onAddClick: () -> Unit,
    onCardClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = AppViewModelProvider.Factory
    ),
) {
    val uiStates by viewModel.distribuidores.collectAsState()

    val uiState by remember(uiStates) {
        derivedStateOf {
            uiStates.map {
                it.toDistribuidor()
            }.groupBy { it.regiao }
        }
    }

    Scaffold(
        topBar = {
            SolidarizaAppBar(
                canNavigateBack = false,
                onNavigateUp = {}
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Adicionar",
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { padding ->
        DistribuidorList(
            uiState = uiState,
            onClick = onCardClick,
            modifier = modifier.padding(padding),
        )
    }
}

@Preview
@Composable
fun HomeScreenContentPreview() {
    SolidarizaTheme {
        HomeScreen(
            onCardClick = {},
            onAddClick = {},
        )
    }
}