package com.example.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// ============================================================
// Tela desenvolvida por: [NOME DO INTEGRANTE 2]
// Tela 2 - Meu Inventário: lista de peças com busca e filtro
// ============================================================
@Composable
fun TelaInventario(
    pecas: List<Peca>,
    onAbrirPeca: (Peca) -> Unit,
    modifier: Modifier = Modifier
) {
    var busca by remember { mutableStateOf("") }
    var categoriaSelecionada by remember { mutableStateOf("Todas") }

    // Mesma quantidade de chips do mockup, pra caber numa linha só
    // sem precisar de scroll horizontal (não visto em aula).
    val categorias = remember {
        listOf("Todas", "CPU", "GPU", "Placa-mãe", "Memória RAM")
    }

    val pecasFiltradas = pecas.filter { peca ->
        val passaCategoria = categoriaSelecionada == "Todas" || peca.categoria == categoriaSelecionada
        val passaBusca = busca.isBlank() || peca.nome.lowercase().contains(busca.trim().lowercase())
        passaCategoria && passaBusca
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // "Ícone" simples: um Card quadrado com um caractere dentro,
                // igual ao "< Voltar" que já usamos como texto em vez de Icon.
                Card(shape = RoundedCornerShape(10.dp), modifier = Modifier.size(38.dp)) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("◆", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                    }
                }
                Spacer(modifier = Modifier.size(10.dp))
                Column {
                    Text("Meu Inventário", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Text(
                        text = "${pecas.size} PEÇAS",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Card(shape = RoundedCornerShape(10.dp), modifier = Modifier.size(38.dp)) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("+", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = busca,
            onValueChange = { busca = it },
            placeholder = { Text("Buscar peça ou modelo") },
            leadingIcon = { Text("🔍") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            categorias.forEach { categoria ->
                FiltroChip(
                    texto = categoria,
                    selecionado = categoria == categoriaSelecionada,
                    onClick = { categoriaSelecionada = categoria }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "${pecasFiltradas.size} PEÇAS",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(items = pecasFiltradas, key = { it.id }) { peca ->
                CartaoPeca(peca = peca, onClick = { onAbrirPeca(peca) })
            }
        }
    }
}
