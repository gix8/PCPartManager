package com.example.myapplication.data

import android.content.Context
import com.example.myapplication.data.dao.UsuarioDao
import com.example.myapplication.data.dao.PecaDao
import com.example.myapplication.data.dao.ComputadorDao
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AppDatabase(context: Context) : SQLiteOpenHelper(context, "pc_parts.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE usuarios (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, email TEXT UNIQUE, senha TEXT)")
        db.execSQL("CREATE TABLE sessao (id INTEGER PRIMARY KEY, usuario_id INTEGER)")
        db.execSQL("CREATE TABLE pecas (id TEXT PRIMARY KEY, nome TEXT, categoria TEXT, marca TEXT, modelo TEXT, status TEXT, localizacao TEXT, preco TEXT, data_compra TEXT, numero_serie TEXT, loja TEXT)")
        db.execSQL("CREATE TABLE computadores (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, status TEXT, compatibilidade TEXT, compativel INTEGER, valor_total TEXT, consumo TEXT, data_montagem TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) = Unit

    val usuarioDao = UsuarioDao(this)
    val pecaDao = PecaDao(this)
    val computadorDao = ComputadorDao(this)
}
