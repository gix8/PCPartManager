package com.example.myapplication.navigation

import kotlinx.serialization.Serializable

@Serializable
data object Login

@Serializable
data object Cadastro

@Serializable
data object Inicio

@Serializable
data object Inventario

@Serializable
data object Computadores

@Serializable
data object Desejos

@Serializable
data class DetalhePeca(val id: String)
