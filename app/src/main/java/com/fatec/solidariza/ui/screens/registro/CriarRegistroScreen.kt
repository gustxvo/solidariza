package com.fatec.solidariza.ui.screens.registro

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fatec.solidariza.R
import com.fatec.solidariza.ui.AppViewModelProvider
import com.fatec.solidariza.ui.SolidarizaAppBar

@Composable
fun CriarRegistroScreen(
    canNavigateBack: Boolean,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit,
    viewModel: RegistroViewModel = viewModel(
        factory = AppViewModelProvider.Factory
    ),
) {
    Scaffold(
        topBar = {
            SolidarizaAppBar(
                canNavigateBack = canNavigateBack,
                onNavigateUp = onNavigateUp
            )
        },
    ) { paddingValues ->
        val registro by viewModel.registro.collectAsState()

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Divider(modifier = Modifier.fillMaxWidth())
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
            ) {
                Text(
                    "Adicionar Registro",
                    style = MaterialTheme.typography.displaySmall.copy(fontSize = 28.sp),
                    modifier = Modifier.padding(start = 12.dp, top = 16.dp, bottom = 8.dp)
                )
            }

            Image(
                painter = when (registro.produto) {
                    "Cacau" -> painterResource(R.drawable.cocoa)
                    "Banana" -> painterResource(R.drawable.bananas)
                    "Cana-de-açúcar" -> painterResource(R.drawable.sugar_cane)
                    else -> painterResource(R.drawable.oranges)
                },
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(88.dp)
                    .clip(CircleShape)
            )
            Text(
                text = registro.regiao,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            OutlinedTextField(
                label = { Text("Quantidade") },
                value = registro.quantidade,
                onValueChange = { viewModel.updateRegistro(registro.copy(quantidade = it)) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.padding(top = 16.dp)
            )
            OutlinedTextField(
                label = { Text("Peso") },
                value = registro.peso,
                onValueChange = { viewModel.updateRegistro(registro.copy(peso = it)) },
                suffix = {
                    Text(text = "toneladas")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),
                modifier = Modifier.padding(top = 4.dp)
            )
            OutlinedTextField(
                label = { Text("Total") },
                value = registro.preco,
                onValueChange = { viewModel.updateRegistro(registro.copy(preco = it)) },
                prefix = {
                    Text("R$")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),
                modifier = Modifier.padding(top = 4.dp)
            )
            Box(
                contentAlignment = Alignment.BottomEnd,
                modifier = Modifier.padding(8.dp)
            ) {
                ElevatedButton(
                    onClick = {
                        viewModel.addRegistro()
                        onAddClick()
                    }
                ) {
                    Text(text = "Adicionar")
                }
            }
        }
    }
}