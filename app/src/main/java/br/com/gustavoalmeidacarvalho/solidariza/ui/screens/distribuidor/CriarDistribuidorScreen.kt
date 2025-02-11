package br.com.gustavoalmeidacarvalho.solidariza.ui.screens.distribuidor

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.gustavoalmeidacarvalho.solidariza.ui.AppViewModelProvider
import br.com.gustavoalmeidacarvalho.solidariza.ui.SolidarizaAppBar

@Composable
fun CriarDistribuidorScreen(
    canNavigateBack: Boolean,
    onNavigateUp: () -> Unit,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CriarDistribuidorViewModel = viewModel(
        factory = AppViewModelProvider.Factory
    ),
) {
    Scaffold(
        topBar = {
            SolidarizaAppBar(
                canNavigateBack = canNavigateBack,
                onNavigateUp = onNavigateUp
            )
        }
    ) { paddingValues ->
        val distribuidor by viewModel.distribuidor.collectAsState()

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Divider(modifier = Modifier.fillMaxWidth())

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "Cadastrar Distribuidor",
                    style = MaterialTheme.typography.displaySmall.copy(fontSize = 28.sp)
                )
            }

            Image(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "Foto de perfil",
                modifier = Modifier.size(120.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.tertiary),
            )
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    label = { Text("Nome") },
                    value = distribuidor.nome,
                    onValueChange = { viewModel.updateDistribuidor(distribuidor.copy(nome = it)) }
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    label = { Text("Região") },
                    value = distribuidor.regiao,
                    onValueChange = { viewModel.updateDistribuidor(distribuidor.copy(regiao = it)) }
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    label = { Text("Endereço") },
                    value = distribuidor.endereco,
                    onValueChange = {
                        viewModel.updateDistribuidor(distribuidor.copy(endereco = it))
                    }
                )
            }
            Box(
                contentAlignment = Alignment.BottomEnd
            ) {
                ElevatedButton(
                    onClick = {
                        viewModel.saveDistribuidor()
                        onAddClick()
                    }
                ) {
                    Text(text = "Adicionar")
                }
            }
        }
    }
}