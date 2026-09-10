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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// ============================================================
// Tela desenvolvida por: Bernardo
// Tela 1 - Início: visão geral do inventário (dashboard)
// ============================================================
@Composable
fun TelaInicio(
    pecas: List<Peca>,
    onVerTodasAsPecas: () -> Unit,
    onAbrirPeca: (Peca) -> Unit,
    modifier: Modifier = Modifier
) {
    val disponiveis = pecas.filter { it.status == "Disponível" }.size
    val instaladas = pecas.filter { it.status == "Instalada" }.size
    val manutencao = pecas.filter { it.status == "Manutenção" }.size

    val categorias = remember { listOf("GPU", "CPU", "Memória RAM", "SSD / HD") }
    val totalPecas = pecas.size

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Column {
            Text(
                text = "PC Parts Manager",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Visão geral do inventário",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            CartaoEstatistica("Disponíveis", disponiveis.toString(), Color(0xFF6FA37B))
            CartaoEstatistica("Instaladas", instaladas.toString(), Color(0xFF6E8FBF))
            CartaoEstatistica("Manutenção", manutencao.toString(), Color(0xFFCFA76A))
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text("Peças por categoria", fontWeight = FontWeight.Bold)

                categorias.forEach { categoria ->
                    val quantidade = pecas.filter { it.categoria == categoria }.size
                    val fracao = if (totalPecas > 0) quantidade.toFloat() / totalPecas else 0f

                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(categoria, style = MaterialTheme.typography.bodySmall)
                            Text("$quantidade", style = MaterialTheme.typography.bodySmall)
                        }
Box(
    modifier = Modifier
        .fillMaxWidth()
        .height(8.dp)
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(fracao)
            .height(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(4.dp),
        content = {
            Box(modifier = Modifier.fillMaxWidth().height(8.dp))
        }
    )
}
                    }
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Adicionadas recentemente", fontWeight = FontWeight.Bold)
            TextButton(onClick = onVerTodasAsPecas) {
                Text("Ver todas")
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            pecas.take(4).forEach { peca ->
                CartaoPeca(peca = peca, onClick = { onAbrirPeca(peca) })
            }
        }
    }
}

@Composable
private fun CartaoEstatistica(rotulo: String, valor: String, cor: Color) {
    Card(modifier = Modifier.width(108.dp)) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(valor, style = MaterialTheme.typography.headlineSmall, color = cor, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(2.dp))
            Text(rotulo, style = MaterialTheme.typography.bodySmall)
        }
    }
}
