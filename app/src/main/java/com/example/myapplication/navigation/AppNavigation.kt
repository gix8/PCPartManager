package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.myapplication.TelaCadastro
import com.example.myapplication.TelaComputadores
import com.example.myapplication.TelaDesejos
import com.example.myapplication.TelaDetalhePeca
import com.example.myapplication.TelaInicio
import com.example.myapplication.TelaInventario
import com.example.myapplication.TelaLogin
import com.example.myapplication.Dados
import com.example.myapplication.viewmodel.AuthViewModel
import com.example.myapplication.viewmodel.ComputadoresViewModel
import com.example.myapplication.viewmodel.PecasViewModel
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AppNavigation(navController: NavHostController, authViewModel: AuthViewModel, modifier: Modifier = Modifier) {
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
                irParaCadastro = { navController.navigate(Cadastro) },
                viewModel = authViewModel
            )
        }

        composable<Cadastro> {
            TelaCadastro(irParaLogin = { navController.popBackStack() }, viewModel = authViewModel)
        }

        composable<Inicio> {
            val pecasViewModel: PecasViewModel = viewModel()
            LaunchedEffect(Unit) { pecasViewModel.listar() }
            TelaInicio(
                pecas = pecasViewModel.pecas,
                onVerTodasAsPecas = { navController.navigate(Inventario) },
                onAbrirPeca = { peca -> navController.navigate(DetalhePeca(peca.id)) }
            )
        }

        composable<Inventario> {
            val pecasViewModel: PecasViewModel = viewModel()
            LaunchedEffect(Unit) { pecasViewModel.listar() }
            TelaInventario(
                pecas = pecasViewModel.pecas,
                onAbrirPeca = { peca -> navController.navigate(DetalhePeca(peca.id)) },
                onSalvar = pecasViewModel::salvar,
                onDeletar = pecasViewModel::deletar
            )
        }

        composable<Computadores> {
            val computadoresViewModel: ComputadoresViewModel = viewModel()
            LaunchedEffect(Unit) { computadoresViewModel.listar() }
            TelaComputadores(
                computadores = computadoresViewModel.computadores,
                onSalvar = computadoresViewModel::salvar,
                onDeletar = computadoresViewModel::deletar
            )
        }

        composable<Desejos> {
            TelaDesejos(desejos = Dados.desejos)
        }

        composable<DetalhePeca> { backStackEntry ->
            val pecasViewModel: PecasViewModel = viewModel()
            LaunchedEffect(Unit) { pecasViewModel.listar() }
            val rota = backStackEntry.toRoute<DetalhePeca>()
            val peca = pecasViewModel.pecas.firstOrNull { it.id == rota.id }

            if (peca != null) {
                TelaDetalhePeca(
                    peca = peca,
                    onVoltar = { navController.popBackStack() }
                )
            }
        }
    }
}
