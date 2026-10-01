package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.hasRoute
import com.example.myapplication.navigation.AppNavigation
import com.example.myapplication.navigation.Cadastro
import com.example.myapplication.navigation.Computadores
import com.example.myapplication.navigation.Desejos
import com.example.myapplication.navigation.DetalhePeca
import com.example.myapplication.navigation.Inicio
import com.example.myapplication.navigation.Inventario
import com.example.myapplication.navigation.Login
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val destination = backStackEntry?.destination
    val mostrarMenu = destination?.hasRoute<Login>() != true &&
        destination?.hasRoute<Cadastro>() != true &&
        destination?.hasRoute<DetalhePeca>() != true

    val telaAtual = when {
        destination?.hasRoute<Inventario>() == true -> "inventario"
        destination?.hasRoute<Computadores>() == true -> "computadores"
        destination?.hasRoute<Desejos>() == true -> "desejos"
        else -> "inicio"
    }

    Scaffold(
        bottomBar = {
            if (mostrarMenu) {
                BarraDeNavegacao(
                    telaAtual = telaAtual,
                    onTrocarTela = { novaTela ->
                        when (novaTela) {
                            "inicio" -> navController.navigate(Inicio)
                            "inventario" -> navController.navigate(Inventario)
                            "computadores" -> navController.navigate(Computadores)
                            "desejos" -> navController.navigate(Desejos)
                        }
                    },
                    onSair = {
                        navController.navigate(Login) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        AppNavigation(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BarraDeNavegacao(
    telaAtual: String,
    onTrocarTela: (String) -> Unit,
    onSair: () -> Unit
) {
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
        abas.forEach { (id, rotulo) ->
            ItemMenu(
                rotulo = rotulo,
                selecionado = id == telaAtual,
                onClick = { onTrocarTela(id) }
            )
        }
        ItemMenu(rotulo = "Sair", selecionado = false, onClick = onSair)
    }
}

@Composable
private fun ItemMenu(rotulo: String, selecionado: Boolean, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Text(
            text = rotulo,
            color = if (selecionado) MaterialTheme.colorScheme.primary else Color.Gray
        )
    }
}
