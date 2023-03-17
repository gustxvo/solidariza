package com.fatec.solidariza.ui.screens.distribuidor

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fatec.solidariza.R
import com.fatec.solidariza.data.model.Registro
import com.fatec.solidariza.ui.AppViewModelProvider
import com.fatec.solidariza.ui.SolidarizaAppBar

@Composable
fun DistribuidorScreen(
    canNavigateBack: Boolean,
    onAddClick: (Int, String) -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DistribuidorViewModel = viewModel(
        factory = AppViewModelProvider.Factory
    ),
) {
    val distribuidor by viewModel.distribuidor.collectAsState()

    Scaffold(
        topBar = {
            SolidarizaAppBar(
                canNavigateBack = canNavigateBack,
                onNavigateUp = onNavigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAddClick(distribuidor.id, distribuidor.regiao) }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_registro),
                    contentDescription = "Adicionar registro"
                )
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = modifier
                .padding(padding)
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
        ) {
            item {
                Column {
                    Divider(modifier = Modifier.fillMaxWidth())
                    DistribuidorHeader(distribuidor = distribuidor)
                }
            }
            item {
                Text(
                    text = "Registros",
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }

            items(distribuidor.registros.size) { index ->
                Divider()
                RegistroItem(registro = distribuidor.registros[index], item = index + 1)
            }
            if (distribuidor.registros.isEmpty()) {
                item {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    ) {
                        Text(text = "Não há registros no momento")
                    }
                }
            }
        }
    }
}

@Composable
fun DistribuidorHeader(distribuidor: DistribuidorState) {

    Row(
        modifier = Modifier
            .padding(top = 8.dp, start = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = Icons.Filled.AccountCircle,
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.tertiary),
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
                .padding(horizontal = 8.dp)
        )
        Column(modifier = Modifier) {
            Text(text = distribuidor.nome, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
            Text(text = distribuidor.endereco, fontSize = 16.sp)
            Text(text = distribuidor.produto, fontSize = 16.sp)
        }
    }

}

@Composable
fun RegistroItem(
    registro: Registro,
    item: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Registro #${item}",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(text = "Quantidade = ${registro.quantidade}", fontSize = 16.sp)
            Text(text = "Peso = ${registro.peso} toneladas", fontSize = 16.sp)
            Text(text = "Preço = R$${registro.preco}", fontSize = 16.sp)
        }

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxHeight()
        ) {
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = "Edit",
                tint = MaterialTheme.colorScheme.tertiary,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Delete",
                tint = Color.Red
            )
        }
    }
}
