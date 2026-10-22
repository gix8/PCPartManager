package com.example.myapplication.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.example.myapplication.Computador
import com.example.myapplication.Peca
import com.example.myapplication.data.BancoDados

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val banco = BancoDados(application)
    var sessaoAtiva by mutableStateOf(banco.temSessao()); private set
    fun login(email: String, senha: String) = banco.login(email, senha).also { sessaoAtiva = it }
    fun cadastrar(nome: String, email: String, senha: String) = banco.cadastrar(nome, email, senha)
    fun sair() { banco.sair(); sessaoAtiva = false }
}

class PecasViewModel(application: Application) : AndroidViewModel(application) {
    private val banco = BancoDados(application)
    var pecas by mutableStateOf(emptyList<Peca>()); private set
    fun listar() { pecas = banco.listarPecas() }
    fun salvar(peca: Peca) { banco.salvarPeca(peca); listar() }
    fun deletar(id: String) { banco.deletarPeca(id); listar() }
}

class ComputadoresViewModel(application: Application) : AndroidViewModel(application) {
    private val banco = BancoDados(application)
    var computadores by mutableStateOf(emptyList<Computador>()); private set
    fun listar() { computadores = banco.listarComputadores() }
    fun salvar(computador: Computador) { banco.salvarComputador(computador); listar() }
    fun deletar(id: Long) { banco.deletarComputador(id); listar() }
}
