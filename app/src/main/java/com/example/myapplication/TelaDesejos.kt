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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// ============================================================
// Tela desenvolvida por: João
// Tela 4 - Quero comprar: lista de desejos com filtro por prioridade
// ============================================================
@Composable
fun TelaDesejos(
    desejos: List<Desejo>,
    modifier: Modifier = Modifier
) {
    var prioridadeSelecionada by remember { mutableStateOf("Todas") }

    // Só as prioridades que cabem numa linha só, igual ao mockup
    // (sem scroll horizontal, que não vimos em aula).
    val prioridades = remember { listOf("Todas", "Alta", "Média") }

    val desejosFiltrados = desejos.filter {
        prioridadeSelecionada == "Todas" || it.prioridade == prioridadeSelecionada
    }

    val quantidadeAlta = desejos.count { it.prioridade == "Alta" }
    val custoTotal = desejos.sumOf { precoParaInteiro(it.precoAtual) }
    val fracaoAlta = if (desejos.isNotEmpty()) quantidadeAlta.toFloat() / desejos.size else 0f

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
                Card(shape = RoundedCornerShape(10.dp), modifier = Modifier.size(38.dp)) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("◆", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                    }
                }
                Spacer(modifier = Modifier.size(10.dp))
                Column {
                    Text("Quero comprar", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Text(
                        text = "LISTA DE DESEJOS",
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

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "CUSTO DA LISTA",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = formatarPreco(custoTotal),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${desejos.size} itens · $quantidadeAlta em prioridade alta",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(4.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(fracaoAlta)
                            .height(6.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF6FA37B)
                        ),
                        shape = RoundedCornerShape(3.dp),
                        content = {
                            Box(modifier = Modifier.fillMaxWidth().height(6.dp))
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            prioridades.forEach { prioridade ->
                FiltroChip(
                    texto = if (prioridade == "Todas") "Todas" else "Prioridade $prioridade",
                    selecionado = prioridade == prioridadeSelecionada,
                    onClick = { prioridadeSelecionada = prioridade }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(desejosFiltrados) { desejo ->
                CartaoDesejo(desejo)
            }
        }
    }
}

// Transforma "R$ 9.899" em 9899, e "R$ 9.899" + "R$ 1.349" em uma soma
// certinha pro card de custo total (não dá pra somar texto direto).
private fun precoParaInteiro(preco: String): Int {
    val digitos = preco.filter { it.isDigit() }
    return if (digitos.isEmpty()) 0 else digitos.toInt()
}

// Caminho inverso: 24034 vira "R$ 24.034", agrupando de 3 em 3 dígitos.
private fun formatarPreco(valor: Int): String {
    val texto = valor.toString()
    var resultado = ""
    for (indice in texto.indices) {
        val posicaoDoFim = texto.length - indice
        resultado += texto[indice]
        if (posicaoDoFim > 1 && posicaoDoFim % 3 == 1) resultado += "."
    }
    return "R$ $resultado"
}

@Composable
private fun CartaoDesejo(desejo: Desejo) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(desejo.nome, fontWeight = FontWeight.Bold)
                    Text(
                        text = "${desejo.categoria} · para ${desejo.destino}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
                BadgePrioridade(desejo.prioridade)
            }

            Text(desejo.precoAtual, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)

            val tendenciaTexto = if (desejo.caiu) "preço caiu ${desejo.tendencia}" else "preço subiu ${desejo.tendencia}"
            Text(
                text = "${desejo.loja} · $tendenciaTexto",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}

@Composable
private fun BadgePrioridade(prioridade: String) {
    val cor = corPrioridade(prioridade)
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = cor.copy(alpha = 0.18f))
    ) {
        Text(
            text = prioridade,
            color = cor,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}
