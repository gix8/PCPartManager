package com.example.myapplication

// ==== Modelos de dados do PC Parts Manager ====
// Arquivo compartilhado pela equipe: define os "tipos" usados em todas as telas.

// Um item do histórico de manutenção de uma peça
data class RegistroManutencao(
    val titulo: String,
    val data: String,
    val origem: String
)

// Uma peça do inventário (GPU, CPU, memória, etc.)
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

// Um "slot" de componente dentro de um computador montado.
// valor == "" indica que o slot está vazio (sem peça instalada).
data class Slot(
    val nome: String,
    val valor: String
)

// Um computador montado (build)
data class Computador(
    val nome: String,
    val status: String, // "Ativo" ou "Em manutenção"
    val compatibilidade: String,
    val compativel: Boolean,
    val valorTotal: String,
    val consumo: String,
    val dataMontagem: String,
    val slots: List<Slot>
)

// Um item da lista de desejos (peças que a pessoa quer comprar)
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
