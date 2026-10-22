package com.example.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import java.util.UUID

@Composable
fun TelaInventario(pecas: List<Peca>, onAbrirPeca: (Peca) -> Unit, onSalvar: (Peca) -> Unit, onDeletar: (String) -> Unit, modifier: Modifier = Modifier) {
    var busca by remember { mutableStateOf("") }
    var pecaEmEdicao by remember { mutableStateOf<Peca?>(null) }
    var criando by remember { mutableStateOf(false) }
    val filtradas = pecas.filter { it.nome.contains(busca, true) || it.categoria.contains(busca, true) }
    Column(modifier = modifier.fillMaxSize().padding(20.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Meu Inventário", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Button(onClick = { criando = true }) { Text("Nova peça") }
        }
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(busca, { busca = it }, label = { Text("Buscar peça") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(12.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(filtradas, key = { it.id }) { peca ->
                Card(modifier = Modifier.fillMaxWidth()) { Column(Modifier.padding(12.dp)) {
                    Text(peca.nome, fontWeight = FontWeight.Bold); Text("${peca.categoria} - ${peca.marca}")
                    Row { TextButton(onClick = { onAbrirPeca(peca) }) { Text("Ver") }; TextButton(onClick = { pecaEmEdicao = peca }) { Text("Alterar") }; TextButton(onClick = { onDeletar(peca.id) }) { Text("Deletar") } }
                } }
            }
        }
    }
    if (criando) PecaDialog(null, { criando = false }) { onSalvar(it); criando = false }
    pecaEmEdicao?.let { PecaDialog(it, { pecaEmEdicao = null }) { nova -> onSalvar(nova); pecaEmEdicao = null } }
}

@Composable
private fun PecaDialog(peca: Peca?, onDismiss: () -> Unit, onSalvar: (Peca) -> Unit) {
    var nome by remember { mutableStateOf(peca?.nome ?: "") }
    var categoria by remember { mutableStateOf(peca?.categoria ?: "") }
    var marca by remember { mutableStateOf(peca?.marca ?: "") }
    var modelo by remember { mutableStateOf(peca?.modelo ?: "") }
    var preco by remember { mutableStateOf(peca?.preco ?: "") }
    AlertDialog(onDismissRequest = onDismiss, title = { Text(if (peca == null) "Cadastrar peça" else "Alterar peça") }, text = { Column { OutlinedTextField(nome, { nome = it }, label = { Text("Nome") }); OutlinedTextField(categoria, { categoria = it }, label = { Text("Categoria") }); OutlinedTextField(marca, { marca = it }, label = { Text("Marca") }); OutlinedTextField(modelo, { modelo = it }, label = { Text("Modelo") }); OutlinedTextField(preco, { preco = it }, label = { Text("Preço") }) } }, confirmButton = { Button(onClick = { if (nome.isNotBlank()) onSalvar(Peca(peca?.id ?: UUID.randomUUID().toString(), nome, categoria, marca, modelo, peca?.status ?: "Disponível", peca?.localizacao ?: "", preco, peca?.dataCompra ?: "", peca?.numeroSerie ?: "", peca?.loja ?: "")) }) { Text("Salvar") } }, dismissButton = { TextButton(onClick = onDismiss) { Text("Cancelar") } })
}
