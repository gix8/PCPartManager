package com.example.myapplication.data.dao

import android.content.ContentValues
import com.example.myapplication.data.AppDatabase
import android.database.Cursor
import com.example.myapplication.models.Computador

class ComputadorDao(private val database: AppDatabase) {
    fun listarComputadores(): List<Computador> = cursorComputadores(database.readableDatabase.query("computadores", null, null, null, null, null, "nome"))
    fun salvarComputador(computador: Computador) {
        val valores = computadorValores(computador)
        if (computador.id == 0L) database.writableDatabase.insert("computadores", null, valores)
        else database.writableDatabase.update("computadores", valores, "id=?", arrayOf(computador.id.toString()))
    }
    fun deletarComputador(id: Long) { database.writableDatabase.delete("computadores", "id=?", arrayOf(id.toString())) }

    private fun computadorValores(c: Computador) = ContentValues().apply {
        put("nome", c.nome); put("status", c.status); put("compatibilidade", c.compatibilidade); put("compativel", if (c.compativel) 1 else 0); put("valor_total", c.valorTotal); put("consumo", c.consumo); put("data_montagem", c.dataMontagem)
    }
    private fun cursorComputadores(cursor: Cursor) = cursor.use { c -> buildList { while (c.moveToNext()) add(Computador(c.getLong(c.getColumnIndexOrThrow("id")), c.getString(c.getColumnIndexOrThrow("nome")), c.getString(c.getColumnIndexOrThrow("status")), c.getString(c.getColumnIndexOrThrow("compatibilidade")), c.getInt(c.getColumnIndexOrThrow("compativel")) == 1, c.getString(c.getColumnIndexOrThrow("valor_total")), c.getString(c.getColumnIndexOrThrow("consumo")), c.getString(c.getColumnIndexOrThrow("data_montagem")), emptyList())) } }
}
