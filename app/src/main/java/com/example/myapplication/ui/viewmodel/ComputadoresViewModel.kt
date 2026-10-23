package com.example.myapplication.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.example.myapplication.data.AppDatabase
import com.example.myapplication.models.Computador

class ComputadoresViewModel(application: Application) : AndroidViewModel(application) {
    private val banco = AppDatabase(application).computadorDao
    var computadores by mutableStateOf(emptyList<Computador>()); private set
    fun listar() { computadores = banco.listarComputadores() }
    fun salvar(computador: Computador) { banco.salvarComputador(computador); listar() }
    fun deletar(id: Long) { banco.deletarComputador(id); listar() }
}
