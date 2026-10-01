package com.example.myapplication.auth

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class AuthManagerTest {
    @Test
    fun cadastroValidoPermiteLogin() {
        assertTrue(AuthManager.cadastro("Ana", "ana@up.edu.br", "123"))
        assertTrue(AuthManager.login("ana@up.edu.br", "123"))
    }

    @Test
    fun emailRepetidoEhRecusado() {
        AuthManager.cadastro("Ana", "repetido@up.edu.br", "123")

        assertFalse(AuthManager.cadastro("Outra Ana", "repetido@up.edu.br", "456"))
    }

    @Test
    fun senhaIncorretaEhRecusada() {
        AuthManager.cadastro("Ana", "senha@up.edu.br", "123")

        assertFalse(AuthManager.login("senha@up.edu.br", "errada"))
    }

    @Test
    fun limparRemoveUsuariosCadastrados() {
        AuthManager.cadastro("Ana", "limpar@up.edu.br", "123")

        AuthManager.limpar()

        assertFalse(AuthManager.login("limpar@up.edu.br", "123"))
    }
}
