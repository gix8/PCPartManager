package com.example.myapplication.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.myapplication.Computador
import com.example.myapplication.Peca

class BancoDados(context: Context) : SQLiteOpenHelper(context, "pc_parts.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE usuarios (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, email TEXT UNIQUE, senha TEXT)")
        db.execSQL("CREATE TABLE sessao (id INTEGER PRIMARY KEY, usuario_id INTEGER)")
        db.execSQL("CREATE TABLE pecas (id TEXT PRIMARY KEY, nome TEXT, categoria TEXT, marca TEXT, modelo TEXT, status TEXT, localizacao TEXT, preco TEXT, data_compra TEXT, numero_serie TEXT, loja TEXT)")
        db.execSQL("CREATE TABLE computadores (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, status TEXT, compatibilidade TEXT, compativel INTEGER, valor_total TEXT, consumo TEXT, data_montagem TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) = Unit

    fun cadastrar(nome: String, email: String, senha: String): Boolean = try {
        writableDatabase.insertOrThrow("usuarios", null, ContentValues().apply {
            put("nome", nome); put("email", email); put("senha", senha)
        }) != -1L
    } catch (_: Exception) { false }

    fun login(email: String, senha: String): Boolean {
        val cursor = readableDatabase.query("usuarios", arrayOf("id"), "email=? AND senha=?", arrayOf(email, senha), null, null, null)
        val usuarioId = cursor.use { if (it.moveToFirst()) it.getLong(0) else -1L }
        if (usuarioId == -1L) return false
        writableDatabase.delete("sessao", null, null)
        writableDatabase.insert("sessao", null, ContentValues().apply { put("id", 1); put("usuario_id", usuarioId) })
        return true
    }

    fun temSessao(): Boolean = readableDatabase.query("sessao", null, null, null, null, null, null).use { it.count > 0 }
    fun sair() { writableDatabase.delete("sessao", null, null) }

    fun listarPecas(): List<Peca> = cursorPecas(readableDatabase.query("pecas", null, null, null, null, null, "nome"))
    fun salvarPeca(peca: Peca) {
        val valores = pecaValores(peca)
        if (writableDatabase.update("pecas", valores, "id=?", arrayOf(peca.id)) == 0) writableDatabase.insert("pecas", null, valores)
    }
    fun deletarPeca(id: String) { writableDatabase.delete("pecas", "id=?", arrayOf(id)) }

    fun listarComputadores(): List<Computador> = cursorComputadores(readableDatabase.query("computadores", null, null, null, null, null, "nome"))
    fun salvarComputador(computador: Computador) {
        val valores = computadorValores(computador)
        if (computador.id == 0L) writableDatabase.insert("computadores", null, valores)
        else writableDatabase.update("computadores", valores, "id=?", arrayOf(computador.id.toString()))
    }
    fun deletarComputador(id: Long) { writableDatabase.delete("computadores", "id=?", arrayOf(id.toString())) }

    private fun pecaValores(p: Peca) = ContentValues().apply {
        put("id", p.id); put("nome", p.nome); put("categoria", p.categoria); put("marca", p.marca); put("modelo", p.modelo); put("status", p.status); put("localizacao", p.localizacao); put("preco", p.preco); put("data_compra", p.dataCompra); put("numero_serie", p.numeroSerie); put("loja", p.loja)
    }
    private fun computadorValores(c: Computador) = ContentValues().apply {
        put("nome", c.nome); put("status", c.status); put("compatibilidade", c.compatibilidade); put("compativel", if (c.compativel) 1 else 0); put("valor_total", c.valorTotal); put("consumo", c.consumo); put("data_montagem", c.dataMontagem)
    }
    private fun cursorPecas(cursor: Cursor) = cursor.use { c -> buildList { while (c.moveToNext()) add(Peca(c.getString(c.getColumnIndexOrThrow("id")), c.getString(c.getColumnIndexOrThrow("nome")), c.getString(c.getColumnIndexOrThrow("categoria")), c.getString(c.getColumnIndexOrThrow("marca")), c.getString(c.getColumnIndexOrThrow("modelo")), c.getString(c.getColumnIndexOrThrow("status")), c.getString(c.getColumnIndexOrThrow("localizacao")), c.getString(c.getColumnIndexOrThrow("preco")), c.getString(c.getColumnIndexOrThrow("data_compra")), c.getString(c.getColumnIndexOrThrow("numero_serie")), c.getString(c.getColumnIndexOrThrow("loja")))) } }
    private fun cursorComputadores(cursor: Cursor) = cursor.use { c -> buildList { while (c.moveToNext()) add(Computador(c.getLong(c.getColumnIndexOrThrow("id")), c.getString(c.getColumnIndexOrThrow("nome")), c.getString(c.getColumnIndexOrThrow("status")), c.getString(c.getColumnIndexOrThrow("compatibilidade")), c.getInt(c.getColumnIndexOrThrow("compativel")) == 1, c.getString(c.getColumnIndexOrThrow("valor_total")), c.getString(c.getColumnIndexOrThrow("consumo")), c.getString(c.getColumnIndexOrThrow("data_montagem")), emptyList())) } }
}
