package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.myapplication.Dados
import com.example.myapplication.TelaCadastro
import com.example.myapplication.TelaComputadores
import com.example.myapplication.TelaDesejos
import com.example.myapplication.TelaDetalhePeca
import com.example.myapplication.TelaInicio
import com.example.myapplication.TelaInventario
import com.example.myapplication.TelaLogin

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Login,
        modifier = modifier
    ) {
        composable<Login> {
            TelaLogin(
                aoEntrar = {
                    navController.navigate(Inicio) {
                        popUpTo(Login) { inclusive = true }
                    }
                },
                irParaCadastro = { navController.navigate(Cadastro) }
            )
        }

        composable<Cadastro> {
            TelaCadastro(irParaLogin = { navController.popBackStack() })
        }

        composable<Inicio> {
            TelaInicio(
                pecas = Dados.pecas,
                onVerTodasAsPecas = { navController.navigate(Inventario) },
                onAbrirPeca = { peca -> navController.navigate(DetalhePeca(peca.id)) }
            )
        }

        composable<Inventario> {
            TelaInventario(
                pecas = Dados.pecas,
                onAbrirPeca = { peca -> navController.navigate(DetalhePeca(peca.id)) }
            )
        }

        composable<Computadores> {
            TelaComputadores(computadores = Dados.computadores)
        }

        composable<Desejos> {
            TelaDesejos(desejos = Dados.desejos)
        }

        composable<DetalhePeca> { backStackEntry ->
            val rota = backStackEntry.toRoute<DetalhePeca>()
            val peca = Dados.pecas.firstOrNull { it.id == rota.id }

            if (peca != null) {
                TelaDetalhePeca(
                    peca = peca,
                    onVoltar = { navController.popBackStack() }
                )
            }
        }
    }
}
