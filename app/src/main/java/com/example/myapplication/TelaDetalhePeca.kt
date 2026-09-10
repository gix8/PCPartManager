package com.example.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// ============================================================
// Tela desenvolvida por: [NOME DO INTEGRANTE 5]
// Tela 5 - Detalhe da peça: informações completas de uma peça
// ============================================================
@Composable
fun TelaDetalhePeca(
    peca: Peca,
    onVoltar: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextButton(onClick = onVoltar) {
            Text("< Voltar")
        }

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(peca.nome, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            BadgeStatus(peca.status)
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Informações", fontWeight = FontWeight.Bold)
                LinhaInfo("Marca / modelo", "${peca.marca} - ${peca.modelo}")
                LinhaInfo("Categoria", peca.categoria)
                LinhaInfo("Localização", peca.computador ?: peca.localizacao)
                LinhaInfo("Nº de série", peca.numeroSerie)
                LinhaInfo("Valor pago", peca.preco)
                LinhaInfo("Data de compra", peca.dataCompra)
                LinhaInfo("Loja", peca.loja)
            }
        }

        if (peca.especificacoes.isNotEmpty()) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Especificações técnicas", fontWeight = FontWeight.Bold)
                    peca.especificacoes.forEach { especificacao ->
                        LinhaInfo(especificacao.first, especificacao.second)
                    }
                }
            }
        }

        if (peca.historico.isNotEmpty()) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text("Histórico", fontWeight = FontWeight.Bold)
                    peca.historico.forEach { registro ->
                        Column {
                            Text(registro.titulo, fontWeight = FontWeight.Medium)
                            Text(
                                text = "${registro.data} - ${registro.origem}",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LinhaInfo(chave: String, valor: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(chave, color = Color.Gray, style = MaterialTheme.typography.bodySmall)
        Text(valor, style = MaterialTheme.typography.bodySmall)
    }
}
