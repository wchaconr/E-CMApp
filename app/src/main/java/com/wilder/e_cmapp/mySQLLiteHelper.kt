package com.wilder.e_cmapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class mySQLLiteHelper(context: Context): SQLiteOpenHelper(context, "Users.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val orderCreation = "CREATE TABLE Users" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "FirstName TEXT, LastName TEXT, Username TEXT, Email TEXT, Password TEXT)"
        db!!.execSQL(orderCreation)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val orderDelete="DROP TABLE IF EXISTS Users"
        db!!.execSQL(orderDelete)
        onCreate(db)
    }

    fun addData(Firstname: String, Lastname: String, Username: String, Email: String, Password: String){
        val data=ContentValues()
        data.put("FirstName", Firstname)
        data.put("LastName", Lastname)
        data.put("Username", Username)
        data.put("Email", Email)
        data.put("Password", Password)

        val db = this.writableDatabase
        db.insert("Users", null, data)
        db.close()
    }

    fun deleteData(id: Int):Int{
        val args = arrayOf(id.toString())
        val db = this.writableDatabase
        val  retorno = db.delete("Users", "_id", args) //id o user?
        db.close()
        return retorno
    }

    fun uploadData (id: Int, Firstname: String, Lastname: String, Username: String, Email: String, Password: String): Int {
        val args= arrayOf(id.toString())
        val data=ContentValues()
        data.put("FirstName", Firstname)
        data.put("LastName", Lastname)
        data.put("Username", Username)
        data.put("Email", Email)
        data.put("Password", Password)

        val db = this.writableDatabase
        val retorno = db.update("Users", data, "_id?", args)
        db.close()
        return retorno
    }

}