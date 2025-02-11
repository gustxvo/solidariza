package br.com.gustavoalmeidacarvalho.solidariza.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.gustavoalmeidacarvalho.solidariza.data.model.Distribuidor
import br.com.gustavoalmeidacarvalho.solidariza.ui.theme.SolidarizaTheme

@Composable
fun DistribuidorList(
    uiState: DistribuidoresPorRegiao,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        item {
            Divider(modifier = Modifier.fillMaxWidth())
            Text(
                "Distribuidores",
                style = MaterialTheme.typography.displaySmall.copy(fontSize = 28.sp),
                modifier = Modifier.padding(start = 12.dp, top = 16.dp, bottom = 8.dp)
            )
        }

        for (grupos in uiState) {
            item(grupos.key) {
                Text(
                    grupos.key,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            items(grupos.value, key = { it.id }) { distribuidor ->
                DistribuidorItem(distribuidor = distribuidor, onClick = onClick)
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DistribuidorItem(
    distribuidor: Distribuidor,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.07f)
        ),
        onClick = { onClick(distribuidor.id) },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                imageVector = Icons.Filled.AccountCircle,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.tertiary.copy(alpha = 0.8f)),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(48.dp)
            )
            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(
                    text = distribuidor.nome, style = MaterialTheme.typography.headlineSmall.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Text(
                    text = distribuidor.produto,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.paddingFromBaseline(8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun DistribuidorItemPreview() {
    SolidarizaTheme {
        DistribuidorItem(
            distribuidor = Distribuidor(
                1,
                "Alberto",
                "Laranja",
                "Norte",
                "Rua da calçada"
            ),
            onClick = {}
        )
    }
}