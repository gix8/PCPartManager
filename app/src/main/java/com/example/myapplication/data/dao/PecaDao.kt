package com.example.myapplication.data.dao

import android.content.ContentValues
import com.example.myapplication.data.AppDatabase
import android.database.Cursor
import com.example.myapplication.models.Peca

class PecaDao(private val database: AppDatabase) {
    fun listarPecas(): List<Peca> = cursorPecas(database.readableDatabase.query("pecas", null, null, null, null, null, "nome"))
    fun salvarPeca(peca: Peca) {
        val valores = pecaValores(peca)
        if (database.writableDatabase.update("pecas", valores, "id=?", arrayOf(peca.id)) == 0) database.writableDatabase.insert("pecas", null, valores)
    }
    fun deletarPeca(id: String) { database.writableDatabase.delete("pecas", "id=?", arrayOf(id)) }

    private fun pecaValores(p: Peca) = ContentValues().apply {
        put("id", p.id); put("nome", p.nome); put("categoria", p.categoria); put("marca", p.marca); put("modelo", p.modelo); put("status", p.status); put("localizacao", p.localizacao); put("preco", p.preco); put("data_compra", p.dataCompra); put("numero_serie", p.numeroSerie); put("loja", p.loja)
    }
    private fun cursorPecas(cursor: Cursor) = cursor.use { c -> buildList { while (c.moveToNext()) add(Peca(c.getString(c.getColumnIndexOrThrow("id")), c.getString(c.getColumnIndexOrThrow("nome")), c.getString(c.getColumnIndexOrThrow("categoria")), c.getString(c.getColumnIndexOrThrow("marca")), c.getString(c.getColumnIndexOrThrow("modelo")), c.getString(c.getColumnIndexOrThrow("status")), c.getString(c.getColumnIndexOrThrow("localizacao")), c.getString(c.getColumnIndexOrThrow("preco")), c.getString(c.getColumnIndexOrThrow("data_compra")), c.getString(c.getColumnIndexOrThrow("numero_serie")), c.getString(c.getColumnIndexOrThrow("loja")))) } }
}
