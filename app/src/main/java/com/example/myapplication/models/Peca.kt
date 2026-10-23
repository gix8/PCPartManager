package com.example.myapplication.models

data class Peca(
    val id: String,
    val nome: String,
    val categoria: String,
    val marca: String,
    val modelo: String,
    val status: String, // "Disponível", "Instalada", "Manutenção" ou "Reservada"
    val localizacao: String,
    val preco: String,
    val dataCompra: String,
    val numeroSerie: String,
    val loja: String,
    val computador: String? = null, // preenchido quando a peça está instalada em um PC
    val especificacoes: List<Pair<String, String>> = emptyList(),
    val historico: List<RegistroManutencao> = emptyList()
)
