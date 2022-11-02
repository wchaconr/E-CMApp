package com.wilder.e_cmapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import android.os.PersistableBundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    private var edtusuario: EditText?=null
    private var edtclave: EditText?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        edtusuario=findViewById(R.id.edtusuario)
        edtclave=findViewById(R.id.edtclave)

        val signin:View = findViewById(R.id.signin_btn)
        signin.setOnClickListener{View->

            val goPay = Intent(this, SigninActivity::class.java)
            startActivity(goPay)

        }
    }

    fun login(btn:View){
        var user: String=edtusuario!!.text.toString()
        var password: String=edtclave!!.text.toString()


        if (user =="user@correo" && password == "123"){
            val login = Intent(this,homeCatalog::class.java)
            startActivity(login)
            Toast.makeText(this, "successful validation", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, "Wrong username or password", Toast.LENGTH_LONG).show()
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId)  {
        R.id.ayudar->{
            val intento = Intent(this, HelpActivity::class.java)
            startActivity(intento)
            true
        }
        R.id.cuenta->{
            Toast.makeText(this, "Necesitas ayuda ?", Toast.LENGTH_SHORT).show()
            true
        }
        R.id.tema->{
            Toast.makeText(this, "Day / Nigth", Toast.LENGTH_SHORT).show()
            true
        }
        R.id.configuracion->{
            Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show()
            true
        }
        R.id.home->{
            val redirecHome = Intent(this, homeCatalog::class.java)
            startActivity(redirecHome)
            true
        }
        else->{
            super.onOptionsItemSelected(item)
        }

    }

}
