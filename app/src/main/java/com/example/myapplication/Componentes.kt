package com.example.myapplication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// ==== Funções auxiliares (when) para cores de status e prioridade ====

fun corStatus(status: String): Color {
    return when (status) {
        "Disponível" -> Color(0xFF6FA37B)
        "Instalada" -> Color(0xFF6E8FBF)
        "Manutenção" -> Color(0xFFCFA76A)
        "Reservada" -> Color(0xFFA290C4)
        else -> Color.Gray
    }
}

fun corPrioridade(prioridade: String): Color {
    return when (prioridade) {
        "Alta" -> Color(0xFFC5726B)
        "Média" -> Color(0xFFCFA76A)
        else -> Color(0xFF8A857D)
    }
}

// ==== Componentes reutilizados por mais de uma tela ====

@Composable
fun BadgeStatus(status: String) {
    val cor = corStatus(status)
    Text(
        text = status,
        color = cor,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.bodySmall
    )
}

@Composable
fun CartaoPeca(peca: Peca, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(peca.nome, fontWeight = FontWeight.Bold)
                BadgeStatus(peca.status)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${peca.marca} · ${peca.modelo} · ${peca.preco}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun FiltroChip(texto: String, selecionado: Boolean, onClick: () -> Unit) {
    val corTexto = if (selecionado)
        MaterialTheme.colorScheme.onPrimary
    else
        MaterialTheme.colorScheme.onSurfaceVariant

    Card(
        modifier = Modifier.clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (selecionado)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Text(
            text = texto,
            color = corTexto,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
        )
    }
}
