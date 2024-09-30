package com.example.uncle_homes.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "UncleHomes.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_USERS = "users"
        private const val COLUMN_ID = "id"
        private const val COLUMN_FULL_NAME = "full_name"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_PHONE = "phone"
        private const val COLUMN_PASSWORD = "password"
        private const val COLUMN_ROLE = "role"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """CREATE TABLE $TABLE_USERS (
            $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_FULL_NAME TEXT,
            $COLUMN_EMAIL TEXT UNIQUE,
            $COLUMN_PHONE TEXT,
            $COLUMN_PASSWORD TEXT,
            $COLUMN_ROLE TEXT
        )"""
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    fun addUser(fullName: String, email: String, phone: String, password: String, role: String): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_FULL_NAME, fullName)
            put(COLUMN_EMAIL, email)
            put(COLUMN_PHONE, phone)
            put(COLUMN_PASSWORD, password)
            put(COLUMN_ROLE, role)
        }
        return db.insert(TABLE_USERS, null, values)
    }

    fun getUserByEmailAndPassword(email: String, password: String): Pair<Boolean, String?> {
        val db = this.readableDatabase
        val selection = "$COLUMN_EMAIL = ? AND $COLUMN_PASSWORD = ?"
        val selectionArgs = arrayOf(email, password)
        val cursor = db.query(TABLE_USERS, arrayOf(COLUMN_ROLE), selection, selectionArgs, null, null, null)

        return if (cursor.moveToFirst()) {
            val roleIndex = cursor.getColumnIndex(COLUMN_ROLE)
            if (roleIndex != -1) {
                val role = cursor.getString(roleIndex)
                cursor.close()
                Pair(true, role)
            } else {
                cursor.close()
                Pair(false, null)
            }
        } else {
            cursor.close()
            Pair(false, null)
        }
    }

    fun getUserData(email: String): UserData? {
        val db = this.readableDatabase
        val selection = "$COLUMN_EMAIL = ?"
        val selectionArgs = arrayOf(email)
        val cursor = db.query(TABLE_USERS, null, selection, selectionArgs, null, null, null)

        return if (cursor.moveToFirst()) {
            val userData = cursor.use {
                UserData(
                    fullName = it.getStringOrNull(COLUMN_FULL_NAME) ?: "",
                    email = it.getStringOrNull(COLUMN_EMAIL) ?: "",
                    phone = it.getStringOrNull(COLUMN_PHONE) ?: "",
                    role = it.getStringOrNull(COLUMN_ROLE) ?: ""
                )
            }
            userData
        } else {
            null
        }
    }

    private fun Cursor.getStringOrNull(columnName: String): String? {
        val columnIndex = getColumnIndex(columnName)
        return if (columnIndex != -1) getString(columnIndex) else null
    }

    data class UserData(val fullName: String, val email: String, val phone: String, val role: String)
}