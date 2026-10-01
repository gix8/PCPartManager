package com.example.myapplication.auth

object AuthManager {
    private data class Usuario(
        val nome: String,
        val email: String,
        val senha: String
    )

    private val usuarios = mutableListOf<Usuario>()

    fun cadastro(nome: String, email: String, senha: String): Boolean {
        if (usuarios.any { it.email == email }) return false

        usuarios.add(Usuario(nome = nome, email = email, senha = senha))
        return true
    }

    fun login(email: String, senha: String): Boolean {
        return usuarios.any { it.email == email && it.senha == senha }
    }
}
