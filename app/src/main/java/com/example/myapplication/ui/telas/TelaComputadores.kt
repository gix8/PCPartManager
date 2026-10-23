package com.example.myapplication.ui.telas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication.models.Computador

@Composable
fun TelaComputadores(computadores: List<Computador>, onSalvar: (Computador) -> Unit, onDeletar: (Long) -> Unit, modifier: Modifier = Modifier) {
    var editando by remember { mutableStateOf<Computador?>(null) }
    var criando by remember { mutableStateOf(false) }
    Column(modifier = modifier.fillMaxSize().padding(20.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Computadores", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Button(onClick = { criando = true }) { Text("Novo PC") }
        }
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) { items(computadores, key = { it.id }) { pc -> Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(14.dp)) { Text(pc.nome, fontWeight = FontWeight.Bold); Text("${pc.status} - ${pc.valorTotal}"); Row { TextButton(onClick = { editando = pc }) { Text("Alterar") }; TextButton(onClick = { onDeletar(pc.id) }) { Text("Deletar") } } } } } }
    }
    if (criando) ComputadorDialog(null, { criando = false }) { onSalvar(it); criando = false }
    editando?.let { pc -> ComputadorDialog(pc, { editando = null }) { onSalvar(it); editando = null } }
}

@Composable
private fun ComputadorDialog(pc: Computador?, onDismiss: () -> Unit, onSalvar: (Computador) -> Unit) {
    var nome by remember { mutableStateOf(pc?.nome ?: "") }
    var valor by remember { mutableStateOf(pc?.valorTotal ?: "") }
    AlertDialog(onDismissRequest = onDismiss, title = { Text(if (pc == null) "Cadastrar computador" else "Alterar computador") }, text = { Column { OutlinedTextField(nome, { nome = it }, label = { Text("Nome") }); OutlinedTextField(valor, { valor = it }, label = { Text("Valor total") }) } }, confirmButton = { Button(onClick = { if (nome.isNotBlank()) onSalvar(
        Computador(
            pc?.id ?: 0,
            nome,
            pc?.status ?: "Ativo",
            pc?.compatibilidade ?: "Compatível",
            pc?.compativel ?: true,
            valor,
            pc?.consumo ?: "",
            pc?.dataMontagem ?: "",
            emptyList()
        )
    ) }) { Text("Salvar") } }, dismissButton = { TextButton(onClick = onDismiss) { Text("Cancelar") } })
}
