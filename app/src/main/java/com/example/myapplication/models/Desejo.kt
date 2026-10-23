package com.example.myapplication.models

data class Desejo(
    val nome: String,
    val categoria: String,
    val destino: String, // para qual computador é destinada
    val prioridade: String, // "Alta", "Média" ou "Baixa"
    val precoAtual: String,
    val precoAnterior: String,
    val tendencia: String, // ex: "-14%"
    val caiu: Boolean,
    val loja: String
)
