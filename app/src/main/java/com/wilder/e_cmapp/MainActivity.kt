package com.wilder.e_cmapp

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import android.view.MenuItem
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.firestore.FirebaseFirestore
import com.wilder.e_cmapp.databinding.ActivityAccount2Binding
import com.wilder.e_cmapp.mySQLLiteHelper
import com.wilder.e_cmapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    var globaledtuserName = ""
    lateinit var binding: ActivityMainBinding
    lateinit var usersDbHelper: mySQLLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)

        val db : FirebaseFirestore = FirebaseFirestore.getInstance()

        usersDbHelper= mySQLLiteHelper(this)



        val signin:View = findViewById(R.id.signin_btn)
        signin.setOnClickListener{View->

            val goSignin = Intent(this, SigninActivity::class.java)
            startActivity(goSignin)

        }

        binding.btnLogin?.setOnClickListener{

            var edtuserName = binding.edtusuario.text
            val edtpassword = binding.edtclave.text
            val intent: Intent = Intent(this,homeCatalog::class.java)


            if (edtpassword.isNotBlank() && edtuserName.isNotBlank()){
                db.collection("Users")
                    .document(edtuserName.toString())
                    .get()
                    .addOnSuccessListener { res ->
                        if(res.getString("Password") == edtpassword.toString()){
                            val getUserName = res.id
                            Toast.makeText(this, "Successful validation", Toast.LENGTH_LONG).show()
                            intent.putExtra("getnickname",getUserName )
                            startActivity(intent)
                            edtuserName.clear()
                            edtpassword.clear()
                        }else{
                            Toast.makeText(this, "Wrong username or password", Toast.LENGTH_LONG).show()
                        }
                    }.addOnFailureListener{
                        Toast.makeText(this, "Unregistered user", Toast.LENGTH_LONG).show()
                    }
            }else{
                Toast.makeText(this, "It is necessary to fill all the fields", Toast.LENGTH_LONG).show()
            }
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
            Toast.makeText(this, "You need to log in to access this option", Toast.LENGTH_SHORT).show()
            true
        }
        R.id.tema->{
            Toast.makeText(this, "Day / Nigth", Toast.LENGTH_SHORT).show()
            teme()
            true
        }
        R.id.configuracion->{
            Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show()
            true
        }
        R.id.home->{
            Toast.makeText(this, "You need to log in to access this option", Toast.LENGTH_SHORT).show()
            true
        }
        else->{
            super.onOptionsItemSelected(item)
        }


    }

    fun teme(){
        val switch = findViewById<Switch>(R.id.swichDN)

        switch.setOnCheckedChangeListener{_,_ ->
            if (!switch.isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switch.text = "Night "
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switch.text = "Day "
            }
        }
    }


}
