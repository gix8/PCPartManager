package com.example.myapplication.data.dao

import android.content.ContentValues
import com.example.myapplication.data.AppDatabase

class UsuarioDao(private val database: AppDatabase) {
    fun cadastrar(nome: String, email: String, senha: String): Boolean = try {
        database.writableDatabase.insertOrThrow("usuarios", null, ContentValues().apply {
            put("nome", nome); put("email", email); put("senha", senha)
        }) != -1L
    } catch (_: Exception) { false }

    fun login(email: String, senha: String): Boolean {
        val cursor = database.readableDatabase.query("usuarios", arrayOf("id"), "email=? AND senha=?", arrayOf(email, senha), null, null, null)
        val usuarioId = cursor.use { if (it.moveToFirst()) it.getLong(0) else -1L }
        if (usuarioId == -1L) return false
        database.writableDatabase.delete("sessao", null, null)
        database.writableDatabase.insert("sessao", null, ContentValues().apply { put("id", 1); put("usuario_id", usuarioId) })
        return true
    }

    fun temSessao(): Boolean = database.readableDatabase.query("sessao", null, null, null, null, null, null).use { it.count > 0 }
    fun sair() { database.writableDatabase.delete("sessao", null, null) }
}
