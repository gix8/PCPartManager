package com.example.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// ============================================================
// Tela desenvolvida por: Vinicius
// Tela 3 - Computadores: lista de montagens (builds) e seus slots
// ============================================================
@Composable
fun TelaComputadores(
    computadores: List<Computador>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text("Computadores", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text("${computadores.size} montagens", color = MaterialTheme.colorScheme.onSurfaceVariant)

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(14.dp)) {
            items(computadores) { computador ->
                CartaoComputador(computador)
            }
        }
    }
}

@Composable
private fun CartaoComputador(computador: Computador) {
    val corStatus = if (computador.status == "Ativo") Color(0xFF6FA37B) else Color(0xFFCFA76A)
    val corCompat = if (computador.compativel) Color(0xFF6FA37B) else Color(0xFFCFA76A)

    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(computador.nome, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                Text(computador.status, color = corStatus)
            }

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Text("Valor: ${computador.valorTotal}", style = MaterialTheme.typography.bodySmall)
                Text("Consumo: ${computador.consumo}", style = MaterialTheme.typography.bodySmall)
                Text("Montagem: ${computador.dataMontagem}", style = MaterialTheme.typography.bodySmall)
            }

            Text(
                text = computador.compatibilidade,
                color = corCompat,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold
            )

            // Linha divisória simples
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE4E1DA)
                ),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(0.dp),
                content = {
                    Box(modifier = Modifier.fillMaxWidth().height(1.dp))
                }
            )

            computador.slots.forEach { slot ->
                val vazio = slot.valor.isBlank()
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(slot.nome, style = MaterialTheme.typography.bodySmall)
                    Text(
                        text = if (vazio) "slot vazio" else slot.valor,
                        style = MaterialTheme.typography.bodySmall,
                        color = if (vazio) Color.Gray else MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}