package com.wilder.e_cmapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.wilder.e_cmapp.databinding.ActivityAccount2Binding

class Account2Activity : AppCompatActivity() {
    lateinit var binding: ActivityAccount2Binding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        binding = ActivityAccount2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        val db : FirebaseFirestore = FirebaseFirestore.getInstance()

        onLoad()


        binding.btnDeleteAccount.setOnClickListener {
            val getUsername = intent
            val userNameS = getUsername?.getStringExtra("getnickname")

            Toast.makeText(this, userNameS, Toast.LENGTH_LONG).show()

        }

        binding.btnToEdtFirstname.setOnClickListener{
            binding.edtFirstNameAccount.isEnabled = true
            binding.edtFirstNameAccount.setBackgroundResource(R.drawable.edtdesing)
        }

        binding.btnToEdtLastname.setOnClickListener{
            binding.edtLastNameAccount.isEnabled = true
            binding.edtLastNameAccount.setBackgroundResource(R.drawable.edtdesing)
        }

        binding.btnToEdtUsername.setOnClickListener{
            binding.edtUserNameAccount.isEnabled = true
            binding.edtUserNameAccount.setBackgroundResource(R.drawable.edtdesing)
        }

        binding.btnToEdtEmail.setOnClickListener{
            binding.edtEmailAccount.isEnabled = true
            binding.edtEmailAccount.setBackgroundResource(R.drawable.edtdesing)
        }


        binding.btnSaveChanges.setOnClickListener {
            val firstname = binding.edtFirstNameAccount.text
            val lastname = binding.edtLastNameAccount.text
            val username = binding.edtUserNameAccount.text
            val email = binding.edtEmailAccount.text
            val password = binding.edtPasswordAccount.text
            val lastUserName = binding.edtLastUserNameAccount.text.toString()
            if (firstname.isNotBlank() && lastname.isNotBlank() && username.isNotBlank() && email.isNotBlank() && password.isNotBlank() ){

                val data = hashMapOf(
                    "Firstname" to firstname.toString(),
                    "Lastname" to lastname.toString(),
                    "Email" to email.toString(),
                    "Password" to password.toString()
                )

                db.collection("Users")
                    .document(username.toString())
                    .set(data)
                    .addOnSuccessListener { result ->
                        Toast.makeText(this, "", Toast.LENGTH_LONG).show()
                        if (username.toString() != lastUserName){
                            deleteAccount(lastUserName)
                        }
                        blockEditText()
                    }
                    .addOnFailureListener{ error ->
                        Toast.makeText(this, R.string.signin_negative_process, Toast.LENGTH_LONG).show()
                    }

            }else{
                Toast.makeText(this, "It is necessary to fill all the fields", Toast.LENGTH_LONG).show()
            }
        }

   }

    fun onLoad(){

        val db : FirebaseFirestore = FirebaseFirestore.getInstance()

        val getUsername = intent
        val userNameS: String? = getUsername.getStringExtra("getnickname")

        binding.edtUserNameAccount.setText(userNameS)

        if (userNameS != null) {
            db.collection("Users")
                .document(userNameS.toString())
                .get()
                .addOnSuccessListener {
                    binding.edtFirstNameAccount.setText(it.get("Firstname") as String)
                    binding.edtLastNameAccount.setText(it.get("Lastname") as String)
                    binding.edtEmailAccount.setText(it.get("Email") as String)
                    binding.edtPasswordAccount.setText(it.get("Password") as String)
                    binding.edtLastUserNameAccount.setText(userNameS)
                }
                .addOnFailureListener {error ->
                    Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
                }
        }
    }

    fun deleteAccount(id: String){
        val db : FirebaseFirestore = FirebaseFirestore.getInstance()
        db.collection("Users")
            .document(id.toString())
            .delete()
            .addOnSuccessListener { res->

            }
            .addOnFailureListener { error ->

            }
    }

    fun blockEditText(){
        binding.edtFirstNameAccount.isEnabled = false
        binding.edtFirstNameAccount.setBackgroundColor(0)
        binding.edtLastNameAccount.isEnabled = false
        binding.edtLastNameAccount.setBackgroundColor(0)
        binding.edtUserNameAccount.isEnabled = false
        binding.edtUserNameAccount.setBackgroundColor(0)
        binding.edtEmailAccount.isEnabled = false
        binding.edtEmailAccount.setBackgroundColor(0)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bar, menu)
        return super.onCreateOptionsMenu(menu)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.ayudar -> {
            val intent = Intent(this, HelpActivity::class.java)
            startActivity(intent)
            true
        }
        R.id.cuenta -> {
            Toast.makeText(this, "...", Toast.LENGTH_SHORT).show()
            true
        }
        R.id.tema -> {
            Toast.makeText(this, "Day / Nigth", Toast.LENGTH_SHORT).show()
            true
        }
        R.id.configuracion -> {
            Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show()
            true
        }
        R.id.home -> {
            val redirecHome = Intent(this, homeCatalog::class.java)
            startActivity(redirecHome)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

}