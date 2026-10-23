package com.example.myapplication.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.example.myapplication.data.AppDatabase
import com.example.myapplication.models.Peca

class PecasViewModel(application: Application) : AndroidViewModel(application) {
    private val banco = AppDatabase(application).pecaDao
    var pecas by mutableStateOf(emptyList<Peca>()); private set
    fun listar() { pecas = banco.listarPecas() }
    fun salvar(peca: Peca) { banco.salvarPeca(peca); listar() }
    fun deletar(id: String) { banco.deletarPeca(id); listar() }
}