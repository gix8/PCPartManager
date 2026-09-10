package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

// ============================================================
// MainActivity - ponto de entrada do app.
// Responsável por: [NOME DO INTEGRANTE responsável pela integração]
//
// Guarda qual tela está ativa em uma variável de estado (var + remember)
// e decide, com "when", qual Composable mostrar. É a mesma ideia usada
// em aula no MainActivity.kt do projeto "myapplication"
// (lembra do comentário/descomentário de Formulario() / ListaColumn()?),
// só que agora trocamos a tela clicando na barra de navegação embaixo.
// ============================================================
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    // Guarda qual aba está selecionada
    var telaAtual by remember { mutableStateOf("inicio") }

    // Quando != null, mostramos a tela de detalhe por cima das abas
    var pecaSelecionada by remember { mutableStateOf<Peca?>(null) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        content = {
            Column(verticalArrangement = Arrangement.Top, modifier = Modifier.fillMaxSize()) {
                val peca = pecaSelecionada

                when {
                    peca != null -> TelaDetalhePeca(
                        peca = peca,
                        onVoltar = { pecaSelecionada = null },
                        modifier = Modifier.weight(1f)
                    )

                    telaAtual == "inicio" -> TelaInicio(
                        pecas = Dados.pecas,
                        onVerTodasAsPecas = { telaAtual = "inventario" },
                        onAbrirPeca = { pecaClicada -> pecaSelecionada = pecaClicada },
                        modifier = Modifier.weight(1f)
                    )

                    telaAtual == "inventario" -> TelaInventario(
                        pecas = Dados.pecas,
                        onAbrirPeca = { pecaClicada -> pecaSelecionada = pecaClicada },
                        modifier = Modifier.weight(1f)
                    )

                    telaAtual == "computadores" -> TelaComputadores(
                        computadores = Dados.computadores,
                        modifier = Modifier.weight(1f)
                    )

                    telaAtual == "desejos" -> TelaDesejos(
                        desejos = Dados.desejos,
                        modifier = Modifier.weight(1f)
                    )
                }

                if (pecaSelecionada == null) {
                    BarraDeNavegacao(
                        telaAtual = telaAtual,
                        onTrocarTela = { novaTela -> telaAtual = novaTela }
                    )
                }
            }
        }
    )
}

@Composable
fun BarraDeNavegacao(telaAtual: String, onTrocarTela: (String) -> Unit) {
    val abas = listOf(
        "inicio" to "Início",
        "inventario" to "Peças",
        "computadores" to "PCs",
        "desejos" to "Desejos"
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        abas.forEach { aba ->
            val id = aba.first
            val rotulo = aba.second
            val selecionada = id == telaAtual

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable { onTrocarTela(id) }
            ) {
                Text(
                    text = rotulo,
                    color = if (selecionada) MaterialTheme.colorScheme.primary else Color.Gray
                )
            }
        }
    }
}