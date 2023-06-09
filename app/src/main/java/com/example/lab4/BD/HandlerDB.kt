package com.example.lab4.BD

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.lab4.BD.BD

class HandlerDB(context: Context): SQLiteOpenHelper(context, BD().DATABASE_NAME, null, BD().DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(BD().CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL(BD().DELETE_TABLE)
        onCreate(db)
    }


}