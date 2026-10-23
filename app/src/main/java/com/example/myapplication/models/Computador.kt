package com.example.myapplication.models

data class Computador(
    val id: Long = 0,
    val nome: String,
    val status: String, // "Ativo" ou "Em manutenção"
    val compatibilidade: String,
    val compativel: Boolean,
    val valorTotal: String,
    val consumo: String,
    val dataMontagem: String,
    val slots: List<Slot>
)
