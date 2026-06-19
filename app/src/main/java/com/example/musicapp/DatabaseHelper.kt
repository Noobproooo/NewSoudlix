package com.example.musicapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "MusicApp.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL(
            """
            CREATE TABLE Users(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                email TEXT,
                password TEXT
            )
            """
        )

        // tài khoản mẫu
        db.execSQL(
            """
            INSERT INTO Users(email,password)
            VALUES('admin@gmail.com','123456')
            """
        )
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS Users")
        onCreate(db)
    }

    fun checkLogin(
        email: String,
        password: String
    ): Boolean {

        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM Users WHERE email=? AND password=?",
            arrayOf(email, password)
        )

        val result = cursor.count > 0

        cursor.close()

        return result
    }
}